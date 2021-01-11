public class BinarySortTreeDemo 
{
	public static void main(String[] args) 
	{
		int[] arr = new int[]{7, 3, 10, 12, 5, 1, 9, 2};
		
		BinarySortTree bst = new BinarySortTree();

		//创建一个二叉排序树
		for (int ele : arr)
		{
			bst.add(new Node(ele));
		}

		//中序遍历输出
		System.out.println("中序遍历：");
		bst.suffixOrder();

		//测试删除叶子结点
		//bst.delNode(2);
		//bst.delNode(5);
		//bst.delNode(9);
		//bst.delNode(12);
		//测试删除有一个子树的结点
		//bst.delNode(1);
		//测试删除有两个子树的结点
		//bst.delNode(3);
		//bst.delNode(10);
		bst.delNode(7);

		//中序遍历输出
		System.out.println("删除后的：");
		bst.suffixOrder();

	}
}

//二叉排序树的类
class BinarySortTree
{
	private Node root;

	//参数获取方法
	public Node getRoot()
	{
		return this.root;
	}
	//构造器
	public BinarySortTree(){}
	public BinarySortTree(Node root)
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

	//实现二叉排序树的添加结点方法
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

	//重写toString()方法
	@Override
	public String toString()
	{
		return "Node[value = " + value + "]";
	}
}