public class VALTreeDemo 
{
	public static void main(String[] args) 
	{
		//测试双右旋转
		//int[] arr = new int[]{10, 11, 7, 6, 8, 9};
		//测试双左旋转
		int[] arr = new int[]{2, 1, 6, 5, 7, 3};
		//测试单右旋转
		//int[] arr = new int[]{10, 12, 8, 9, 7, 6};
		//测试单左旋转
		//int[] arr = new int[]{4, 3, 6, 5, 7, 8};
		
		VALTree bst = new VALTree();

		//创建一个二叉平衡树
		for (int ele : arr)
		{
			bst.add(new Node(ele));
		}

		//中序遍历输出
		System.out.println("中序遍历：");
		bst.suffixOrder();
		
		System.out.println("当前树的root = " + bst.getRoot());
		System.out.println("当前树的深度 = " + bst.getHeight());
		System.out.println("当前左子树树的深度 = " + bst.leftHeight());
		System.out.println("当前右子树树的深度 = " + bst.rightHeight());
	}
}

//二叉平衡树的类
class VALTree
{
	private Node root;

	//参数获取方法
	public Node getRoot()
	{
		return this.root;
	}
	//构造器
	public VALTree(){}
	public VALTree(Node root)
	{
		this.root = root;
	}

	//给链表添加结点的方法
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

	//中序遍历方法
	public void suffixOrder()
	{
		if (root == null)
		{
			System.out.println("树为空，无法遍历！！！");
		}
		else
		{
			root.suffixOrder();
		}
	}

