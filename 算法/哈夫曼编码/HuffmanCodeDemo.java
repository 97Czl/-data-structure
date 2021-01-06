import java.util.*;
import java.io.*;

public class HuffmanCodeDemo
{
	public static void main(String[] args) 
	{
		/*
		String data = "i like like like java do you like a java hjauncakjkj jakljfklsajflhjdaoifhjlk  kjalkjfl ajflj lkahl ljl j";
		
		System.out.println("原始数据：");
		System.out.println(data);

		byte[] code = HuffmanCode.huffmanZip(data.getBytes());
		
		System.out.println("编码好存储为byte的数据：");
		System.out.println(Arrays.toString(code));

		byte[] result = HuffmanCode.huffmanUnZip(code, HuffmanCode.getBook());

		System.out.println("解码后的：");
		System.out.println(new String(result));
		*/
		

		//测试压缩文件
		HuffmanCode.zipFile("E://javafile//999.bmp", "E://javafile//test.zip");
		System.out.println("压缩成功");

		//测试解压缩文件
		HuffmanCode.unZipFile("E://javafile//test.zip", "E://javafile//huanyuan.bmp");
		System.out.println("解压缩成功");
	}
}

class HuffmanCode
{
	//存放对应的编码本
	private static Map<Byte, String> book = new HashMap<>();

	public static Map<Byte, String> getBook()
	{
		return book;
	}

