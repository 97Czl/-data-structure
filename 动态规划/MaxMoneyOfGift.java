/*
	礼物的最大价值：
	给定一个m*n的数组，表示每一个位置的价值，从左上角出发，每次只能 向右/向下走一步，一直到右下角
	可以看出：确定每一步的最优值是看该位置的上方/左方哪一个值最大，哪一个大就选择哪一个

	1.定义原问题与子问题：
		原问题：从 1,1 到 m,n 的最大价值
		子问题：从 1,1 到 i,j 的最大价值
	2.定义状态
		i,j
	3.定义状态转移方程
				 M11									,i=j=1;
				 f(i-1,j) + Mij							,j=1 && i>1
		f(i,j) = f(i,j-1) + Mij							,i=1 && j>1
				 Max(f(i-1,j), f(i,j-1)) + Mij			,i>1 && j>1
*/
import java.util.*;

public class MaxMoneyOfGift 
{
	public static void main(String[] args) 
	{
		int[][] moneyInfo = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
		System.out.println("该礼物数组对应的最大的价值为：" + new MaxMoneyOfGift().getMaxMoneyRecusion(moneyInfo));
		System.out.println("该礼物数组对应的最大的价值为：" + new MaxMoneyOfGift().getMaxMoneyFront(moneyInfo));
	}

	public int getMaxMoneyRecusion(int[][] moneyInfo)
	{
		int m = moneyInfo.length;
		int n = moneyInfo[0].length;

		int[][] maxMoney = new int[m][n];

		return getMaxMoney(moneyInfo, m - 1, n - 1, maxMoney);
	}
	

	//获取最大价值，从后往前递归型
	private int getMaxMoney(int[][] moneyInfo, int i, int j, int[][] maxMoney)
	{
		if(i == 0 && j == 0)
		{
			//左上角
			maxMoney[i][j] = moneyInfo[0][0];
			return maxMoney[i][j];
		}
		else if (i == 0 && j > 0)
		{
			//第一行的位置，只能选择左边的
			maxMoney[i][j] = getMaxMoney(moneyInfo, i, j - 1, maxMoney) + moneyInfo[i][j];
			return maxMoney[i][j];
		}
		else if (j == 0 && i > 0)
		{
			//第一列的位置，只能选择上面的
			maxMoney[i][j] = getMaxMoney(moneyInfo, i - 1, j, maxMoney) + moneyInfo[i][j];
			return maxMoney[i][j];
		}
		else
		{
			//普通位置，取决于上面的和左边的哪一个大
			maxMoney[i][j] = Math.max(getMaxMoney(moneyInfo, i - 1, j, maxMoney), getMaxMoney(moneyInfo, i, j - 1, maxMoney)) + moneyInfo[i][j];
			
			//如果是最后的位置，输出
			if(i == moneyInfo.length - 1 && j == moneyInfo[0].length - 1)
			{
				//输出
				int[][] paths = getPath(moneyInfo, maxMoney);
				for (int[] path : paths)
				{
					System.out.println(Arrays.toString(path));
				}
			}
			return maxMoney[i][j];
		}
	}

	//获取最大价值，从前往后直接计算型
	private int getMaxMoneyFront(int[][] moneyInfo)
	{
		int m = moneyInfo.length;
		int n = moneyInfo[0].length;
		//判断边界条件
		if (m == 0 & n == 0)
		{
			return 0;
		}
		if (m == 1 & n == 1)
		{
			return moneyInfo[1][1];
		}

		int[][] maxMoney = new int[m][n];
		
		//初始化第一行和第一列的内容
		maxMoney[0][0] = moneyInfo[0][0];
		for (int j = 1; j < n; j++)
		{
			maxMoney[0][j] = maxMoney[0][j - 1] + moneyInfo[0][j];
		}
		for (int i = 1; i < m; i++)
		{
			maxMoney[i][0] = maxMoney[i - 1][0] + moneyInfo[i][0];
		}
		
		//计算后面的普通节点
		for (int i = 1; i < m; i++)
		{
			for (int j = 1; j < n; j++)
			{
				maxMoney[i][j] = Math.max(maxMoney[i - 1][j], maxMoney[i][j - 1]) + moneyInfo[i][j];
			}
		}
		//输出
		//int[][] paths = getPath(moneyInfo, maxMoney);
		int[][] paths = getPath(maxMoney);
		for (int[] path : paths)
		{
			System.out.println(Arrays.toString(path));
		}
		return maxMoney[m - 1][n - 1];
	}

	//输出选择路径有两种方法 
	//	1.判断当前位置的最高价值 减去 当前的价值 等于 左边/上面   即 公式
	//	2.直接判断 左/上 哪一个位置的价值最高即可
	public int[][] getPath(int[][] moneyInfo, int[][] maxMoney)
	{
		//定义数组
		int m = moneyInfo.length;
		int n = moneyInfo[0].length;

		int[][] path = new int[m][n];
		//从最后一个位置开始，判断
		for (int i = m - 1, j = n - 1; ;)
		{
			path[i][j] = 1;
			if (i == 0 && j == 0)
				break;
			//判断是否在边边上
			if (i == 0)
			{
				--j;
			}
			else if (j == 0)
			{
				--i;
			}
			else if (maxMoney[i][j] - moneyInfo[i][j] == maxMoney[i - 1][j])
			{
				--i;
			}
			else
			{
				--j;
			}
		}
		return path;
	}
	public int[][] getPath(int[][] maxMoney)
	{
		//定义数组
		int m = maxMoney.length;
		int n = maxMoney[0].length;

		int[][] path = new int[m][n];
		//从最后一个位置开始，判断
		for (int i = m - 1, j = n - 1; ;)
		{
			path[i][j] = 1;
			if (i == 0 && j == 0)
				break;
			//判断是否在边边上
			if (i == 0)
			{
				--j;
			}
			else if (j == 0)
			{
				--i;
			}
			//判断哪一个大就逆向选择哪一条路
			else if (maxMoney[i - 1][j] > maxMoney[i][j - 1])
			{
				--i;
			}
			else
			{
				--j;
			}
		}
		return path;
	}
}
