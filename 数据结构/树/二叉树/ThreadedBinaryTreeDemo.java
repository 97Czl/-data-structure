/*线索化二叉树   （针对）
要求：	1)线索化的二叉树 n 个节点的二叉树中含有 n + 1(公式 2n - (n - 1) = n + 1)个空指针域，
		2)为了将其利用起来，将空指针指向前驱和后继节点
		3)同时需要标识符，左右节点标识符为 0 代表左右子树， 1 代表前驱或者后继节点
		4)需要一个pre来记录前一个节点，否则无法实现前驱节点的连接

思路：
		1)按照正常遍历顺序，如果当前节点的左右指针空，则引入前驱和后继节点
		2)递归实现
		3)由于前序遍历的特殊性，父节点为左(可能为右)子节点的pre，
		所以需要格外注意已经设置了前驱或者后继节点的端口就不能继续遍历了，否则会陷入死循环

遍历：
		1)一直遍历直到某个节点的right为后继节点，则可以链式输出

*/

public class ThreadedBinaryTreeDemo 
{
	public static void main(String[] args) 
	{
		//创建树中存储的几个节点
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode hero2 = new HeroNode(3, "吴用");
		HeroNode hero3 = new HeroNode(6, "卢俊义");
		HeroNode hero4 = new HeroNode(8, "林冲");
		HeroNode hero5 = new HeroNode(10, "关胜");
		HeroNode hero6 = new HeroNode(14, "时迁");

		//创建一个二叉树
		ThreadedBinaryTree tree = new ThreadedBinaryTree(root);

		//构建一个 宋江是根节点，左边吴用，右边卢俊义，  卢俊义左右分别为 关胜和林冲的二叉树
		root.setLeft(hero2);
		root.setRight(hero3);
		hero2.setLeft(hero4);
		hero2.setRight(hero5);
		//hero3.setLeft(hero6);
		hero3.setRight(hero6);
		
		/*
		//前序线索化：
		tree.threadPre();
		System.out.println("节点8的左节点为：" + hero4.getLeft());
		System.out.println("节点8的右节点为：" + hero4.getRight());
		System.out.println("节点10的左节点为：" + hero5.getLeft());
		System.out.println("节点10的右节点为：" + hero5.getRight());
		System.out.println("节点14的左节点为：" + hero6.getLeft());
		System.out.println("节点14的右节点为：" + hero6.getRight());
		System.out.println("节点6的左节点为：" + hero3.getLeft());
		System.out.println("节点6的右节点为：" + hero3.getRight());
		*/

		/*
		//中序线索化：
		tree.threadInfix();
		System.out.println("节点8的左节点为：" + hero4.getLeft());
		System.out.println("节点8的右节点为：" + hero4.getRight());
		System.out.println("节点10的左节点为：" + hero5.getLeft());
		System.out.println("节点10的右节点为：" + hero5.getRight());
		System.out.println("节点14的左节点为：" + hero6.getLeft());
		System.out.println("节点14的右节点为：" + hero6.getRight());
		System.out.println("节点6的左节点为：" + hero3.getLeft());
		System.out.println("节点6的右节点为：" + hero3.getRight());
		*/

		/*
		//后序线索化：
		tree.threadPost();
		System.out.println("节点8的左节点为：" + hero4.getLeft());
		System.out.println("节点8的右节点为：" + hero4.getRight());
		System.out.println("节点10的左节点为：" + hero5.getLeft());
		System.out.println("节点10的右节点为：" + hero5.getRight());
		System.out.println("节点14的左节点为：" + hero6.getLeft());
		System.out.println("节点14的右节点为：" + hero6.getRight());
		System.out.println("节点6的左节点为：" + hero3.getLeft());
		System.out.println("节点6的右节点为：" + hero3.getRight());
		*/
		//tree.postOrder();

		//前序线索化：
		//tree.threadPre();
		//tree.threadPreTraverse();
		//中序线索化：
		tree.threadInfix();
		tree.threadInfixTraverse();
		//后序线索化：
		//tree.threadPost();
	}
}

//线索化二叉树
class ThreadedBinaryTree
{
	private HeroNode root;
	//记录前一个节点
	private HeroNode pre;