	public static void zipFile(String srcFile, String dstFile)
	{
		InputStream fis = null;
		OutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			//建立对应的输入输出流
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(dstFile);
			oos = new ObjectOutputStream(fos);

			byte[] bytes = new byte[fis.available()];

			//读取
			fis.read(bytes);

			//开始压缩
			byte[] result = huffmanZip(bytes);

			//保存文件
			oos.writeObject(result);
			oos.writeObject(book);
		}
		catch (Exception w)
		{
			w.printStackTrace();
		}
		finally
		{
			try
			{
				fis.close();
				fos.close();
				oos.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void unZipFile(String srcFile, String dstFile)
	{
		InputStream fis = null;
		OutputStream fos = null;
		ObjectInputStream ois = null;
		try
		{
			//建立对应的输入输出流
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(dstFile);
			ois = new ObjectInputStream(fis);

			byte[] bytes = (byte[])ois.readObject();

			Map<Byte, String> codeBook = (Map<Byte, String>)ois.readObject();

			//开始解压缩
			byte[] result = huffmanUnZip(bytes, codeBook);

			//保存文件
			fos.write(result);
		}
		catch (Exception w)
		{
			w.printStackTrace();
		}
		finally
		{
			try
			{
				fis.close();
				fos.close();
				ois.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	//根据输入的字符串得到编码后的字符数组
	public static byte[] huffmanZip(byte[] data)
	{
		//根据字符串得到哈夫曼树
		HuffmanTree tree = getTree(data);
		//根据哈夫曼树得到编码字典
		Map<Byte, String> book = getCodeBook(tree);
		//根据字符串进行编码
		byte[] result = zip(data, book);
		return result;
	} 

	//解码
	public static byte[] huffmanUnZip(byte[] bytes, Map<Byte, String> book)
	{
		//首先根据编码后的字节得到对应的二进制字符串
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < bytes.length; i++)
		{
			boolean flag = (i == (bytes.length - 1) ? true : false);
			//获取对应的二进制字符串并连接起来
			builder.append(byteToString(bytes[i], flag));
		}
		
		//译码后的二进制
		System.out.println("接收到的二进制:");
		//System.out.println(builder);

		//将密码本反转，便于译码
		Map<String, Byte> decodeBook = new HashMap<>();
		for (Map.Entry<Byte, String> entry : book.entrySet())
		{
			decodeBook.put(entry.getValue(), entry.getKey());
		}
		
		//此处无法知道长度，用链表存储
		List<Byte> list = new ArrayList<>();
		int index = 0;
		//根据读取到的1 0 数据流依次译码
		for (int i = 0; i < builder.length();)
		{
			//保存往后读了多少位
			int count = 1;
			//在解码密码本读到的内容
			Byte b = null;
			while (b == null)
			{
				//尝试读取
				b = decodeBook.get(builder.substring(i, i + count));
				count++;
			}
			//读取到了对应的字节
			list.add(b);
			i = i + count - 1;
		}
		//将链表转换成数组
		byte[] result = new byte[list.size()];

		for (int i = 0; i < list.size(); i++)
		{
			result[i] = list.get(i);
		}
		return result;
	}

	//根据btye字符得到对应的比特流数据
	/**
	 *@param b 输入的byte字符
	 *@param boolean flag 标志位，表示是否为最后一个字节 true是 false不是
	 *return 返回b对应的二进制字符串
	 */
	private static String byteToString(byte b, boolean flag)
	{
		//这里存的b是int类型的
		int number = b;
		
		//如果不是最后一位
		if (!flag)
		{
			//需要将其进行补零操作，防止如果是整数，不足八位，需要将其补充成8位
			number = 256 | number;
		}
		
		String s = Integer.toBinaryString(number);
		
		//因为之前存储的有符号int类型，但是该方法调用的无符号，所以需要将最高位转换一下（如果是正数，前面的0会省略掉，读不了8位）
		if (flag)
		{
			//如果是最后一个字节直接返回
			return s;
		}
		else
		{
			return s.substring(s.length() - 8);
		}
	}

	//根据字符串的到哈夫曼树
	private static HuffmanTree getTree(byte[] buffer)
	{
		//用来保存每一个字符和其出现次数的map
		HashMap<Byte, Integer> dataMap = new HashMap<>();

		//对字符串进行遍历，依次统计每一个字符
		for (var ele : buffer)
		{
			Integer count = dataMap.get(ele);
			if (count == null)
			{
				dataMap.put(ele, 1);
			}
			else 
			{
				dataMap.put(ele, count + 1);
			}
		}
		//根据存放好的数据，生成链表
		List<Node> nodes = new ArrayList<>();

		//遍历map，存入到ArrayList中
		for (Map.Entry<Byte, Integer> entry : dataMap.entrySet())
		{
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		//生成树
		return new HuffmanTree(nodes);
	}


	//根据哈夫曼树得到编码表
	public static Map<Byte, String> getCodeBook(HuffmanTree tree)
	{
		if (tree.getRoot() != null)
		{
			codeBook(tree.getRoot(), "", new StringBuilder(""));
			return book;
		}
		return null;
	}

	//根据原始的字符串的byte数组得到编码后的哈夫曼byte数组
	/**
	 *@param bytes 原始数据对应的比特数组
	 *@param codeBook 原始数据编好的密码本
	 *@return 返回的是已经编码好的结果，对应8比特存储成一个Integer（这里存储的是原来的码 - 1 取反码）
	 */
	private static byte[] zip(byte[] bytes, Map<Byte, String> codeBook)
	{
		//首先，将所有的字符对应的二进制信息组成完整的字符串
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bytes)
		{
			stringBuilder.append(codeBook.get(b));
		}

		//System.out.println(stringBuilder);

		//然后求出需要创建的返回的比特数组的大小
		int length = stringBuilder.length() / 8 + ( stringBuilder.length() % 8 == 0 ? 0 : 1);
		byte[] result = new byte[length];
		
		int index = 0;
		//循环遍历，每8位存储成一个byte
		for (int i = 0; i < stringBuilder.length(); i += 8)
		{
			if (i + 8 > stringBuilder.length())
			{
				//注意这里存储的对应关系
				result[index] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
			}
			else
			{
				result[index] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
			}
			index++;
		}
		return result;
	}

	//工具方法，用来产生每一次的代码
	/**
	 *@param node 每次传入的结点
	 *@param code 每次路径，左边为0，右边为1
	 *@param stringBuilder 拼接路径
	 */
	private static void codeBook(Node node, String direction, StringBuilder stringBuilder)
	{
		//存放当前的编码
		StringBuilder buffer = new StringBuilder(stringBuilder.toString());
		buffer.append(direction);
		//如果当前是叶子结点，则保存数据
		if (node.getData() != null)
		{
			book.put(node.getData(), buffer.toString());
		}
		//如果不是叶子结点，则不是需要存储的数据，继续遍历
		else
		{
			//向左遍历
			codeBook(node.getLeft(), "0", buffer);
			//向右遍历
			codeBook(node.getRight(), "1", buffer);
		}
	}
}

class HuffmanTree
{
	private Node root = null;
	
	//默认构造器
	public HuffmanTree(){}
	//直接提供根节点的构造器
	public HuffmanTree(Node node)
	{
		this.root = node;
	}
	//提供链表的构造器
	public HuffmanTree(List<Node> list)
	{
		//调用生成哈夫曼树的方法来对该哈夫曼树初始化
		this.root = createHuffmanTree(list);
	}

	//访问元素的方法
	public Node getRoot()
	{
		return this.root;
	}
	public void setRoot(Node root)
	{
		this.root = root;
	} 

	public static Node createHuffmanTree(List<Node> nodes)
	{
		while (nodes.size() > 1)
		{
			//首先对链表进行排序
			Collections.sort(nodes);
			//取出最小的两个元素
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			//父节点肯定不是非叶子结点，则没有data
			Node fatherNode = new Node(null, leftNode.getWeight() + rightNode.getWeight());

			//移除已经分配的结点
			nodes.remove(leftNode);
			nodes.remove(rightNode);

			fatherNode.setLeft(leftNode);
			fatherNode.setRight(rightNode);
			
			nodes.add(fatherNode);
		}
		//返回根结点
		return nodes.get(0);
	}

	//哈夫曼树的前序遍历方法
	public void preOrder()
	{
		if (root != null)
		{
			root.preOrder();
		}
		else
		{
			System.out.println("树空，无法遍历！");
		}
	}
}

//存放的数据，包含data、weight、left、right
class Node implements Comparable<Node>
{
	//定义节点的域
	private Byte data;
	private int weight;
	private Node left;
	private Node right;

	//参数的设置和获取方法
	public Byte getData()
	{
		return this.data;
	}
	public int getWeight()
	{
		return this.weight;
	}
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	public Node getLeft()
	{
		return this.left;
	}
	public void setLeft(Node left)
	{
		this.left = left;
	}

	public Node getRight()
	{
		return this.right;
	}
	public void setRight(Node right)
	{
		this.right = right;
	}

	//构造器
	public Node(Byte data, int weight)
	{
		this.data = data;
		this.weight = weight;
	}

	public int compareTo(Node o)
	{
		return weight - o.getWeight();
	}

	//重写toString方法
	public String toString()
	{
		return "[Node:data = " + data + " weight=" + weight + "]";
	}

	//进行前序遍历的递归方法
	public void preOrder()
	{
		//首先输出当前元素
		System.out.println(this);
		//递归左子节点
		if (this.getLeft() != null)
		{
			this.getLeft().preOrder();
		}
		//递归右子节点
		if (this.getRight() != null)
		{
			this.getRight().preOrder();
		}
	}
}