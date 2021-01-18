public class PackageProblem 
{
	/*
	动态规划问题的解决思路类似于分治算法，但是区别在于：
		分治算法分解后得到的小问题一般来说是相互独立的，但是适合用动态规划算法解决的问题分解后的小问题一般不是相互独立的
	*/
	//利用动态规划算法解决背包问题
	/*
	背包问题即如何在给定的背包情况下，将给定的物品组合装进背包，实现最高的价值，这里是01背包（对应完全背包），即各物品最多只能装一个
	如给定物品的重量数组weight[i]，物品的价值数组value[i]， maxV[i][j]表示前i件物品装进容量为j的背包时能获得的最大价值
	那么就会有以下递推公式：
		1、maxV[i][0] = maxV[0][j] = 0
			如果没有物品或者背包容量为0，则价值为0
		2、当 weight[i] > j 时，maxV[i][j] = maxV[i - 1][j];
			如果新添加了物品，但是该物品的单个重量已经大于背包的重量，那么最高价值保留上一次的情况
		3、当 j > weight[i] 时，maxV[i][j] = max{maxV[i - 1][j], maxV[i - 1][j - weight[i]] + value[i]};
			如果新添加的物品是可以放进背包的，那么当前的最大价值分为两种情况：要么新装i还不如之前，则为前者，要么，装了i，剩下空间装i以前的东西为最优解
	*/
	/**
	 *@param weight 物品的重量数组
	 *@param value 物品的价值数组
	 *@param capacity 背包容量
	 *@return 返回装东西的情况，用于得知最优情况装了什么东西
	 */
	public static void solvePackage(int[] weight, int[] value, int capacity)
	{
		//首先定义保存最优价值表的数组
		int[][] maxV = new int[weight.length + 1][capacity + 1];
		//定义包里放了哪些东西，即返回的数组，其中1代表放了，0代表没放
		int[][] path = new int[weight.length + 1][capacity + 1];

		//开始处理
		for (int i = 0; i < maxV.length; i++)
		{
			for (int j = 0; j < maxV[0].length; j++)
			{
				//首先第一个公式，对第一行和第一列赋零
				if (i == 0 || j == 0)
				{
					maxV[i][j] = 0;
					continue;
				}

				//第二三个公式 是 if else 的关系
				if (weight[i - 1] > j)
				{
					//这里因为maxV的行和列都是多了一行0，所以访问weight和value时候下标都得-1
					maxV[i][j] = maxV[i - 1][j];
					//这里其实并没有新加物品，所以不需要修改path
				}
				else
				{
					//这里如果采用maxV[i][j] = Math.max{maxV[i - 1][j], maxV[i - 1][j - weight[i - 1]] + value[i - 1]};
					//会不知道是采用了哪一种最大值，无法输出路径，所以继续使用if else结构
					if (maxV[i - 1][j] >= (maxV[i - 1][j - weight[i - 1]] + value[i - 1]))
					{
						//如果依旧是填了物品i还不如没添加，则不需要i，即依旧没变化
						maxV[i][j] = maxV[i - 1][j];
					}
					else
					{
						//只有这一步才会添加物品i
						maxV[i][j] = maxV[i - 1][j - weight[i - 1]] + value[i - 1];
						path[i][j] = 1;
					}
				}
			}
		}
		System.out.println("背包的总价值为：" + maxV[path.length - 1][path[0].length - 1]);
		//处理结束后打印数据
		//因为前面的物品一定是有很多包含了的，所以需要倒序遍历
		for (int i = path.length - 1; i > 0;)
		{
			for (int j = path[0].length - 1; j > 0;)
			{
				//只打印path[i] == 1的情况
				if (path[i][j] == 1)
				{
					System.out.printf("第%d件物品被装进了背包 \n", i);
					//这里打印了i物品后，背包的容量就得减下去
					j = j - weight[i - 1];
					//j只能这么减，因为总共的背包容量不能动，除非有东西已经打印了，再去减背包重量，找最优解的情况
				}
				//如果当前背包没找到，说明当前i未添加，往回一个物品
				i--;
			}
		}
	}
	public static void main(String[] args) 
	{
		//三件物品 吉他 1 1500；音响 4 3000；电脑 3 2000
		int[] weight = new int[]{1, 4, 3};
		int[] value = new int[]{1500, 3000, 2000};

		//背包容量
		int capacity = 4;

		PackageProblem.solvePackage(weight, value, capacity);
	}
}