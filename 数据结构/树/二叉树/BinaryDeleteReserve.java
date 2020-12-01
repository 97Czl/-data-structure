/*
要求：	1)如果删除的节点是叶子节点，则删除该节点
		2)如果删除的节点是非叶子节点
		 2.1)如果该非叶子节点A只有一个子节点B，则子节点B替代节点A
		 2.2)如果该非叶子节点A有左子节点B和右子节点C，则让左子节点B替代节点A



思路：
	1、首先，判断该树是否为空，若为空，直接返回false，否则判断根节点是否是要删除的节点
	2、如果根节点是要删除的节点，将根节点置为null，返回true，否则以根节点作为当前节点，开始递归3-6；
	3、如果当前节点的左节点不为空且为要删除的节点，删除，返回true；
	4、如果当前节点的右节点不为空且为要删除的节点，删除，返回true；
	5、如果当前节点的左节点不为空，将其作为当前节点递归执行3
	6、如果当前节点的右节点不为空，将其作为当前节点递归执行3
*/

public class BinaryDeleteReserve 
{
	public static void main(String[] args) 
	{
		//创建树中存储的几个节点
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode hero2 = new HeroNode(2, "吴用");
		HeroNode hero3 = new HeroNode(3, "卢俊义");
		HeroNode hero4 = new HeroNode(4, "林冲");
		HeroNode hero5 = new HeroNode(5, "关胜");
		HeroNode hero6 = new HeroNode(6, "燕青");
		HeroNode hero7 = new HeroNode(7, "史进");

		//创建一个二叉树
		BinaryTree tree = new BinaryTree(root);

		//构建一个 宋江是根节点，左边吴用，右边卢俊义，  卢俊义左右分别为 关胜和林冲的二叉树
		root.setLeft(hero2);
		root.setRight(hero3);
		hero3.setLeft(hero5);
		hero3.setRight(hero4);
		hero2.setLeft(hero6);
		hero5.setRight(hero7);
		
		int deleteNo = 5;
		

		//前序打印二叉树
		System.out.println("删除前，前序遍历二叉树：");
		tree.preOrder();

		tree.deleteReserve(deleteNo);

		//前序打印二叉树
		System.out.println("删除后，前序遍历二叉树：");
		tree.preOrder();
	}
}

//二叉树
class BinaryTree
{
	private HeroNode root;

	public BinaryTree(HeroNode root)
	{
		this.root = root;
	}
	
	//***********************************************************************************************
	//以下是二叉树的遍历方法
	//***********************************************************************************************
	//调用头节点的前序遍历实现对二叉树的前序遍历
	public void preOrder()
	{
		//首先判断当前的树是否为空，即头节点是否为空
		if (this.root == null)
		{
			System.out.println("树为空，无法遍历");
		}
		else 
		{
			this.root.preOrder();
		}
	}
	//调用头节点的中序遍历实现对二叉树的中序遍历
	public void infixOrder()
	{
		//首先判断当前的树是否为空，即头节点是否为空
		if (this.root == null)
		{
			System.out.println("树为空，无法遍历");
		}
		else 
		{
			this.root.infixOrder();
		}
	}
	//调用头节点的后序遍历实现对二叉树的后序遍历
	public void postOrder()
	{
		//首先判断当前的树是否为空，即头节点是否为空
		if (this.root == null)
		{
			System.out.println("树为空，无法遍历");
		}
		else 
		{
			this.root.postOrder();
		}
	}
	
	//***********************************************************************************************
	//以下是二叉树的查找方法
	//***********************************************************************************************
	//调用头节点的前序查找实现对二叉树的前序查找
	public HeroNode preOrderSearch(int no)
	{
		//首先判断当前的树是否为空，即头节点是否为空
		if (this.root == null)
		{
			System.out.println("树为空，无法查找");
			return null;
		}
		else 
		{
			return this.root.preOrderSearch(no);
		}
	}
	//调用头节点的中序遍历实现对二叉树的中序遍历
	public HeroNode infixOrderSearch(int no)
	{
		//首先判断当前的树是否为空，即头节点是否为空
		if (this.root == null)
		{
			System.out.println("树为空，无法查找");
			return null;
		}
		else 
		{
			return this.root.infixOrderSearch(no);
		}
	}
	//调用头节点的后序遍历实现对二叉树的后序遍历
	public HeroNode postOrderSearch(int no)
	{
		//首先判断当前的树是否为空，即头节点是否为空
		if (this.root == null)
		{
			System.out.println("树为空，无法查找");
			return null;
		}
		else 
		{
			return this.root.postOrderSearch(no);
		}
	}