	public ThreadedBinaryTree(HeroNode root)
	{
		this.root = root;
	}

	//***********************************************************************************************
	//以下是二叉树的三种线索化的方法
	//***********************************************************************************************
	//二叉树前序线索化方法
	public boolean threadPre()
	{
		if (this.root != null)
		{
			threadPre(root);
			System.out.println("前序线索化成功！");
			return true;
		}
		else 
		{
			System.out.println("前序线索化失败！");
			return false;
		}
	}
	private void threadPre(HeroNode node)
	{
		//安全性判断
		if (node == null)
		{
			return;
		}

		//前序顺序 当前节点 -> 左节点遍历 -> 右节点遍历
		
		/*
		处理当前节点
		*/
		//当前节点的左边，即处理前驱节点
		if (node.getLeft() == null)
		{
			//左子节点为空，设置为前驱节点
			node.setLeft(pre);
			//说明左节点不是左子树，设置状态
			node.setLeftStatus(false);
		}
		//上一个节点的右边，处理的是上一个的后继节点，因为无法知道当前节点的遍历顺序的下一个
		if (pre != null &&  pre.getRight() == null)  //pre为空 代表第一次进来root的左节点和右节点都为空
		{
			//在处理当前节点时，如果上一个节点的右节点还是空的，就要设置后继节点
			pre.setRight(node);
			//说明右节点不是左子树，设置状态
			pre.setRightStatus(false);
		}
		
		//更新pre
		pre = node;
		
		/*
		遍历左节点
		*/
		//不能让 左节点 已经连接前驱节点还是参与遍历
		if (node.getLeftStatus())
		{
			//左节点遍历
			threadPre(node.getLeft());
		}
		
		/*
		遍历右节点
		*/
		//不能让 右节点 已经连接后继节点还是参与遍历
		if (node.getRightStatus())
		{
			//右节点遍历
			threadPre(node.getRight());
		}
	}
	//中序线索化
	public boolean threadInfix()
	{
		if (this.root != null)
		{
			threadInfix(root);
			System.out.println("中序线索化成功！");
			return true;
		}
		else 
		{
			System.out.println("中序线索化失败！");
			return false;
		}
	}
	private void threadInfix(HeroNode node)
	{
		//安全性判断
		if (node == null)
		{
			return;
		}

		//中序顺序 左节点遍历 -> 当前节点 -> 右节点遍历
		
		/*
		左节点遍历
		*/
		threadInfix(node.getLeft());
		
		/*
		处理当前节点
		*/
		//当前节点的左边，即处理前驱节点
		if (node.getLeft() == null)
		{
			//左子节点为空，设置为前驱节点
			node.setLeft(pre);
			//说明左节点不是左子树，设置状态
			node.setLeftStatus(false);
		}
		//上一个节点的右边，处理的是上一个的后继节点，因为无法知道当前节点的遍历顺序的下一个
		if (pre != null &&  pre.getRight() == null)  //pre为空 代表第一次进来root的左节点和右节点都为空
		{
			//在处理当前节点时，如果上一个节点的右节点还是空的，就要设置后继节点
			pre.setRight(node);
			//说明右节点不是左子树，设置状态
			pre.setRightStatus(false);
		}
		
		//更新pre
		pre = node;

		/*
		右节点遍历
		*/
		threadInfix(node.getRight());
	}
	//后序线索化
	public boolean threadPost()
	{
		if (this.root != null)
		{
			threadPost(root);
			System.out.println("后序线索化成功！");
			return true;
		}
		else 
		{
			System.out.println("后序线索化失败！");
			return false;
		}
	}
	private void threadPost(HeroNode node)
	{
		//安全性判断
		if (node == null)
		{
			return;
		}

		//中序顺序 左节点遍历 -> 右节点遍历 -> 当前节点
		
		/*
		左节点遍历
		*/
		threadPost(node.getLeft());
		/*
		右节点遍历
		*/
		threadPost(node.getRight());

		/*
		处理当前节点
		*/
		//当前节点的左边，即处理前驱节点
		if (node.getLeft() == null)
		{
			//左子节点为空，设置为前驱节点
			node.setLeft(pre);
			//说明左节点不是左子树，设置状态
			node.setLeftStatus(false);
		}
		//上一个节点的右边，处理的是上一个的后继节点，因为无法知道当前节点的遍历顺序的下一个
		if (pre != null &&  pre.getRight() == null)  //pre为空 代表第一次进来root的左节点和右节点都为空
		{
			//在处理当前节点时，如果上一个节点的右节点还是空的，就要设置后继节点
			pre.setRight(node);
			//说明右节点不是左子树，设置状态
			pre.setRightStatus(false);
		}
		
		//更新pre
		pre = node;
	}

