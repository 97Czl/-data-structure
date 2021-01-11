public class BinarySortTreeDemo 
{
	public static void main(String[] args) 
	{
		int[] arr = new int[]{7, 3, 10, 12, 5, 1, 9, 2};
		
		BinarySortTree bst = new BinarySortTree();

		//����һ������������
		for (int ele : arr)
		{
			bst.add(new Node(ele));
		}

		//����������
		System.out.println("���������");
		bst.suffixOrder();

		//����ɾ��Ҷ�ӽ��
		//bst.delNode(2);
		//bst.delNode(5);
		//bst.delNode(9);
		//bst.delNode(12);
		//����ɾ����һ�������Ľ��
		//bst.delNode(1);
		//����ɾ�������������Ľ��
		//bst.delNode(3);
		//bst.delNode(10);
		bst.delNode(7);

		//����������
		System.out.println("ɾ����ģ�");
		bst.suffixOrder();

	}
}

//��������������
class BinarySortTree
{
	private Node root;

	//������ȡ����
	public Node getRoot()
	{
		return this.root;
	}
	//������
	public BinarySortTree(){}
	public BinarySortTree(Node root)
	{
		this.root = root;
	}

	//��������ӽ��ķ���
	public void add(Node node)
	{
		if (root == null)
		{
			root = node;
		}
		else
		{
			root.add(node);
		}
	}

	//�����������
	public void suffixOrder()
	{
		if (root == null)
		{
			System.out.println("��Ϊ�գ��޷�����������");
		}
		else
		{
			root.suffixOrder();
		}
	}

	//����ĳ�����ķ���
	/**
	 *@param value Ҫ���ҵĽ���value
	 *@return ���ظýڵ�
	 */
	public Node search(int value)
	{
		if (this.root == null)
		{
			return null;
		}
		else 
		{
			return root.search(value);
		}
	}
	public Node searchFather(int value)
	{
		if (this.root == null)
		{
			return null;
		}
		else 
		{
			return root.searchFather(value);
		}
	}

	//ɾ�����ķ���
	public void delNode(int value)
	{
		//�����ж�root
		if (this.root == null)
		{
			return;
		}
		else
		{
			//�ҵ�Ҫɾ���Ľ��
			Node targetNode = search(value);
			//���û�ҵ�
			if (targetNode == null)
			{
				return;
			}

			//�����ǰ��ֻ��һ�����
			if (this.root.getLeft() == null && this.root.getRight() == null)
			{
				this.root = null;
				return;
			}

			//�ҵ�Ҫɾ���Ľ��ĸ����
			Node parent = searchFather(value);

			//���Ҫɾ������û���ӽ���Ҷ�ӽ��
			if (targetNode.getLeft() == null && targetNode.getRight() == null)
			{
				//�ж�Ŀ�������ڸ������ıߣ�Ȼ��ֱ��ɾ������
				if (parent.getLeft() != null && parent.getLeft().getValue() == value)
				{
					parent.setLeft(null);
				}
				else if (parent.getRight() != null && parent.getRight().getValue() == value)
				{
					parent.setRight(null);
				}
			}
			//ɾ��ӵ�����������Ľ��
			else if (targetNode.getLeft() != null && targetNode.getRight() != null)
			{
				//�ҵ��ý�������������С����ֵ
				int minValue = delRightMinNode(targetNode.getRight());
				targetNode.setValue(minValue);
			}
			//ɾ��ֻ��һ�������Ľ��
			else 
			{
				//�����ǰ���ӵ��������
				if (targetNode.getLeft() != null)
				{
					//�����ų�ֻʣ������������������Ļ���Ҫɾ�����ڵ㣬parent��null
					if (parent == null)
					{
						root = targetNode.getLeft();
					}
					else 
					{
						//���Ҫɾ���Ľ���Ǹ��������ӽ��
						if (parent.getLeft() != null && parent.getLeft().getValue() == targetNode.getValue())
						{
							parent.setLeft(targetNode.getLeft());
						}
						else 
						{
							parent.setRight(targetNode.getLeft());
						}
					}
				}
				else 
				{
					//��ǰ�����������
					//�����ų�ֻʣ������������������Ļ���Ҫɾ�����ڵ㣬parent��null
					if (parent == null)
					{
						root = targetNode.getRight();
					}
					else 
					{
						//���Ҫɾ���Ľ���Ǹ��������ӽ��
						if (parent.getLeft() != null && parent.getLeft().getValue() == targetNode.getValue())
						{
							parent.setLeft(targetNode.getRight());
						}
						else 
						{
							parent.setRight(targetNode.getRight());
						}
					}
				}
			}
		}
	}