	//***********************************************************************************************
	//以下是二叉树的删除方法
	//***********************************************************************************************
	public boolean delete(int no)
	{
		//1、首先，判断该树是否为空，若为空，直接返回false，否则判断根节点是否是要删除的节点
		if (root != null)
		{
			//2、如果根节点是要删除的节点，将根节点置为null，返回true，
			if (this.root.getNo() == no)
			{
				System.out.println("序号为" + no + "的节点已删除！");
				this.root = null;
				return true;
			}
			else
			{
				//否则以根节点作为当前节点，开始递归3-6；
				if (root.delete(no))
				{
					//如果删除成功
					System.out.println("序号为" + no + "的节点已删除！");
					return true;
				}
				else
				{
					//如果没有找到节点，即删除失败
					System.out.println("没有找到序号为" + no + "的节点，删除失败！");
					return false;
				}
			}
		}
		else
		{
			//如果是空树
			System.out.println("空树，无法删除元素！");
			return false;
		}
	}

	//***********************************************************************************************
	//以下是二叉树的有保留的删除方法
	//***********************************************************************************************
	public boolean deleteReserve(int no)
	{
		//1、首先，判断该树是否为空，若为空，直接返回false，否则判断根节点是否是要删除的节点
		if (root != null)
		{
			//2、如果根节点是要删除的节点，将根节点置为null，返回true，
			if (this.root.getNo() == no)
			{
				System.out.println("序号为" + no + "的节点已删除！");
				switch (root.countOfSon())
				{
					//无子节点
					case 0:
						root = null;
						break;
					//只有右子节点
					case 2:
						root = root.getRight();
						break;
					//只有左子节点
					default :
						//只有左子节点和两个节点都是保留左边，可以放在一块
						root = root.getLeft();
						break;
				}
				return true;
			}
			else
			{
				//否则以根节点作为当前节点，开始递归3-6；
				if (root.deleteReserve(no))
				{
					//如果删除成功
					System.out.println("序号为" + no + "的节点已删除！");
					return true;
				}
				else
				{
					//如果没有找到节点，即删除失败
					System.out.println("没有找到序号为" + no + "的节点，删除失败！");
					return false;
				}
			}
		}
		else
		{
			//如果是空树
			System.out.println("空树，无法删除元素！");
			return false;
		}
	}
}

//二叉树中存储的节点类
class HeroNode
{
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;

	//构造器，默认左右节点为空
	public HeroNode(int no, String name)
	{
		this.no = no;
		this.name = name;
	}

