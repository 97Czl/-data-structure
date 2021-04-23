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
import java.util.Stack;

public class ThiefProblem 
{
	public static void main(String[] args) 
	{
		int[] roomInfo = new int[]{1,4,1,2,5,6,3};
		System.out.println("(DP)��͵���������Ϊ��" + new ThiefProblem().steal(roomInfo));
		//�ݹ鷽ʽ
		System.out.println("(DG)��͵���������Ϊ��" + new ThiefProblem().stealRecursion(roomInfo));
		//ѹ��״̬
		System.out.println("(OP)��͵���������Ϊ��" + new ThiefProblem().stealOptimal(roomInfo));
		//���������Ϣ
		System.out.println("(GR)͵���ķ��� ��� Ϊ��" + Arrays.toString(new ThiefProblem().stealGetRoom(roomInfo)));
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

	//�Ż�1��״̬ѹ�����ݹۼ��㲽�裬�õ���״̬��ʵֻ��f(k-1) �� f(k-2)����ֻ��Ҫ��������������
	public int stealOptimal(int[] roomInfo)
	{
		int length = roomInfo.length;
		//�����Ե����ϵı�̷�ʽ
		if(length == 1) return roomInfo[0];
		else if(length == 2) return Math.max(roomInfo[0], roomInfo[1]);
		else 
		{
			int k_2 = roomInfo[0];
			int k_1 = Math.max(roomInfo[0], roomInfo[1]);
			int current = 0;
			for (int k = 2; k < length; k++)
			{
				//����Ӧ���ȼ����ٸ���״̬
				current = Math.max(k_1, k_2 + roomInfo[k]);
				k_2 = k_1;
				k_1 = current;
			}
			return current;
		}
	}

	//�汾2������͵�Եķ��ݱ�� M=[1,4,1,2,5,6,3]  FF=[1,4,4,6,9,12,12]
	/*���Եõ���
		(1)���������i��FF(i) >= M(i)
		(2)FF����
	����:
		1.��FF�����ֵ��һ�γ��ֵ�λ��index����С͵͵�Ե����һ�䷿
		2.�Ƚ�FF[index]��M[index]��С�����ܻ������ֽ��
			1)FF[index] == M[index] û��͵��ǰ��ķ��ӣ�����
			2)FF[index] > M[index] ǰ�滹��͵�Եķ��ӣ���������͵ȡ��� max=FF[index] - M[index]
			   ������index-=2���������㲻�������ڵķ���,Ȼ��2,ֱ��������ս�� 
    */
	public int[] stealGetRoom(int[] roomInfo)
	{
		int length = roomInfo.length;
		//�����Ե����ϵı�̷�ʽ
		if(length == 1) return new int[]{0};
		else if(length == 2) 
		{
			if(roomInfo[0] > roomInfo[1])
				return new int[]{0};
			else
				return new int[]{1};
		}
		else 
		{
			//����������2�����
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


			//��¼������Ϣ
			Stack<Integer> room = new Stack<>();
			int index = length - 1;
			System.out.println("(GR)��͵���������Ϊ��" + maxRMB[length - 1]);
			while (index >= 0)
			{
				//�ظ���ֵ�ͼ���
				if(index > 0 && maxRMB[index] == maxRMB[index - 1])
				{
					index--;
					continue;
				}
				if (maxRMB[index] == roomInfo[index])
				{
					//����ǰ�Ѿ�û��͵��
					room.push(index);
					break;
				}
				else
				{
					//���ȱ��浱ǰ������
					room.push(index);
					//���ϲ����� 
					index -= 2;
				}
			}
			//��ջת��������
			int[] roomIndex = new int[room.size()];
			for (int i = 0; i < roomIndex.length; i++)
			{
				roomIndex[i] = room.pop();
			}
			return roomIndex;
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
