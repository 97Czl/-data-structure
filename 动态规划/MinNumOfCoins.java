/*
	零钱兑换问题：
	给定不同面值的硬币如：[1, 2, 5] 给定总金额S = 11 计算可以凑成所需金额的最少硬币个数

	1.定义原问题和子问题
		子问题：凑成x(x < S)金额的需要最少的硬币个数
	2.定义状态
		x
	3.定义状态转移方程
		设定面值为 [c1, c2, ... cm]
		一般情况：	f(x) = min{f(x - c1), f(x - c2),..., f(x - cm)} + 1;
		规定：x-ci < 0 代表所需总金额小于硬币的面值，设定f(x - ci) = +无穷大 剔除出去
			  x-ci == 0 代表现在需要的金额刚好等于一个硬币，所以 置零， 即去除本身之后，需要的是0个硬币
*/

import java.util.Arrays;

public class MinNumOfCoins 
{
	public static void main(String[] args) 
	{
		int[] coins = new int[]{1, 2, 5};
		System.out.println(new MinNumOfCoins().getMinNum(coins, 11));
	}


	//计算的方法
	public int getMinNum(int[] coins, int price)
	{
		if (price < 0)
		{
			//如果硬币大于了总金额  就没必要计算了
			return Integer.MAX_VALUE;
		}
		else if (price == 0)
		{
			//当时的硬币刚好就是所需金额，那么剩余金额就是0
			return 0;
		}
		else
		{
			//多两个  一个是为了保证下标和状态对应  一个位置是绑定负状态
			int[] minNums = new int[price + 2];
			minNums[price + 1] = Integer.MAX_VALUE;
			//一般情况，需要找到前驱的一个最小值
			for (int k = 1; k <= price; k++)
			{
				//每一次更新状态
				int minNum = Integer.MAX_VALUE;
				for (int i = 0; i < coins.length; i++)
				{
					//定义当前状态的最小硬币数量
					int fi;
					//如果索引为负，指到最后一位
					if (k - coins[i] < 0)
						fi = minNums[price + 1];
					else
					    fi = minNums[k - coins[i]];
					if (fi == Integer.MAX_VALUE)
						break;
					if (minNum > fi)
					{
						minNum = fi;
					}
				}
				//更新结果
				if (minNum == Integer.MAX_VALUE)
					minNums[k] = minNum;
				else 
					minNums[k] = minNum + 1;
			}

			//更新结果
			if (minNums[price] == Integer.MAX_VALUE)
				return -1;
			else 
			{
				showResult(coins, minNums, price);
				showResult2(coins, minNums, price);
				return minNums[price];
			}
		}
	}

	/**
	 *输出是哪些硬币的组合
	 *@param coins 对应的硬币数组
	 *@param minNums 对应的最小数量数组
	 *@param price 价格
	 */
	public void showResult(int[] coins, int[] minNums, int price)
	{
		//定义一个记录信息的数组
		int[] info = new int[minNums[price]];
		int index = 0;
		//主要思想是根据上面的方法倒推，去掉每一个硬币以后，剩余的价值对应的最少数量如果是 原来的数量 - 1 那么说明就是正确的路径
		while (price > 0)
		{
			//依次计算当前的数量
			for (int coin : coins)
			{
				if (minNums[price - coin] == (minNums[price] - 1))
				{
					//如果满足条件，保存并更新
					info[index++] = coin;
					price -= coin;
					break;
				}
			}
		}
		//最后输出
		System.out.println(Arrays.toString(info));
	}
	
	//判断去掉一个硬币以后哪一个的最小数即可
	public void showResult2(int[] coins, int[] minNums, int price)
	{
		//定义一个记录信息的数组
		int[] info = new int[minNums[price]];
		int index = 0;
		//主要思想是根据上面的方法倒推，去掉每一个硬币以后，剩余的价值对应的最少数量如果是 原来的数量 - 1 那么说明就是正确的路径
		while (price > 0)
		{
			int min = Integer.MAX_VALUE;
			int minCoin = Integer.MAX_VALUE;
			//依次计算当前的数量
			for (int coin : coins)
			{
				//或者直接保存最小的即可
				if (minNums[price - coin] < min)
				{
					min = minNums[price - coin];
					minCoin = coin;
				}
			}
			//保存
			info[index++] = minCoin;
			price -= minCoin;
		}
		//最后输出
		System.out.println(Arrays.toString(info));
	}
}
