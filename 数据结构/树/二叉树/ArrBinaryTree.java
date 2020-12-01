/*
以数组的形式来实现二叉树，遍历时采用前，中，后序三种方式遍历

原理：(完全二叉树)
	假设二叉树的下表从左到右，下表从0开始
	1.第 n 个节点的左节点为 2 * n + 1
	2.第 n 个节点的右节点为 2 * n + 2
	3.第 n 个节点的父节点为 (n - 1) / 2
*/

public class ArrBinaryTree 
{
	private int[] arr;

	//构造器
	public ArrBinaryTree(int[] arr)
	{
		this.arr = arr;
	}

	//**************************************************************************
	//重载三种遍历，避免输入index
	//**************************************************************************
	public void preOrder()
	{
		System.out.println("前序遍历结果：");
		this.preOrder(0);
		System.out.println();
	}
	public void infixOrder()
	{
		System.out.println("中序遍历结果：");
		this.infixOrder(0);
		System.out.println();
	}
	public void postOrder()
	{
		System.out.println("后序遍历结果：");
		this.postOrder(0);
		System.out.println();
	}
	
	//**************************************************************************
	//三种遍历方法
	//**************************************************************************
	//前序遍历方法
	public void preOrder(int index)
	{
		//首先安全性判断，如果数组为空或者数组长度为零
		if (arr == null || arr.length == 0)
		{
			System.out.println("数组为空，无法遍历");
			return;
		}

		//开始前序遍历，首先输出当前元素
		System.out.printf("arr[%d] = %d  ", index, arr[index]);

		//递归左边
		if (index * 2 + 1 < arr.length)
		{
			preOrder(index * 2 + 1);
		}

		//递归右边
		if (index * 2 + 2 < arr.length)
		{
			preOrder(index * 2 + 2);
		}
	}
	//中序遍历方法
	public void infixOrder(int index)
	{
		//首先安全性判断，如果数组为空或者数组长度为零
		if (arr == null || arr.length == 0)
		{
			System.out.println("数组为空，无法遍历");
			return;
		}

		//开始中序遍历，首先递归左边
		if (index * 2 + 1 < arr.length)
		{
			infixOrder(index * 2 + 1);
		}

		//输出当前元素
		System.out.printf("arr[%d] = %d  ", index, arr[index]);

		//递归右边
		if (index * 2 + 2 < arr.length)
		{
			infixOrder(index * 2 + 2);
		}
	}
	//后序遍历方法
	public void postOrder(int index)
	{
		//首先安全性判断，如果数组为空或者数组长度为零
		if (arr == null || arr.length == 0)
		{
			System.out.println("数组为空，无法遍历");
			return;
		}

		//开始前序遍历，首先递归左边
		if (index * 2 + 1 < arr.length)
		{
			postOrder(index * 2 + 1);
		}

		//递归右边
		if (index * 2 + 2 < arr.length)
		{
			postOrder(index * 2 + 2);
		}

		//输出当前元素
		System.out.printf("arr[%d] = %d  ", index, arr[index]);
	}

	public static void main(String[] args) 
	{
		/*
		 二叉树结构 ：               1
								  2      3
								 4 5    6 7
								8 9
		*/
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		ArrBinaryTree arrTree = new ArrBinaryTree(arr);

		//前序遍历结果 1 2 4 8 9 5 3 6 7
		arrTree.preOrder();

		//中序遍历结果 8 4 9 2 5 1 6 3 7
		arrTree.infixOrder();

		//后序遍历结果 8 9 4 5 2 6 7 3 1
		arrTree.postOrder();
	}
}