import java.util.*;

public class DivideAndConquer 
{
	//分治算法的主要思想是将一个要解决的问题分解成小问题
	//小问题的解决方案和大问题相同，且小问题的解合起来就可以得到大问题的解，则可以使用算法
	//否则继续递归分解

	public static void main(String[] args) 
	{
		System.out.println("汉诺塔测试！！！");
		hanoiTower(new Scanner(System.in).nextInt(), 'A', 'B', 'C');
	}

	//这里是举了分治算法的一个实例应用  汉诺塔、
	/**
	 *@param num 汉诺塔的层数，
	 *@param a 每次目标移动的时候的起始位置
	 *@param b 每一次移动时不参与移动的位置
	 *@param c 每一次移动的终点位置
	 */
	public static void hanoiTower(int num, char a, char b, char c)
	{
		//汉诺塔每一次都可以只看成数量为1  则将该层塔从a移动到c
		//同样如果是两层，则先将除了最底层的上面一层先从a移动到b，然后将最底层从a移动到c，最后将除了底层的上面一层从b移动到c
		//这样逻辑就出来了，无论汉诺塔具体有多少层。都可以看做两层， 最低层和上面的所有层
		if (num == 1)
		{
			//如果只有一层。那么只需要将a处的移动到c即可
			System.out.println("第1层的盘从" + a + "移动到" + c);
		}
		else
		{
			//分解问题，即递归
			//每一次操作是一样的

			//1.将除了最底层的上面所有层先从a移动到b
			hanoiTower(num - 1, a, c, b);
			//2.将最底层从a移动到c
			System.out.println("第" + num + "层的盘从" + a + "移动到" + c);
			//3.最后将除了底层的上面所有层从b移动到c
			hanoiTower(num - 1, b, a, c);
		}
	}
}
