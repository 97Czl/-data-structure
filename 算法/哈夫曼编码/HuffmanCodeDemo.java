import java.util.*;
import java.io.*;

public class HuffmanCodeDemo
{
	public static void main(String[] args) 
	{
		/*
		String data = "i like like like java do you like a java hjauncakjkj jakljfklsajflhjdaoifhjlk  kjalkjfl ajflj lkahl ljl j";
		
		System.out.println("ԭʼ���ݣ�");
		System.out.println(data);

		byte[] code = HuffmanCode.huffmanZip(data.getBytes());
		
		System.out.println("����ô洢Ϊbyte�����ݣ�");
		System.out.println(Arrays.toString(code));

		byte[] result = HuffmanCode.huffmanUnZip(code, HuffmanCode.getBook());

		System.out.println("�����ģ�");
		System.out.println(new String(result));
		*/
		

		//����ѹ���ļ�
		HuffmanCode.zipFile("E://javafile//999.bmp", "E://javafile//test.zip");
		System.out.println("ѹ���ɹ�");

		//���Խ�ѹ���ļ�
		HuffmanCode.unZipFile("E://javafile//test.zip", "E://javafile//huanyuan.bmp");
		System.out.println("��ѹ���ɹ�");
	}
}

class HuffmanCode
{
	//��Ŷ�Ӧ�ı��뱾
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
			//������Ӧ�����������
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(dstFile);
			oos = new ObjectOutputStream(fos);

			byte[] bytes = new byte[fis.available()];

			//��ȡ
			fis.read(bytes);

			//��ʼѹ��
			byte[] result = huffmanZip(bytes);

			//�����ļ�
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
			//������Ӧ�����������
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(dstFile);
			ois = new ObjectInputStream(fis);

			byte[] bytes = (byte[])ois.readObject();

			Map<Byte, String> codeBook = (Map<Byte, String>)ois.readObject();

			//��ʼ��ѹ��
			byte[] result = huffmanUnZip(bytes, codeBook);

			//�����ļ�
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

	//����������ַ����õ��������ַ�����
	public static byte[] huffmanZip(byte[] data)
	{
		//�����ַ����õ���������
		HuffmanTree tree = getTree(data);
		//���ݹ��������õ������ֵ�
		Map<Byte, String> book = getCodeBook(tree);
		//�����ַ������б���
		byte[] result = zip(data, book);
		return result;
	} 

	//����
	public static byte[] huffmanUnZip(byte[] bytes, Map<Byte, String> book)
	{
		//���ȸ��ݱ������ֽڵõ���Ӧ�Ķ������ַ���
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < bytes.length; i++)
		{
			boolean flag = (i == (bytes.length - 1) ? true : false);
			//��ȡ��Ӧ�Ķ������ַ�������������
			builder.append(byteToString(bytes[i], flag));
		}
		
		//�����Ķ�����
		System.out.println("���յ��Ķ�����:");
		//System.out.println(builder);

		//�����뱾��ת����������
		Map<String, Byte> decodeBook = new HashMap<>();
		for (Map.Entry<Byte, String> entry : book.entrySet())
		{
			decodeBook.put(entry.getValue(), entry.getKey());
		}
		
		//�˴��޷�֪�����ȣ�������洢
		List<Byte> list = new ArrayList<>();
		int index = 0;
		//���ݶ�ȡ����1 0 ��������������
		for (int i = 0; i < builder.length();)
		{
			//����������˶���λ
			int count = 1;
			//�ڽ������뱾����������
			Byte b = null;
			while (b == null)
			{
				//���Զ�ȡ
				b = decodeBook.get(builder.substring(i, i + count));
				count++;
			}
			//��ȡ���˶�Ӧ���ֽ�
			list.add(b);
			i = i + count - 1;
		}
		//������ת��������
		byte[] result = new byte[list.size()];

