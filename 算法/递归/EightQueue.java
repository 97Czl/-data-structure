import java.util.*;

public class EightQueue 
{
	private final int MAXSIZE = 8;
	private static int okNum = 0;
	private static int allNum = 0;

	//����һ��һά��������������
	//��Ϊÿ��������һ����������һά������±�λ������ʾ����
	//ÿ�λʺ�����������������ڲ�����ֵ������1-8
	private int[] qiPan = new int[MAXSIZE];

	public static void main(String[] args)
	{
		EightQueue eq = new EightQueue();
		eq.check(0);
		System.out.println("�ܹ���" + okNum + "�ֿ��ܽ��");
		System.out.println("�ܹ�������" + allNum + "��");
	}

	//��ʾ��ǰԪ��
	public void show()
	{
		okNum++;
		for (var i = 0; i < MAXSIZE; i++)
		{
			System.out.print(qiPan[i] + " ");
		}
		System.out.println();
	}

	//���õ�n���ʺ�
	public void check(int n)
	{
		if (n == MAXSIZE)
		{
			show();
			return;
		}
		
		//------------------------------------------------
		//�����forѭ�� ��֤�� ����һ�����ӷ��ڵ�һ�е������� �Լ�����������ѡ�ʵ�ֵݹ�
		//------------------------------------------------
		//���η���ʺ󣬲��ж��Ƿ��ͻ
		for (int i = 0; i < MAXSIZE; i++)
		{
			//�Ȱѵ�ǰ�Ļʺ�n�����ڸ��е�һ��
			qiPan[n] = i;
			//�жϵ�ǰ���õĻʺ󵽵�i��ʱ���Ƿ��ͻ
			if (judge(n))
			{
				//����ͻ
				//���ŷ���n+1�ʺ󣬼���ʼ�ݹ�
				check(n + 1);
			}
			else
			{
				//�����ͻ�ˣ�����ִ��array[n] = i;
				//������n���ʺ󣬷����ڱ��еĺ���һ��λ��
			}
		}
	}


	//�ж�Ҫ���õĵ�n���ʺ��λ�÷������Ϲ涨
	/**
	 *@param n ��ʾ��n���ʺ�
	 */
	public boolean judge(int n)
	{
		allNum++;
		//����n������֮ǰ�����Ӻ͸�λ�ó岻��ͻ
		for (var i =  0; i < n; i++)
		{
			if (qiPan[i] == qiPan[n] ||								//����ͬһ�У���Ϊ�����±�������������Ծ��Բ�����ͬһ��
				Math.abs(n - i) == Math.abs(qiPan[i] - qiPan[n]))	//����ͬһб��
			{
				return false;
			}
		}
		return true;
	}
}
