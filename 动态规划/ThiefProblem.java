/*
	��ҽ������⣺����һ���������ÿ���˼ҵĿ�͵�ԵĽ�����ÿ����֮���б�����������ͬʱ͵���ڵ����ң�����ᴥ��������
	��[1, 2, 3, 1, 3] �����͵ȡ�Ľ����1,3,5���˼ң��ܹ�1+3+3=7

	��̬�滮�������
	1.����ԭ�����������
	 ԭ���⣺ǰn��������͵���������
	 �����⣺ǰk(k<n)��������͵���������
	2.����״̬
	 ǰk��������͵���������Ϊf(k) k��Ϊ״̬
	3.����״̬ת�Ʒ���
	         M1,						k==1;
	 f(n) =  max(M1, M2),				k==2;
			 max(f(k-1), f(k-2) + Mk),	k>=3;
	 k>=3ʱ��Ҫôk-1���������ֵ��Ҫôk-2�����ֵ����k����
*/
import java.util.Arrays;

public class ThiefProblem 
{
	public static void main(String[] args) 
	{
		int[] roomInfo = new int[]{2,7,2,3,8};
		System.out.println("��͵���������Ϊ��" + new ThiefProblem().steal(roomInfo));
		//�ݹ鷽ʽ
		System.out.println("��͵���������Ϊ��" + new ThiefProblem().stealRecursion(roomInfo));
	}

	public int steal(int[] roomInfo)
	{
		int length = roomInfo.length;
		//�����Ե����ϵı�̷�ʽ
		if(length == 1) return roomInfo[0];
		else if(length == 2) return Math.max(roomInfo[0], roomInfo[1]);
		else 
		{
			int[] maxRMB = new int[length];
			maxRMB[0] = roomInfo[0];
			maxRMB[1] = Math.max(roomInfo[0], roomInfo[1]);
			for (int k = 2; k < length; k++)
			{
				//���͵ȡk-1
				int fore = maxRMB[k - 1];
				//͵ȡk-2 + k
				int rear = maxRMB[k - 2] + roomInfo[k];
				maxRMB[k] = Math.max(fore, rear);
			}
			return maxRMB[length - 1];
		}
	}

	//�ݹ�汾
	public int stealRecursion(int[] roomInfo)
	{
		int length = roomInfo.length;

		if(length == 1) return roomInfo[0];
		else if(length == 2) return Math.max(roomInfo[0], roomInfo[1]);
		else 
		{
			int[] foreArr = Arrays.copyOf(roomInfo, length - 1);
			int[] rearArr = Arrays.copyOf(roomInfo, length - 2);
			//���͵ȡk-1
			int fore = stealRecursion(foreArr);
			//͵ȡk-2 + k
			int rear = stealRecursion(rearArr) + roomInfo[length - 1];
			return Math.max(fore, rear);
		}
	}
}
