import java.util.Collections;
import java.util.ArrayList;

public class HuffmanTree
{
	public static void main(String[] args) 
	{
		int[] arr = new int[]{13, 7, 8, 3, 29, 6};
		Node root = createHuffmanTree(arr);

		preOrder(root);
	}

	//前序遍历
	public static void preOrder(Node root)
	{
		if (root != null)
		{
			root.preOrder();
		}
		else
		{
			System.out.println("树空！ 不能遍历~~");
		}
	}

	//创建Huffman树的方法
	/**
	 *@param arr 需要被创建成哈夫曼树的数组
	 *@return 返回创建好的哈夫曼树的根节点
	 */
	public static Node createHuffmanTree(int[] arr)
	{
		//首先将数组保存在链表中
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int ele : arr)
		{
			nodes.add(new Node(ele));
		}

		//对链表进行排序
		Collections.sort(nodes);
		
		//循环进行创建步骤
		//1、取出两个最小权值的元素，组成新的树，
		//2、权值之和作为新的元素保存在链表中
		//3、对链表重新排序
		while (nodes.size() > 1)
		{
			//取出两个最小权值的元素
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			Node fatherNode = new Node(leftNode.getValue() + rightNode.getValue());
			//组成新的树，
			fatherNode.setLeft(leftNode);
			fatherNode.setRight(rightNode);
			
			nodes.remove(leftNode);
			nodes.remove(rightNode);

			//权值之和作为新的元素保存在链表中
			nodes.add(fatherNode);

			//对链表重新排序
			Collections.sort(nodes);
		}
		return nodes.get(0);
	}
}

class Node implements Comparable<Node>
{
	//定义节点的域
	private int value;
	private Node left;
	private Node right;

	//参数的设置和获取方法
	public int getValue()
	{
		return this.value;
	}
	public void setValue(int value)
	{
		this.value = value;
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
	public Node(int value)
	{
		this.value = value;
	}

	public int compareTo(Node o)
	{
		return this.getValue() - o.getValue();
	}

	//重写toString方法
	public String toString()
	{
		return "[Node: " + this.getValue() + "]";
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