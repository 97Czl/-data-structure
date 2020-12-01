import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class BubbleSortOptimization 
{
	//��������г�ʼ�������ֵ
	public static int[] getArray(int arrSize, int maxNum)
	{
		int[] arr = new int[arrSize];
		for (int i = 0; i < arrSize; i++)
		{
			arr[i] = (int) (Math.random() * maxNum);
		}
		return arr;
	}

	//ð�������㷨
	public static int[] bubbleSort(int[] arr)
	{
		int temp = 0;											//���𽻻�ֵ����ʱ��������
		boolean flag = false;									//�жϸ�����û�н����� ���û������������Ѿ���ɣ�����Ҫ����
		//�����ݽ�������
		for (int i = 0; i < arr.length - 1; i++)
		{				
			for (int j = 0; j < arr.length - 1 - i; j++)
			{
				if (arr[j] > arr[j + 1])						//�������Ԫ�ز���������˳��
				{
					flag = true;								//��������˽���������Ϊtrue
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}

			//ÿһ�α����������ж��Ƿ������Ѿ�����
			if (flag == false)
			{
				return arr;
			}
			else
			{
				flag = false;									//����δ��ɣ�������flag��ΪĬ��ֵ
			}

			//System.out.println("��ǰ���ȣ�" + ((float) ((i + 1) * 1.0 / arr.length)) + "%");
		}
		return arr;
	}

	public static void main(String[] args) 
	{
		//======================11111111111111111===================
		//�����ʼ��ʱ��
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("�����ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));

		//======================22222222222222222===================
		long time1 = System.currentTimeMillis();

		//�������ʼ��
		int arrSize = 80000;
		int[] arr = BubbleSortOptimization.getArray(arrSize, 100000);
		
		//======================22222222222222222===================
		//ͳ�Ƴ�ʼ��������ʱ��
		long time2 = System.currentTimeMillis();
		System.out.println("��СΪ" + arrSize + "���������ɵ�ʱ����" + (time2 - time1) + "ms");

		//======================11111111111111111===================
		//ð�ݿ�ʼ��ʱ��
		Date startBubbleTime = new Date();
		System.out.println("��ʼð�������ʱ�䣺" + simpleDateformat.format(startBubbleTime));

		//ð������
		BubbleSortOptimization.bubbleSort(arr);

		//======================22222222222222222===================
		System.out.println("ð�����򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time2) + "ms");
		//======================11111111111111111===================
		//ð�ݽ�����ʱ��
		Date endTime = new Date();
		System.out.println("ð�����������ʱ�䣺" + simpleDateformat.format(endTime));
	}
}