	//查找某个结点的方法
	/**
	 *@param value 要查找的结点的value
	 *@return 返回该节点
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

	//删除结点的方法
	public void delNode(int value)
	{
		//首先判断root
		if (this.root == null)
		{
			return;
		}
		else
		{
			//找到要删除的结点
			Node targetNode = search(value);
			//如果没找到
			if (targetNode == null)
			{
				return;
			}

			//如果当前树只有一个结点
			if (this.root.getLeft() == null && this.root.getRight() == null)
			{
				this.root = null;
				return;
			}

			//找到要删除的结点的父结点
			Node parent = searchFather(value);

			//如果要删除的是没有子结点的叶子结点
			if (targetNode.getLeft() == null && targetNode.getRight() == null)
			{
				//判断目标结点是在父结点的哪边，然后直接删除即可
				if (parent.getLeft() != null && parent.getLeft().getValue() == value)
				{
					parent.setLeft(null);
				}
				else if (parent.getRight() != null && parent.getRight().getValue() == value)
				{
					parent.setRight(null);
				}
			}
			//删除拥有两个子树的结点
			else if (targetNode.getLeft() != null && targetNode.getRight() != null)
			{
				//找到该结点的右子树中最小结点的值
				int minValue = delRightMinNode(targetNode.getRight());
				targetNode.setValue(minValue);
			}
			//删除只有一个子树的结点
			else 
			{
				//如果当前结点拥有左子树
				if (targetNode.getLeft() != null)
				{
					//首先排除只剩两个结点的情况，这样的话，要删除根节点，parent是null
					if (parent == null)
					{
						root = targetNode.getLeft();
					}
					else 
					{
						//如果要删除的结点是父结点的左子结点
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
					//当前结点有右子树
					//首先排除只剩两个结点的情况，这样的话，要删除根节点，parent是null
					if (parent == null)
					{
						root = targetNode.getRight();
					}
					else 
					{
						//如果要删除的结点是父结点的左子结点
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

	//用来辅助删除拥有两个子树的结点的方法
	private int delRightMinNode(Node node)
	{
		Node temp = node;
		//找到最小的结点
		while (temp.getLeft() != null)
		{
			temp = temp.getLeft();
		}
		//删除该结点
		delNode(temp.getValue());
		return temp.getValue();
	}

	//*********************平衡二叉树的多的方法**********************
	//求出当前树所包含的子树的高度
	public int getHeight()
	{
		return root.getHeight();
	}
	//求当前树的左子树高度
	public int leftHeight()
	{
		return root.leftHeight();
	}
	//求当前树的右子树的高度
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
	
	//构造器
	public Node(int value)
	{
		this.value = value;
	}
	
	//各参数的设置和获取方法
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

	//实现二叉平衡树的添加结点方法
	public void add(Node node)
	{
		//判断当前结点可不可以插入
		if (node == null)
		{
			return;
		}
		//判断参数的值与当前结点的大小关系
		if (node.getValue() < this.value)
		{
			//判断当前结点的左结点是否为空，如果是空直接添加即可，如果不是需要继续递归遍历
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
			//如果传入的结点值比当前结点值大或者等于
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
		//平衡二叉树和排序二叉树的区别就是添加完数据会进行平衡，保证两边的高度一致
		///*
		if (this.leftHeight() - this.rightHeight() > 1)
		{
			//左子树比右子树高度大于2，需要右旋转，但是注意********* 如果左子树的右子节点的高度高于左子结点，则需要首先对左子树结点首先进行左旋转
			if (this.left != null && this.left.leftHeight() < this.left.rightHeight())
			{
				this.left.leftRotate();
			}
			this.rightRotate();

			//保证安全！！！！！！！！！！！！
			return ;
		}
		//*/ 
		///*
		if (this.rightHeight() - this.leftHeight() > 1)
		{
			//右子树比左子树高度大于2，需要左旋转，但是注意********* 如果右子树的左子节点的高度高于右子结点，则需要首先对右子树结点首先进行右旋转
			if (this.right != null && this.right.rightHeight() < this.right.leftHeight())
			{
				this.right.rightRotate();
			}
			this.leftRotate();
		}
		//*/
	}
	
	//中序遍历方法
	public void suffixOrder()
	{
		//首先处理左边结点
		if (this.left != null)
		{
			//递归
			this.left.suffixOrder();
		}
		
		//打印当前结点
		System.out.println(this.toString());

		//处理右边结点
		if (this.right != null)
		{
			//递归
			this.right.suffixOrder();
		}
	}

	//要删除结点，需要找到要删除的结点和父节点
	public Node search(int value)
	{
		//首先判断当前结点是不是要找的结点
		if (this.value == value)
		{
			return this;
		}
		else if (value < this.value)
		{
			//说明要查找的结点在当前结点的左侧
			if (this.left == null)
			{
				return null;
			}
			else 
			{
				//向左递归遍历
				return this.left.search(value);
			}
		}
		else 
		{
			//要查找的结点在当前结点的右侧
			if (this.right == null)
			{
				return null;
			}
			else 
			{
				//向右侧递归遍历
				return this.right.search(value);
			}
		}
	}
	//查找父节点的方法
	public Node searchFather(int value)
	{
		//如果当前结点就符合
		if ((this.left != null && this.left.getValue() == value) || (this.right != null && this.right.getValue() == value))
		{
			return this;
		}
		else
		{
			//如果当前结点不是要找的结点，首先看左边
			if (value < this.value && this.left != null)
			{
				//左边递归
				return this.left.searchFather(value);
			}
			else if (value >= this.value && this.right != null)
			{
				//右边递归
				return this.right.searchFather(value);
			}
			else 
				return null;
		}
	}

	//-----------------------------------以下是为了实现二叉平衡树定义的方法----------------------------------------
	//求出当前结点所包含的子树的高度
	public int getHeight()
	{
		return Math.max(this.left == null ? 0 : this.left.getHeight(), this.right == null ? 0 : this.right.getHeight()) + 1;
	}
	//求当前结点的左子树高度
	public int leftHeight()
	{
		return this.left == null ? 0 : this.left.getHeight();
	}
	//求当前结点的右子树的高度
	public int rightHeight()
	{
		return this.right == null ? 0 : this.right.getHeight();
	}
	//左旋转方法
	public void leftRotate()
	{
		//旋转的规则：
		//左旋转即向左旋转
		//首先定义新结点
		Node newNode = new Node(this.value);
		//将新结点的左指针指向原来的左子结点
		newNode.setLeft(this.left);
		//将新结点的右指针指向原来的右子节点的左子结点
		newNode.setRight(this.right.getLeft());
		//将右子结点左移到root
		this.value = this.right.getValue();
		//设置当前结点的左子结点是新结点
		this.setLeft(newNode);
		//设置当前结点的右子树为right.right
		this.setRight(this.right.getRight());
	}
	//右旋转方法
	public void rightRotate()
	{
		//旋转的规则：
		//右旋转即向右旋转
		//首先定义新结点
		Node newNode = new Node(this.value);
		//将新结点的右指针指向原来的右子结点
		newNode.setRight(this.right);
		//将新结点的左指针指向原来的左子节点的右子结点
		newNode.setLeft(this.left.getRight());
		//将左子结点右移到root
		this.value = this.left.getValue();
		//设置当前结点的右子结点是新结点
		this.setRight(newNode);
		//设置当前结点的左子树为left.left
		this.setLeft(this.left.getLeft());
	}

	//重写toString()方法
	@Override
	public String toString()
	{
		return "Node[value = " + value + "]";
	}
}