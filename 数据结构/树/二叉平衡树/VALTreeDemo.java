public class VALTreeDemo 
{
	public static void main(String[] args) 
	{
		//����˫����ת
		//int[] arr = new int[]{10, 11, 7, 6, 8, 9};
		//����˫����ת
		int[] arr = new int[]{2, 1, 6, 5, 7, 3};
		//���Ե�����ת
		//int[] arr = new int[]{10, 12, 8, 9, 7, 6};
		//���Ե�����ת
		//int[] arr = new int[]{4, 3, 6, 5, 7, 8};
		
		VALTree bst = new VALTree();

		//����һ������ƽ����
		for (int ele : arr)
		{
			bst.add(new Node(ele));
		}

		//����������
		System.out.println("���������");
		bst.suffixOrder();
		
		System.out.println("��ǰ����root = " + bst.getRoot());
		System.out.println("��ǰ������� = " + bst.getHeight());
		System.out.println("��ǰ������������� = " + bst.leftHeight());
		System.out.println("��ǰ������������� = " + bst.rightHeight());
	}
}

//����ƽ��������
class VALTree
{
	private Node root;

	//������ȡ����
	public Node getRoot()
	{
		return this.root;
	}
	//������
	public VALTree(){}
	public VALTree(Node root)
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

	//*********************ƽ��������Ķ�ķ���**********************
	//�����ǰ���������������ĸ߶�
	public int getHeight()
	{
		return root.getHeight();
	}
	//��ǰ�����������߶�
	public int leftHeight()
	{
		return root.leftHeight();
	}
	//��ǰ�����������ĸ߶�
	public int rightHeight()
	{
		return root.rightHeight();
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

	//ʵ�ֶ���ƽ��������ӽ�㷽��
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

		//*************************************************************
		//ƽ�����������������������������������ݻ����ƽ�⣬��֤���ߵĸ߶�һ��
		///*
		if (this.leftHeight() - this.rightHeight() > 1)
		{
			//���������������߶ȴ���2����Ҫ����ת������ע��********* ��������������ӽڵ�ĸ߶ȸ������ӽ�㣬����Ҫ���ȶ�������������Ƚ�������ת
			if (this.left != null && this.left.leftHeight() < this.left.rightHeight())
			{
				this.left.leftRotate();
			}
			this.rightRotate();

			//��֤��ȫ������������������������
			return ;
		}
		//*/ 
		///*
		if (this.rightHeight() - this.leftHeight() > 1)
		{
			//���������������߶ȴ���2����Ҫ����ת������ע��********* ��������������ӽڵ�ĸ߶ȸ������ӽ�㣬����Ҫ���ȶ�������������Ƚ�������ת
			if (this.right != null && this.right.rightHeight() < this.right.leftHeight())
			{
				this.right.rightRotate();
			}
			this.leftRotate();
		}
		//*/
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

	//-----------------------------------������Ϊ��ʵ�ֶ���ƽ��������ķ���----------------------------------------
	//�����ǰ����������������ĸ߶�
	public int getHeight()
	{
		return Math.max(this.left == null ? 0 : this.left.getHeight(), this.right == null ? 0 : this.right.getHeight()) + 1;
	}
	//��ǰ�����������߶�
	public int leftHeight()
	{
		return this.left == null ? 0 : this.left.getHeight();
	}
	//��ǰ�����������ĸ߶�
	public int rightHeight()
	{
		return this.right == null ? 0 : this.right.getHeight();
	}
	//����ת����
	public void leftRotate()
	{
		//��ת�Ĺ���
		//����ת��������ת
		//���ȶ����½��
		Node newNode = new Node(this.value);
		//���½�����ָ��ָ��ԭ�������ӽ��
		newNode.setLeft(this.left);
		//���½�����ָ��ָ��ԭ�������ӽڵ�����ӽ��
		newNode.setRight(this.right.getLeft());
		//�����ӽ�����Ƶ�root
		this.value = this.right.getValue();
		//���õ�ǰ�������ӽ�����½��
		this.setLeft(newNode);
		//���õ�ǰ����������Ϊright.right
		this.setRight(this.right.getRight());
	}
	//����ת����
	public void rightRotate()
	{
		//��ת�Ĺ���
		//����ת��������ת
		//���ȶ����½��
		Node newNode = new Node(this.value);
		//���½�����ָ��ָ��ԭ�������ӽ��
		newNode.setRight(this.right);
		//���½�����ָ��ָ��ԭ�������ӽڵ�����ӽ��
		newNode.setLeft(this.left.getRight());
		//�����ӽ�����Ƶ�root
		this.value = this.left.getValue();
		//���õ�ǰ�������ӽ�����½��
		this.setRight(newNode);
		//���õ�ǰ����������Ϊleft.left
		this.setLeft(this.left.getLeft());
	}

	//��дtoString()����
	@Override
	public String toString()
	{
		return "Node[value = " + value + "]";
	}
}