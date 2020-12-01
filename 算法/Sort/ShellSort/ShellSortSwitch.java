import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ShellSortSwitch 
{
	private static int ARRSIZE = 80000;
	private static int MAXNUM = 100000;

	//����ϣ������ķ���
	public static void shellSort(int[] arr)
	{
		int j = 0;																//��ǰ�жϵ�����ֵ
		int temp = 0;															//��������ʱ����
		int count = 0;
		//�޸ĳ�forѭ��
		//�Բ���ͬһ�������ǰ��Ԫ�غ�һλ��ʼ����
		for (int gap = arr.length / 2; gap >= 1; gap = gap / 2)
		{
			//��count+1������gap = gap / 2,���鱻��Ϊlength / gap�飬���Դӵ�gap��Ԫ�ؿ�ʼ�����±���gap
			for (int i = gap; i < arr.length; i++)
			{
				//�տ�ʼ j = i , Ȼ��������ͬ���ǰ���ֵ�����ж�
				//��Ҫ��֤j - gap����Խ��
				for (j = i; j - gap >= 0; j -= gap)
				{
					if (arr[j] < arr[j - gap])
					{
						//���arr[j] < arr[j - gap]����ô������ֵ����
						temp = arr[j];
						arr[j] = arr[j - gap];
						arr[j - gap] = temp;
					}
				}
			}
				
			//System.out.println("��" + (++count) + "������������Ϊ��\t" + Arrays.toString(arr));
		}

		/*
		//������
		//��1������gap = length / 2,���鱻��Ϊ5�飬���Դӵ�����Ԫ�ؿ�ʼ�����±���5
		for (int i = 5; i < arr.length; i++)
		{
			//�տ�ʼ j = i , Ȼ��������ͬ���ǰ���ֵ�����ж�
			//��Ҫ��֤j - gap����Խ��
			for (j = i; j - 5 >= 0; j -= 5)
			{
				if (arr[j] < arr[j - 5])
				{
					//���arr[j] < arr[j - gap]����ô������ֵ����
					temp = arr[j];
					arr[j] = arr[j - 5];
					arr[j - 5] = temp;
				}
			}
		}
			
		System.out.println("��1������������Ϊ��\t" + Arrays.toString(arr));

		//��2������gap = gap / 2 = 2,���鱻��Ϊ2�飬���Դӵ�3��Ԫ�ؿ�ʼ�����±���2
		for (int i = 2; i < arr.length; i++)
		{
			//�տ�ʼ j = i , Ȼ��������ͬ���ǰ���ֵ�����ж�
			//��Ҫ��֤j - gap����Խ��
			for (j = i; j - 2 >= 0; j -= 2)
			{
				if (arr[j] < arr[j - 2])
				{
					//���arr[j] < arr[j - gap]����ô������ֵ����
					temp = arr[j];
					arr[j] = arr[j - 2];
					arr[j - 2] = temp;
				}
			}
		}
			
		System.out.println("��2������������Ϊ��\t" + Arrays.toString(arr));

		//��3������gap = gap / 2 = 1,���鱻��Ϊ10�飬���Դӵ�2��Ԫ�ؿ�ʼ�����±���1
		for (int i = 1; i < arr.length; i++)
		{
			//�տ�ʼ j = i , Ȼ��������ͬ���ǰ���ֵ�����ж�
			//��Ҫ��֤j - gap����Խ��
			for (j = i; j - 1 >= 0; j -= 1)
			{
				if (arr[j] < arr[j - 1])
				{
					//���arr[j] < arr[j - gap]����ô������ֵ����
					temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
		}
			
		System.out.println("��3������������Ϊ��\t" + Arrays.toString(arr));
		*/
	}

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

	public static void main(String[] args) 
	{

		int[] array = ShellSortCraft.getArray(ARRSIZE, MAXNUM);
		//int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		
		//System.out.println("��ʼ����Ϊ��\t\t" + Arrays.toString(array));

		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("ϣ������ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));


		ShellSortSwitch.shellSort(array);

		System.out.println("ϣ�����򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println("ϣ���������ʱ��ʱ�䣺" + simpleDateformat.format(endTime));
	}
}
