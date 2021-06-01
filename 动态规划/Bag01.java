/*
	01背包问题：给定物品的重量和价值，如何将最高价值的东西装进背包中   01代表只能选择 装或者不装
	因为需要考虑 物品的数量 M 和背包的重量 W 所以需要一个二维数组
	1.定义原问题与子问题：
		原问题：将 M 件物品装进容量为 W 的背包 的最高价值
		子问题：将 i(i<M) 件物品装进容量为 j(j<W) 的背包能获取的最高价值
	2.状态变量：i,j
	3.状态转移方程
		边界条件：(i==0 || j == 0) f(i,j) = 0;  所以需要多弄一行一列 
			首先第一行，即i == 1，只有一件东西，如果j < wi，说明装不下，直接置零，否则 f(i,j) = wi;
			其次第一列，即j == 1, 背包重量为1，所以，只要找到前i件物品中最大的那个，f(i,j) = wmax;      递推公式：wi == 1; f(i, j) = max(f(i - 1, j), pi)
																												  wi > 1; f(i, j) = f(i - 1, j)
		一般情况			f(i - 1, j)										,wi > j 首先考虑能不能把i放进去，如果背包根本装不进去
				  f(i,j) =  max(pi, f(i - 1, j)) 							,wi == j 考虑 只装i 和 不装i 哪种好 
							max(f(i - 1, j - i) + pi, f(i - 1, j))			,wi < j 考虑 装i 和 不装i 哪种好 
*/
import java.util.*;

public class Bag01 
{
	public static void main(String[] args) 
	{
		//定义价格 和 重量 数组
		int[] price = new int[]{540, 200, 180, 350, 60, 150, 280, 450, 320, 120};
		int[] weight = new int[]{6, 3, 4, 5, 1, 2, 3, 5, 4, 2};
		int capacity = 30;
		System.out.println(new Bag01().knapsack01problem(weight, price, capacity));
	}

	/**
	 *@param weight 货物的重量数组
	 *@param price 货物的价值
	 *@param capacity 背包容量
	 *@return 返回最大价值
	 */
	public int knapsack01problem(int[] weight, int[] price, int capacity)
	{
		//首先定义二维数组 i 是物品数量，j是背包容量
		int[][] f = new int[weight.length + 1][capacity + 1];
		//当i == 0 和 j == 0 的时候不需要管， 初始化是零
		//注意 i 和 j对应大一位，所以 读取原始数据 weight 和 price 的时候需要 -1
		for (int i = 1; i <= weight.length; i++)
		{
			for (int j = 1; j <= capacity; j++)
			{
				//依次考虑情况
				if(i == 1)
				{
					//只有一件东西，看能不能装进去
					if(weight[0] <= j)
						f[i][j] = price[0];
					else
						//放不进去
						f[i][j] = 0;
					continue;
				}
				if (j == 1)
				{
					//递推公式
					if (weight[i - 1] == 1)
						f[i][j] = Math.max(f[i - 1][j], price[i - 1]);
					else
						f[i][j] = f[i- 1][j];
					continue;
				}
				//这里就是一般情况了
				if(weight[i - 1] > j)
					f[i][j] = f[i - 1][j];
				else
					f[i][j] = Math.max(f[i - 1][j - weight[i - 1]] + price[i - 1], f[i - 1][j]);
			}
		}
		
		System.out.println("构建的二维数组为：");
		//输出一下数组结果
		for (int[] ff : f)
		{
			for (int ele : ff)
			{
				System.out.printf("%5d", ele);
			}
			System.out.println();
		}
		
		//输出到底装了哪些东西
		ArrayList<Integer> info = showInfo(weight, price, f, f[weight.length][capacity]);
		System.out.println(info.toString());

		//返回结果
		return f[weight.length][capacity];
	}
	
	/**
	 *这里主要思路是：两种方案
		1、首先找到对应背包的最后一列，找到第一行最大值（即以后增加物品数量，价值不变，那么就说明了以后的东西没用） 每次更新参数
		2、利用递推公式，（原来数据需要 - 1）
			比较   f[i - 1][j - weight[i - 1]] + price[i - 1], f[i - 1][j] 哪一个大，对应的就是装进去的，那么就是说，对应的（i - 1）就是用到的物品
	 *@param weight 货物的重量数组
	 *@param price 货物的价值
	 *@param f 对应的二维DP数组
	 *@param maxPrice 对应的最大价值
	 */
	public ArrayList<Integer> showInfo(int[] weight, int[] price, int[][] f, int maxPrice)
	{
		ArrayList<Integer> info = new ArrayList<>();
		//先找到第一行最大值
		int i = f.length - 1;
		int j = f[0].length - 1;

		//将对应的保存
		while (maxPrice > 0)
		{
			//比较哪一个大
			while (f[i - 1][j] == f[i][j])
			{
				i--;
			}
			//找到了出现的第一个最大值，保存
			info.add(i);
			//更新
			maxPrice -= price[i - 1];
			j -= weight[i - 1];
			i--;

		}
		info.sort((o1, o2) ->
		{
			return o1 - o2;
		});
		return info;
	}
	public ArrayList<Integer> showInfo2(int[] weight, int[] price, int[][] f, int maxPrice)
	{
		ArrayList<Integer> info = new ArrayList<>();
		//先找到第一行最大值
		int i = f.length - 1;
		int j = f[0].length - 1;

		//将对应的保存
		while (maxPrice > 0)
		{
			//比较哪一个大
			if ((f[i - 1][j - weight[i - 1]] + price[i - 1]) > f[i - 1][j])
			{
				//说明装了i是大的
				info.add(i);
				maxPrice -= price[i - 1];
				//先读取数据再操作1
				j -= weight[i - 1];
				i--;
			}
			else
			{
				//说明没有用i，那么此时是 i - 1， j比较大，但不能确定用没用 i - 1，继续
				i--;
			}
		}
		info.sort((o1, o2) ->
		{
			return o1 - o2;
		});
		return info;
	}
}