	//��������ɾ��ӵ�����������Ľ��ķ���
	private int delRightMinNode(Node node)
	{
		Node temp = node;
		//�ҵ���С�Ľ��
		while (temp.getLeft() != null)
		{
			temp = temp.getLeft();
		}
		//ɾ���ý��
		delNode(temp.getValue());
		return temp.getValue();
	}
}

class Node
{
	private int value;
	private Node left;
	private Node right;
	
	//������
	public Node(int value)
	{
		this.value = value;
	}
	
	//�����������úͻ�ȡ����
	public void setValue(int value)
	{
		this.value = value;
	}
	public int getValue()
	{
		return this.value;
	}
	public void setLeft(Node left)
	{
		this.left = left;
	}
	public Node getLeft()
	{
		return this.left;
	}
	public void setRight(Node right)
	{
		this.right = right;
	}
	public Node getRight()
	{
		return this.right;
	}

	//ʵ�ֶ�������������ӽ�㷽��
	public void add(Node node)
	{
		//�жϵ�ǰ���ɲ����Բ���
		if (node == null)
		{
			return;
		}
		//�жϲ�����ֵ�뵱ǰ���Ĵ�С��ϵ
		if (node.getValue() < this.value)
		{
			//�жϵ�ǰ���������Ƿ�Ϊ�գ�����ǿ�ֱ����Ӽ��ɣ����������Ҫ�����ݹ����
			if (this.left == null)
			{
				this.left = node;
			}
			else
			{
				this.left.add(node);
			}
		}
		else
		{
			//�������Ľ��ֵ�ȵ�ǰ���ֵ����ߵ���
			if (this.right == null)
			{
				this.right = node;
			}
			else
			{
				this.right.add(node);
			}
		}
	}
	
	//�����������
	public void suffixOrder()
	{
		//���ȴ�����߽��
		if (this.left != null)
		{
			//�ݹ�
			this.left.suffixOrder();
		}
		
		//��ӡ��ǰ���
		System.out.println(this.toString());

		//�����ұ߽��
		if (this.right != null)
		{
			//�ݹ�
			this.right.suffixOrder();
		}
	}

	//Ҫɾ����㣬��Ҫ�ҵ�Ҫɾ���Ľ��͸��ڵ�
	public Node search(int value)
	{
		//�����жϵ�ǰ����ǲ���Ҫ�ҵĽ��
		if (this.value == value)
		{
			return this;
		}
		else if (value < this.value)
		{
			//˵��Ҫ���ҵĽ���ڵ�ǰ�������
			if (this.left == null)
			{
				return null;
			}
			else 
			{
				//����ݹ����
				return this.left.search(value);
			}
		}
		else 
		{
			//Ҫ���ҵĽ���ڵ�ǰ�����Ҳ�
			if (this.right == null)
			{
				return null;
			}
			else 
			{
				//���Ҳ�ݹ����
				return this.right.search(value);
			}
		}
	}
	//���Ҹ��ڵ�ķ���
	public Node searchFather(int value)
	{
		//�����ǰ���ͷ���
		if ((this.left != null && this.left.getValue() == value) || (this.right != null && this.right.getValue() == value))
		{
			return this;
		}
		else
		{
			//�����ǰ��㲻��Ҫ�ҵĽ�㣬���ȿ����
			if (value < this.value && this.left != null)
			{
				//��ߵݹ�
				return this.left.searchFather(value);
			}
			else if (value >= this.value && this.right != null)
			{
				//�ұߵݹ�
				return this.right.searchFather(value);
			}
			else 
				return null;
		}
	}

	//��дtoString()����
	@Override
	public String toString()
	{
		return "Node[value = " + value + "]";
	}
}