	//***********************************************************************************************
	//以下是线索二叉树的三种遍历的方法
	//***********************************************************************************************
	//前序线索化二叉树的遍历方法
	public boolean threadPreTraverse()
	{
		if (this.root != null)
		{
			HeroNode node = root;
			//用while循环来实现遍历
			while (node != null)
			{
				//如果是前序遍历，先从当前节点开始输出当前节点
				/*
				打印当前节点
				*/
				System.out.println(node);
				
				/*
				处理左边的节点,这里左边节点只需要考虑正常子节点，如果是前驱节点不去处理即可
				*/
				//左边节点如果不是前驱节点
				if (node.getLeftStatus())
				{
					node = node.getLeft();
					continue;
				}

				//右边节点如果是后继节点，则可以一直遍历输出，因为后继节点必定是指向下一个遍历节点的
				while (!node.getRightStatus())
				{
					node = node.getRight();
					System.out.println(node);
				}
				
				//更新节点
				//这里是排除 左节点为空（指向了前驱节点），只有右节点的情况（可以看出上面流程 没有考虑到右子节点是正常节点怎么办，相当于逻辑还漏了一块）
				//如果执行到这一步，说明当前节点的左子节点指向了前驱节点，右子节点也不是后继节点，则说明右子节点是正常节点，且左子节点没东西
				//所以下一个节点就是当前节点的右子节点了
				node = node.getRight();
			}
			System.out.println("前序线索化二叉树遍历成功！");
			return true;
		}
		else 
		{
			System.out.println("前序线索化二叉树遍历失败！");
			return false;
		}
	}
	//中序线索化二叉树的遍历方法
	public boolean threadInfixTraverse()
	{
		if (this.root != null)
		{
			HeroNode node = root;
			while (node != null)
			{
				//中序遍历从最左边开始，所以首先找到最左边的元素，

				/*
				处理左边的节点,这里对于每一个节点 首先得找到最左边的子节点开始遍历
				*/
				//一直找到 节点左边是前驱节点的节点 为止
				while (node.getLeftStatus())
				{
					node = node.getLeft();
					//这里退出时，就应该是最左边的节点，即最开始的遍历节点
					continue;
				}

				/*
				打印当前节点
				*/
				System.out.println(node);

				/*
				处理右边节点，其中右边比较特殊，如果是后继节点，可以一直输出，否则换成右子节点继续循环
				*/
				//然后尝试按照线索输出节点
				while (! node.getRightStatus())
				{
					//打印后继节点
					System.out.println(node.getRight());
					//更新节点
					node = node.getRight();
				}

				//能执行到这一步 根据前面的判断结果 右侧节点是子树，继续迭代即可
				node = node.getRight();	
			}
			System.out.println("中序线索化二叉树遍历成功！");
			return true;
		}
		else 
		{
			System.out.println("中序线索化二叉树遍历失败！");
			return false;
		}
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
}

//二叉树中存储的节点类
class HeroNode
{
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;

	//记录节点的两张状态，默认为true,即默认两边都是 子树，只有线索化的时候会修改状态
	private boolean leftIsTree = true;
	private boolean rightIsTree = true;

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
	public void setLeftStatus(boolean status)
	{
		this.leftIsTree = status;
	}
	public boolean getLeftStatus()
	{
		return this.leftIsTree;
	}
	public void setRightStatus(boolean status)
	{
		this.rightIsTree = status;
	}
	public boolean getRightStatus()
	{
		return this.rightIsTree;
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

	public void count()
	{
		System.out.println("进入比较1次~~~");
	}
}
