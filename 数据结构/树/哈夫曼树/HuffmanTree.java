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

	//ǰ�����
	public static void preOrder(Node root)
	{
		if (root != null)
		{
			root.preOrder();
		}
		else
		{
			System.out.println("���գ� ���ܱ���~~");
		}
	}

	//����Huffman���ķ���
	/**
	 *@param arr ��Ҫ�������ɹ�������������
	 *@return ���ش����õĹ��������ĸ��ڵ�
	 */
	public static Node createHuffmanTree(int[] arr)
	{
		//���Ƚ����鱣����������
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int ele : arr)
		{
			nodes.add(new Node(ele));
		}

		//�������������
		Collections.sort(nodes);
		
		//ѭ�����д�������
		//1��ȡ��������СȨֵ��Ԫ�أ�����µ�����
		//2��Ȩֵ֮����Ϊ�µ�Ԫ�ر�����������
		//3����������������
		while (nodes.size() > 1)
		{
			//ȡ��������СȨֵ��Ԫ��
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			Node fatherNode = new Node(leftNode.getValue() + rightNode.getValue());
			//����µ�����
			fatherNode.setLeft(leftNode);
			fatherNode.setRight(rightNode);
			
			nodes.remove(leftNode);
			nodes.remove(rightNode);

			//Ȩֵ֮����Ϊ�µ�Ԫ�ر�����������
			nodes.add(fatherNode);

			//��������������
			Collections.sort(nodes);
		}
		return nodes.get(0);
	}
}

class Node implements Comparable<Node>
{
	//����ڵ����
	private int value;
	private Node left;
	private Node right;

	//���������úͻ�ȡ����
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

	//������
	public Node(int value)
	{
		this.value = value;
	}

	public int compareTo(Node o)
	{
		return this.getValue() - o.getValue();
	}

	//��дtoString����
	public String toString()
	{
		return "[Node: " + this.getValue() + "]";
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