		for (int i = 0; i < list.size(); i++)
		{
			result[i] = list.get(i);
		}
		return result;
	}

	//����btye�ַ��õ���Ӧ�ı���������
	/**
	 *@param b �����byte�ַ�
	 *@param boolean flag ��־λ����ʾ�Ƿ�Ϊ���һ���ֽ� true�� false����
	 *return ����b��Ӧ�Ķ������ַ���
	 */
	private static String byteToString(byte b, boolean flag)
	{
		//������b��int���͵�
		int number = b;
		
		//����������һλ
		if (!flag)
		{
			//��Ҫ������в����������ֹ����������������λ����Ҫ���䲹���8λ
			number = 256 | number;
		}
		
		String s = Integer.toBinaryString(number);
		
		//��Ϊ֮ǰ�洢���з���int���ͣ����Ǹ÷������õ��޷��ţ�������Ҫ�����λת��һ�£������������ǰ���0��ʡ�Ե���������8λ��
		if (flag)
		{
			//��������һ���ֽ�ֱ�ӷ���
			return s;
		}
		else
		{
			return s.substring(s.length() - 8);
		}
	}

	//�����ַ����ĵ���������
	private static HuffmanTree getTree(byte[] buffer)
	{
		//��������ÿһ���ַ�������ִ�����map
		HashMap<Byte, Integer> dataMap = new HashMap<>();

		//���ַ������б���������ͳ��ÿһ���ַ�
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
		//���ݴ�źõ����ݣ���������
		List<Node> nodes = new ArrayList<>();

		//����map�����뵽ArrayList��
		for (Map.Entry<Byte, Integer> entry : dataMap.entrySet())
		{
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		//������
		return new HuffmanTree(nodes);
	}


	//���ݹ��������õ������
	public static Map<Byte, String> getCodeBook(HuffmanTree tree)
	{
		if (tree.getRoot() != null)
		{
			codeBook(tree.getRoot(), "", new StringBuilder(""));
			return book;
		}
		return null;
	}

	//����ԭʼ���ַ�����byte����õ������Ĺ�����byte����
	/**
	 *@param bytes ԭʼ���ݶ�Ӧ�ı�������
	 *@param codeBook ԭʼ���ݱ�õ����뱾
	 *@return ���ص����Ѿ�����õĽ������Ӧ8���ش洢��һ��Integer������洢����ԭ������ - 1 ȡ���룩
	 */
	private static byte[] zip(byte[] bytes, Map<Byte, String> codeBook)
	{
		//���ȣ������е��ַ���Ӧ�Ķ�������Ϣ����������ַ���
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bytes)
		{
			stringBuilder.append(codeBook.get(b));
		}

		//System.out.println(stringBuilder);

		//Ȼ�������Ҫ�����ķ��صı�������Ĵ�С
		int length = stringBuilder.length() / 8 + ( stringBuilder.length() % 8 == 0 ? 0 : 1);
		byte[] result = new byte[length];
		
		int index = 0;
		//ѭ��������ÿ8λ�洢��һ��byte
		for (int i = 0; i < stringBuilder.length(); i += 8)
		{
			if (i + 8 > stringBuilder.length())
			{
				//ע������洢�Ķ�Ӧ��ϵ
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

	//���߷�������������ÿһ�εĴ���
	/**
	 *@param node ÿ�δ���Ľ��
	 *@param code ÿ��·�������Ϊ0���ұ�Ϊ1
	 *@param stringBuilder ƴ��·��
	 */
	private static void codeBook(Node node, String direction, StringBuilder stringBuilder)
	{
		//��ŵ�ǰ�ı���
		StringBuilder buffer = new StringBuilder(stringBuilder.toString());
		buffer.append(direction);
		//�����ǰ��Ҷ�ӽ�㣬�򱣴�����
		if (node.getData() != null)
		{
			book.put(node.getData(), buffer.toString());
		}
		//�������Ҷ�ӽ�㣬������Ҫ�洢�����ݣ���������
		else
		{
			//�������
			codeBook(node.getLeft(), "0", buffer);
			//���ұ���
			codeBook(node.getRight(), "1", buffer);
		}
	}
}

class HuffmanTree
{
	private Node root = null;
	
	//Ĭ�Ϲ�����
	public HuffmanTree(){}
	//ֱ���ṩ���ڵ�Ĺ�����
	public HuffmanTree(Node node)
	{
		this.root = node;
	}
	//�ṩ����Ĺ�����
	public HuffmanTree(List<Node> list)
	{
		//�������ɹ��������ķ������Ըù���������ʼ��
		this.root = createHuffmanTree(list);
	}

	//����Ԫ�صķ���
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
			//���ȶ������������
			Collections.sort(nodes);
			//ȡ����С������Ԫ��
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			//���ڵ�϶����Ƿ�Ҷ�ӽ�㣬��û��data
			Node fatherNode = new Node(null, leftNode.getWeight() + rightNode.getWeight());

			//�Ƴ��Ѿ�����Ľ��
			nodes.remove(leftNode);
			nodes.remove(rightNode);

			fatherNode.setLeft(leftNode);
			fatherNode.setRight(rightNode);
			
			nodes.add(fatherNode);
		}
		//���ظ����
		return nodes.get(0);
	}

	//����������ǰ���������
	public void preOrder()
	{
		if (root != null)
		{
			root.preOrder();
		}
		else
		{
			System.out.println("���գ��޷�������");
		}
	}
}

//��ŵ����ݣ�����data��weight��left��right
class Node implements Comparable<Node>
{
	//����ڵ����
	private Byte data;
	private int weight;
	private Node left;
	private Node right;

	//���������úͻ�ȡ����
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

	//������
	public Node(Byte data, int weight)
	{
		this.data = data;
		this.weight = weight;
	}

	public int compareTo(Node o)
	{
		return weight - o.getWeight();
	}

	//��дtoString����
	public String toString()
	{
		return "[Node:data = " + data + " weight=" + weight + "]";
	}

	//����ǰ������ĵݹ鷽��
	public void preOrder()
	{
		//���������ǰԪ��
		System.out.println(this);
		//�ݹ����ӽڵ�
		if (this.getLeft() != null)
		{
			this.getLeft().preOrder();
		}
		//�ݹ����ӽڵ�
		if (this.getRight() != null)
		{
			this.getRight().preOrder();
		}
	}
}