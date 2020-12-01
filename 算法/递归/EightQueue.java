import java.util.*;

public class EightQueue 
{
	private final int MAXSIZE = 8;
	private static int okNum = 0;
	private static int allNum = 0;

	//定义一个一维数组来代表棋盘
	//因为每次行数不一样，可以用一维数组的下标位置来表示行数
	//每次皇后的列数可以用数组内部的数值来代表，1-8
	private int[] qiPan = new int[MAXSIZE];

	public static void main(String[] args)
	{
		EightQueue eq = new EightQueue();
		eq.check(0);
		System.out.println("总共有" + okNum + "种可能结果");
		System.out.println("总共遍历了" + allNum + "次");
	}

	//显示当前元素
	public void show()
	{
		okNum++;
		for (var i = 0; i < MAXSIZE; i++)
		{
			System.out.print(qiPan[i] + " ");
		}
		System.out.println();
	}

	//放置第n个皇后
	public void check(int n)
	{
		if (n == MAXSIZE)
		{
			show();
			return;
		}
		
		//------------------------------------------------
		//这里的for循环 保证了 将第一个棋子放在第一行的所有列 以及后续的所有选项，实现递归
		//------------------------------------------------
		//依次放入皇后，并判断是否冲突
		for (int i = 0; i < MAXSIZE; i++)
		{
			//先把当前的皇后n，放在该行第一列
			qiPan[n] = i;
			//判断当前放置的皇后到第i列时，是否冲突
			if (judge(n))
			{
				//不冲突
				//接着放置n+1皇后，即开始递归
				check(n + 1);
			}
			else
			{
				//如果冲突了，继续执行array[n] = i;
				//即将第n个皇后，放置在本行的后移一个位置
			}
		}
	}


	//判断要放置的第n个皇后的位置符不符合规定
	/**
	 *@param n 表示第n个皇后
	 */
	public boolean judge(int n)
	{
		allNum++;
		//看第n个棋子之前的棋子和该位置冲不冲突
		for (var i =  0; i < n; i++)
		{
			if (qiPan[i] == qiPan[n] ||								//处于同一列，因为数组下标代表行数，所以绝对不会在同一行
				Math.abs(n - i) == Math.abs(qiPan[i] - qiPan[n]))	//处于同一斜线
			{
				return false;
			}
		}
		return true;
	}
}
