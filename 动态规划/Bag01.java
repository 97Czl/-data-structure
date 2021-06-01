/*
	01�������⣺������Ʒ�������ͼ�ֵ����ν���߼�ֵ�Ķ���װ��������   01����ֻ��ѡ�� װ���߲�װ
	��Ϊ��Ҫ���� ��Ʒ������ M �ͱ��������� W ������Ҫһ����ά����
	1.����ԭ�����������⣺
		ԭ���⣺�� M ����Ʒװ������Ϊ W �ı��� ����߼�ֵ
		�����⣺�� i(i<M) ����Ʒװ������Ϊ j(j<W) �ı����ܻ�ȡ����߼�ֵ
	2.״̬������i,j
	3.״̬ת�Ʒ���
		�߽�������(i==0 || j == 0) f(i,j) = 0;  ������Ҫ��Ūһ��һ�� 
			���ȵ�һ�У���i == 1��ֻ��һ�����������j < wi��˵��װ���£�ֱ�����㣬���� f(i,j) = wi;
			��ε�һ�У���j == 1, ��������Ϊ1�����ԣ�ֻҪ�ҵ�ǰi����Ʒ�������Ǹ���f(i,j) = wmax;      ���ƹ�ʽ��wi == 1; f(i, j) = max(f(i - 1, j), pi)
																												  wi > 1; f(i, j) = f(i - 1, j)
		һ�����			f(i - 1, j)										,wi > j ���ȿ����ܲ��ܰ�i�Ž�ȥ�������������װ����ȥ
				  f(i,j) =  max(pi, f(i - 1, j)) 							,wi == j ���� ֻװi �� ��װi ���ֺ� 
							max(f(i - 1, j - i) + pi, f(i - 1, j))			,wi < j ���� װi �� ��װi ���ֺ� 
*/
import java.util.*;

public class Bag01 
{
	public static void main(String[] args) 
	{
		//����۸� �� ���� ����
		int[] price = new int[]{540, 200, 180, 350, 60, 150, 280, 450, 320, 120};
		int[] weight = new int[]{6, 3, 4, 5, 1, 2, 3, 5, 4, 2};
		int capacity = 30;
		System.out.println(new Bag01().knapsack01problem(weight, price, capacity));
	}

	/**
	 *@param weight �������������
	 *@param price ����ļ�ֵ
	 *@param capacity ��������
	 *@return ��������ֵ
	 */
	public int knapsack01problem(int[] weight, int[] price, int capacity)
	{
		//���ȶ����ά���� i ����Ʒ������j�Ǳ�������
		int[][] f = new int[weight.length + 1][capacity + 1];
		//��i == 0 �� j == 0 ��ʱ����Ҫ�ܣ� ��ʼ������
		//ע�� i �� j��Ӧ��һλ������ ��ȡԭʼ���� weight �� price ��ʱ����Ҫ -1
		for (int i = 1; i <= weight.length; i++)
		{
			for (int j = 1; j <= capacity; j++)
			{
				//���ο������
				if(i == 1)
				{
					//ֻ��һ�����������ܲ���װ��ȥ
					if(weight[0] <= j)
						f[i][j] = price[0];
					else
						//�Ų���ȥ
						f[i][j] = 0;
					continue;
				}
				if (j == 1)
				{
					//���ƹ�ʽ
					if (weight[i - 1] == 1)
						f[i][j] = Math.max(f[i - 1][j], price[i - 1]);
					else
						f[i][j] = f[i- 1][j];
					continue;
				}
				//�������һ�������
				if(weight[i - 1] > j)
					f[i][j] = f[i - 1][j];
				else
					f[i][j] = Math.max(f[i - 1][j - weight[i - 1]] + price[i - 1], f[i - 1][j]);
			}
		}
		
		System.out.println("�����Ķ�ά����Ϊ��");
		//���һ��������
		for (int[] ff : f)
		{
			for (int ele : ff)
			{
				System.out.printf("%5d", ele);
			}
			System.out.println();
		}
		
		//�������װ����Щ����
		ArrayList<Integer> info = showInfo(weight, price, f, f[weight.length][capacity]);
		System.out.println(info.toString());

		//���ؽ��
		return f[weight.length][capacity];
	}
	
	/**
	 *������Ҫ˼·�ǣ����ַ���
		1�������ҵ���Ӧ���������һ�У��ҵ���һ�����ֵ�����Ժ�������Ʒ��������ֵ���䣬��ô��˵�����Ժ�Ķ���û�ã� ÿ�θ��²���
		2�����õ��ƹ�ʽ����ԭ��������Ҫ - 1��
			�Ƚ�   f[i - 1][j - weight[i - 1]] + price[i - 1], f[i - 1][j] ��һ���󣬶�Ӧ�ľ���װ��ȥ�ģ���ô����˵����Ӧ�ģ�i - 1�������õ�����Ʒ
	 *@param weight �������������
	 *@param price ����ļ�ֵ
	 *@param f ��Ӧ�Ķ�άDP����
	 *@param maxPrice ��Ӧ������ֵ
	 */
	public ArrayList<Integer> showInfo(int[] weight, int[] price, int[][] f, int maxPrice)
	{
		ArrayList<Integer> info = new ArrayList<>();
		//���ҵ���һ�����ֵ
		int i = f.length - 1;
		int j = f[0].length - 1;

		//����Ӧ�ı���
		while (maxPrice > 0)
		{
			//�Ƚ���һ����
			while (f[i - 1][j] == f[i][j])
			{
				i--;
			}
			//�ҵ��˳��ֵĵ�һ�����ֵ������
			info.add(i);
			//����
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
		//���ҵ���һ�����ֵ
		int i = f.length - 1;
		int j = f[0].length - 1;

		//����Ӧ�ı���
		while (maxPrice > 0)
		{
			//�Ƚ���һ����
			if ((f[i - 1][j - weight[i - 1]] + price[i - 1]) > f[i - 1][j])
			{
				//˵��װ��i�Ǵ��
				info.add(i);
				maxPrice -= price[i - 1];
				//�ȶ�ȡ�����ٲ���1
				j -= weight[i - 1];
				i--;
			}
			else
			{
				//˵��û����i����ô��ʱ�� i - 1�� j�Ƚϴ󣬵�����ȷ����û�� i - 1������
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