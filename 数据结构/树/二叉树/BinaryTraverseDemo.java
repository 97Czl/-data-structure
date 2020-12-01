public class BinaryTraverseDemo 
{
	public static void main(String[] args) 
	{
		//创建树中存储的几个节点
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode hero2 = new HeroNode(2, "吴用");
		HeroNode hero3 = new HeroNode(3, "卢俊义");
		HeroNode hero4 = new HeroNode(4, "林冲");
		HeroNode hero5 = new HeroNode(5, "关胜");

		//创建一个二叉树
		BinaryTree tree = new BinaryTree(root);

		//构建一个 宋江是根节点，左边吴用，右边卢俊义，  卢俊义左右分别为 关胜和林冲的二叉树
		root.setLeft(hero2);
		root.setRight(hero3);
		hero3.setLeft(hero5);
		hero3.setRight(hero4);


		//首先前序遍历，结果应该是  1，2，3，5，4
		System.out.println("前序遍历：");
		tree.preOrder();

		//然后中序遍历，结果应该是  2，1，5，3，4
		System.out.println("中序遍历：");
		tree.infixOrder();

		//最后后序遍历，结果应该是  2，5，4，3，1
		System.out.println("前序遍历：");
		tree.postOrder();
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
}