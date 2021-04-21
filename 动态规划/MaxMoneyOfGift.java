/*
	���������ֵ��
	����һ��m*n�����飬��ʾÿһ��λ�õļ�ֵ�������Ͻǳ�����ÿ��ֻ�� ����/������һ����һֱ�����½�
	���Կ�����ȷ��ÿһ��������ֵ�ǿ���λ�õ��Ϸ�/����һ��ֵ�����һ�����ѡ����һ��

	1.����ԭ�����������⣺
		ԭ���⣺�� 1,1 �� m,n ������ֵ
		�����⣺�� 1,1 �� i,j ������ֵ
	2.����״̬
		i,j
	3.����״̬ת�Ʒ���
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
		System.out.println("�����������Ӧ�����ļ�ֵΪ��" + new MaxMoneyOfGift().getMaxMoneyRecusion(moneyInfo));
		System.out.println("�����������Ӧ�����ļ�ֵΪ��" + new MaxMoneyOfGift().getMaxMoneyFront(moneyInfo));
	}

	public int getMaxMoneyRecusion(int[][] moneyInfo)
	{
		int m = moneyInfo.length;
		int n = moneyInfo[0].length;

		int[][] maxMoney = new int[m][n];

		return getMaxMoney(moneyInfo, m - 1, n - 1, maxMoney);
	}
	

	//��ȡ����ֵ���Ӻ���ǰ�ݹ���
	private int getMaxMoney(int[][] moneyInfo, int i, int j, int[][] maxMoney)
	{
		if(i == 0 && j == 0)
		{
			//���Ͻ�
			maxMoney[i][j] = moneyInfo[0][0];
			return maxMoney[i][j];
		}
		else if (i == 0 && j > 0)
		{
			//��һ�е�λ�ã�ֻ��ѡ����ߵ�
			maxMoney[i][j] = getMaxMoney(moneyInfo, i, j - 1, maxMoney) + moneyInfo[i][j];
			return maxMoney[i][j];
		}
		else if (j == 0 && i > 0)
		{
			//��һ�е�λ�ã�ֻ��ѡ�������
			maxMoney[i][j] = getMaxMoney(moneyInfo, i - 1, j, maxMoney) + moneyInfo[i][j];
			return maxMoney[i][j];
		}
		else
		{
			//��ͨλ�ã�ȡ��������ĺ���ߵ���һ����
			maxMoney[i][j] = Math.max(getMaxMoney(moneyInfo, i - 1, j, maxMoney), getMaxMoney(moneyInfo, i, j - 1, maxMoney)) + moneyInfo[i][j];
			
			//���������λ�ã����
			if(i == moneyInfo.length - 1 && j == moneyInfo[0].length - 1)
			{
				//���
				int[][] paths = getPath(moneyInfo, maxMoney);
				for (int[] path : paths)
				{
					System.out.println(Arrays.toString(path));
				}
			}
			return maxMoney[i][j];
		}
	}

	//��ȡ����ֵ����ǰ����ֱ�Ӽ�����
	private int getMaxMoneyFront(int[][] moneyInfo)
	{
		int m = moneyInfo.length;
		int n = moneyInfo[0].length;
		//�жϱ߽�����
		if (m == 0 & n == 0)
		{
			return 0;
		}
		if (m == 1 & n == 1)
		{
			return moneyInfo[1][1];
		}

		int[][] maxMoney = new int[m][n];
		
		//��ʼ����һ�к͵�һ�е�����
		maxMoney[0][0] = moneyInfo[0][0];
		for (int j = 1; j < n; j++)
		{
			maxMoney[0][j] = maxMoney[0][j - 1] + moneyInfo[0][j];
		}
		for (int i = 1; i < m; i++)
		{
			maxMoney[i][0] = maxMoney[i - 1][0] + moneyInfo[i][0];
		}
		
		//����������ͨ�ڵ�
		for (int i = 1; i < m; i++)
		{
			for (int j = 1; j < n; j++)
			{
				maxMoney[i][j] = Math.max(maxMoney[i - 1][j], maxMoney[i][j - 1]) + moneyInfo[i][j];
			}
		}
		//���
		//int[][] paths = getPath(moneyInfo, maxMoney);
		int[][] paths = getPath(maxMoney);
		for (int[] path : paths)
		{
			System.out.println(Arrays.toString(path));
		}
		return maxMoney[m - 1][n - 1];
	}

	//���ѡ��·�������ַ��� 
	//	1.�жϵ�ǰλ�õ���߼�ֵ ��ȥ ��ǰ�ļ�ֵ ���� ���/����   �� ��ʽ
	//	2.ֱ���ж� ��/�� ��һ��λ�õļ�ֵ��߼���
	public int[][] getPath(int[][] moneyInfo, int[][] maxMoney)
	{
		//��������
		int m = moneyInfo.length;
		int n = moneyInfo[0].length;

		int[][] path = new int[m][n];
		//�����һ��λ�ÿ�ʼ���ж�
		for (int i = m - 1, j = n - 1; ;)
		{
			path[i][j] = 1;
			if (i == 0 && j == 0)
				break;
			//�ж��Ƿ��ڱ߱���
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
		//��������
		int m = maxMoney.length;
		int n = maxMoney[0].length;

		int[][] path = new int[m][n];
		//�����һ��λ�ÿ�ʼ���ж�
		for (int i = m - 1, j = n - 1; ;)
		{
			path[i][j] = 1;
			if (i == 0 && j == 0)
				break;
			//�ж��Ƿ��ڱ߱���
			if (i == 0)
			{
				--j;
			}
			else if (j == 0)
			{
				--i;
			}
			//�ж���һ���������ѡ����һ��·
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
