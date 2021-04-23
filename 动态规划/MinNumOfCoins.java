/*
	��Ǯ�һ����⣺
	������ͬ��ֵ��Ӳ���磺[1, 2, 5] �����ܽ��S = 11 ������Դճ������������Ӳ�Ҹ���

	1.����ԭ�����������
		�����⣺�ճ�x(x < S)������Ҫ���ٵ�Ӳ�Ҹ���
	2.����״̬
		x
	3.����״̬ת�Ʒ���
		�趨��ֵΪ [c1, c2, ... cm]
		һ�������	f(x) = min{f(x - c1), f(x - c2),..., f(x - cm)} + 1;
		�涨��x-ci < 0 ���������ܽ��С��Ӳ�ҵ���ֵ���趨f(x - ci) = +����� �޳���ȥ
			  x-ci == 0 ����������Ҫ�Ľ��պõ���һ��Ӳ�ң����� ���㣬 ��ȥ������֮����Ҫ����0��Ӳ��
*/

import java.util.Arrays;

public class MinNumOfCoins 
{
	public static void main(String[] args) 
	{
		int[] coins = new int[]{1, 2, 5};
		System.out.println(new MinNumOfCoins().getMinNum(coins, 11));
	}


	//����ķ���
	public int getMinNum(int[] coins, int price)
	{
		if (price < 0)
		{
			//���Ӳ�Ҵ������ܽ��  ��û��Ҫ������
			return Integer.MAX_VALUE;
		}
		else if (price == 0)
		{
			//��ʱ��Ӳ�Ҹպþ����������ôʣ�������0
			return 0;
		}
		else
		{
			//������  һ����Ϊ�˱�֤�±��״̬��Ӧ  һ��λ���ǰ󶨸�״̬
			int[] minNums = new int[price + 2];
			minNums[price + 1] = Integer.MAX_VALUE;
			//һ���������Ҫ�ҵ�ǰ����һ����Сֵ
			for (int k = 1; k <= price; k++)
			{
				//ÿһ�θ���״̬
				int minNum = Integer.MAX_VALUE;
				for (int i = 0; i < coins.length; i++)
				{
					//���嵱ǰ״̬����СӲ������
					int fi;
					//�������Ϊ����ָ�����һλ
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
				//���½��
				if (minNum == Integer.MAX_VALUE)
					minNums[k] = minNum;
				else 
					minNums[k] = minNum + 1;
			}

			//���½��
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
	 *�������ЩӲ�ҵ����
	 *@param coins ��Ӧ��Ӳ������
	 *@param minNums ��Ӧ����С��������
	 *@param price �۸�
	 */
	public void showResult(int[] coins, int[] minNums, int price)
	{
		//����һ����¼��Ϣ������
		int[] info = new int[minNums[price]];
		int index = 0;
		//��Ҫ˼���Ǹ�������ķ������ƣ�ȥ��ÿһ��Ӳ���Ժ�ʣ��ļ�ֵ��Ӧ��������������� ԭ�������� - 1 ��ô˵��������ȷ��·��
		while (price > 0)
		{
			//���μ��㵱ǰ������
			for (int coin : coins)
			{
				if (minNums[price - coin] == (minNums[price] - 1))
				{
					//����������������沢����
					info[index++] = coin;
					price -= coin;
					break;
				}
			}
		}
		//������
		System.out.println(Arrays.toString(info));
	}
	
	//�ж�ȥ��һ��Ӳ���Ժ���һ������С������
	public void showResult2(int[] coins, int[] minNums, int price)
	{
		//����һ����¼��Ϣ������
		int[] info = new int[minNums[price]];
		int index = 0;
		//��Ҫ˼���Ǹ�������ķ������ƣ�ȥ��ÿһ��Ӳ���Ժ�ʣ��ļ�ֵ��Ӧ��������������� ԭ�������� - 1 ��ô˵��������ȷ��·��
		while (price > 0)
		{
			int min = Integer.MAX_VALUE;
			int minCoin = Integer.MAX_VALUE;
			//���μ��㵱ǰ������
			for (int coin : coins)
			{
				//����ֱ�ӱ�����С�ļ���
				if (minNums[price - coin] < min)
				{
					min = minNums[price - coin];
					minCoin = coin;
				}
			}
			//����
			info[index++] = minCoin;
			price -= minCoin;
		}
		//������
		System.out.println(Arrays.toString(info));
	}
}