	//各个参数的设置和获取方法
	public void setNo(int no)
	{
		this.no = no;
	}
	public int getNo()
	{
		return this.no;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setLeft(HeroNode left)
	{
		this.left = left;
	}
	public HeroNode getLeft()
	{
		return this.left;
	}
	public void setRight(HeroNode right)
	{
		this.right = right;
	}
	public HeroNode getRight()
	{
		return this.right;
	}
	
	@Override
	public String toString()
	{
		return "HeroNode[ no = " + no + ", name = " + name + "]";
	}
	
	//***********************************************************************************************
	//以下是二叉树的遍历方法
	//***********************************************************************************************
	//实现二叉树前序遍历的节点方法
	public void preOrder()
	{
		//首先输出当前节点
		System.out.println(this);
		//如果当前节点的左节点不为空，递归实现前序遍历
		if (this.left != null)
		{
			this.left.preOrder();
		}
		//如果当前节点的右节点不为空，递归实现前序遍历
		if (this.right != null)
		{
			this.right.preOrder();
		}
	}

	//实现二叉树中序遍历的节点方法
	public void infixOrder()
	{
		//如果当前节点的左节点不为空，递归实现中序遍历
		if (this.left != null)
		{
			this.left.infixOrder();
		}
		//输出当前节点
		System.out.println(this);
		//如果当前节点的右节点不为空，递归实现中序遍历
		if (this.right != null)
		{
			this.right.infixOrder();
		}
	}

	//实现二叉树后序遍历的节点方法
	public void postOrder()
	{
		//如果当前节点的左节点不为空，递归实现后序遍历
		if (this.left != null)
		{
			this.left.postOrder();
		}
		//如果当前节点的右节点不为空，递归实现后序遍历
		if (this.right != null)
		{
			this.right.postOrder();
		}
		//输出当前节点
		System.out.println(this);
	}

	//***********************************************************************************************
	//以下是二叉树的查找方法
	//***********************************************************************************************
	//实现二叉树前序查找的节点方法
	public HeroNode preOrderSearch(int no)
	{
		count();
		//首先判断当前节点是否满足
		if (this.no == no)
		{
			return this;
		}
		HeroNode result = null;
		//如果当前节点的左节点不为空，递归实现前序查找
		if (this.left != null)
		{
			result = this.left.preOrderSearch(no);
		}
		//判断是否找到。没有则不返回，继续右节点递归
		if (result != null)
		{
			return result;
		}
		//如果当前节点的右节点不为空，递归实现前序查找
		if (this.right != null)
		{
			result = this.right.preOrderSearch(no);
		}
		//无论是否找到，都需要返回
		return result;
	}

	//实现二叉树中序查找的节点方法
	public HeroNode infixOrderSearch(int no)
	{
		HeroNode result = null;
		//如果当前节点的左节点不为空，递归实现中序查找
		if (this.left != null)
		{
			result = this.left.infixOrderSearch(no);
		}
		//判断是否找到。没有则不返回，继续判断中间节点
		if (result != null)
		{
			return result;
		}
		count();
		//判断当前节点是否满足
		if (this.no == no)
		{
			return this;
		}
		//如果当前节点的右节点不为空，递归实现中序查找
		if (this.right != null)
		{
			result = this.right.infixOrderSearch(no);
		}
		//无论是否找到，都需要返回
		return result;
	}

	//实现二叉树后序查找的节点方法
	public HeroNode postOrderSearch(int no)
	{
		HeroNode result = null;
		//如果当前节点的左节点不为空，递归实现后序查找
		if (this.left != null)
		{
			result = this.left.postOrderSearch(no);
		}
		//判断是否找到。没有则不返回，继续判断右边节点
		if (result != null)
		{
			return result;
		}
		//如果当前节点的右节点不为空，递归实现后序查找
		if (this.right != null)
		{
			result = this.right.postOrderSearch(no);
		}
		//判断是否找到。没有则不返回，继续判断当前节点
		if (result != null)
		{
			return result;
		}
		count();
		//判断当前节点是否满足
		if (this.no == no)
		{
			return this;
		}
		//无论是否找到，都需要返回
		return result;
	}

	//***********************************************************************************************
	//以下是二叉树节点的删除方法
	//***********************************************************************************************
	public boolean delete(int no)
	{
		//3、如果当前节点的左节点不为空且为要删除的节点，删除，返回true；
		if (this.left != null && this.left.getNo() == no)
		{
			this.left = null;
			return true;
		}

		//4、如果当前节点的右节点不为空且为要删除的节点，删除，返回true；
		if (this.right != null && this.right.getNo() == no)
		{
			this.right = null;
			return true;
		}

		//5、如果当前节点的左节点不为空，将其作为当前节点递归执行3
		if (this.left != null)
		{
			//如果删除成功
			if (this.left.delete(no))
			{
				return true;
			}
			//如果没有成功还要遍历右边
		}

		//6、如果当前节点的右节点不为空，将其作为当前节点递归执行3
		if (this.right != null)
		{
			//如果删除成功
			if (this.right.delete(no))
			{
				return true;
			}
			//没有成功,则进行最终返回
		}
		
		//如果最终都没有找到要删除的元素，返回false
		return false;
	}

	//***********************************************************************************************
	//以下是二叉树节点的有保留的删除方法
	//***********************************************************************************************
	public boolean deleteReserve(int no)
	{
		//3、如果当前节点的左节点不为空且为要删除的节点，删除，返回true；
		if (this.left != null && this.left.getNo() == no)
		{
			switch (this.left.countOfSon())
			{
				//无子节点
				case 0:
					this.left = null;
					break;
				//只有右子节点
				case 2:
					this.left = this.left.getRight();
					break;
				//只有左子节点
				default :
					//只有左子节点和两个节点都是保留左边，可以放在一块
					this.left = this.left.getLeft();
					break;
			}
			return true;
		}

		//4、如果当前节点的右节点不为空且为要删除的节点，删除，返回true；
		if (this.right != null && this.right.getNo() == no)
		{
			switch (this.right.countOfSon())
			{
				//无子节点
				case 0:
					this.right = null;
					break;
				//只有右子节点
				case 2:
					this.right = this.right.getRight();
					break;
				//只有左子节点
				default :
					//只有左子节点和两个节点都是保留左边，可以放在一块
					this.right = this.right.getLeft();
					break;
			}
			return true;
		}

		//5、如果当前节点的左节点不为空，将其作为当前节点递归执行3
		if (this.left != null)
		{
			//如果删除成功
			if (this.left.deleteReserve(no))
			{
				return true;
			}
			//如果没有成功还要遍历右边
		}

		//6、如果当前节点的右节点不为空，将其作为当前节点递归执行3
		if (this.right != null)
		{
			//如果删除成功
			if (this.right.deleteReserve(no))
			{
				return true;
			}
			//没有成功,则进行最终返回
		}
		
		//如果最终都没有找到要删除的元素，返回false
		return false;
	}

	//***********************************************************************************************
	//以下是二叉树节点判断子节点个数的方法
	/**
	 *@return 返回子节点情况，如果只有左子节点 返回1，如果只有右子节点，返回2，如果有两个节点，返回3，否则返回0
	 */
	public int countOfSon()
	{
		int result = 0;
		if (this.left != null)
		{
			//左边不为空，右边有两种情况
			if (this.right != null)
			{
				//右边也不为空，
				result = 3;
			}
			else 
			{
				//只有左节点
				result = 1;
			}
		}
		else 
		{
			//左边为空，右边如果不为空
			if (this.right != null)
			{
				//右边不为空，
				result = 2;
			}
		}

		return result;
	}

	//***********************************************************************************************
	//以下是二叉树查找的计数方法
	public void count()
	{
		System.out.println("进入比较1次~~~");
	}
}