import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HeapSortCraft2 
{
	private static int ARRSIZE = 8000000;
	private static int MAXNUM = ARRSIZE / 8 * 10;

	//���������ķ�����ʹ�ô󶥶�
	public static void heapSortIncrease(int[] arr)
	{
		//��ȡÿһ����Ҫ����Ԫ�ص�����
		int number = arr.length;
		//����λ�õĸ�������
		int temp = 0;
		for (; number > 0 ; number--)
		{
			//��һ�����ȶ�����ȫ��Ԫ�ر����ҳ���Ҷ�ӽڵ㣬����������Ϊ�󶥶ѵĸ�ʽ
			for (int i = number - 1; i >= 0; i--)
			{
				//��Ҷ�ӽڵ�˵���ýڵ�������ӽڵ㶼�����鷶Χ��
				if ((2 * i + 1) < arr.length && (2 * i + 2) < arr.length)
				{
					//1.����Ԫ�ض����д����� 
					if ((2 * i + 1) < number && (2 * i + 2) < number)
					{
						//�ӽڵ�˭��˭
						if (arr[2 * i + 1] < arr[2 * i + 2] && arr[i] <  arr[2 * i + 2])
						{
							int tmp = arr[i];
							arr[i] = arr[2 * i + 2];
							arr[2 * i + 2] = tmp;
						}
						else if(arr[2 * i + 2] < arr[2 * i + 1] && arr[i] <  arr[2 * i + 1])
						{
							int tmp = arr[i];
							arr[i] = arr[2 * i + 1];
							arr[2 * i + 1] = tmp;
						}
					}
					//2.һ���Ѿ�ȷ�������ܽ�����
					else if ((2 * i + 1) < number && arr[i] < arr[2 * i + 1])
					{
						//����ܻ������ǽϴ�ֵ
						int tmp = arr[i];
						arr[i] = arr[2 * i + 1];
						arr[2 * i + 1] = tmp;
					}
					else if ((2 * i + 2) < number && arr[i] < arr[2 * i + 2])
					{
						//�ұ��ܻ������ǽϴ�ֵ
						int tmp = arr[i];
						arr[i] = arr[2 * i + 2];
						arr[2 * i + 2] = tmp;
					}
					//3.���߶��Ѿ�ȷ������������
				}
			}
			//���������ַŵ����
			temp = arr[0];
			arr[0] = arr[number - 1];
			arr[number - 1] = temp;
		}
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

		//int[] array = HeapSortCraft2.getArray(ARRSIZE, MAXNUM);
		int[] array = new int[]{53, 3, 542, 748, 14, 214, 328, 9, 1000};
		
		System.out.println("��ʼ����Ϊ��\t\t" + Arrays.toString(array));
		
		/*
		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println(ARRSIZE + "�����ֶ�����ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));

		HeapSortCraft2.heapSortIncrease(array);

		System.out.println(ARRSIZE + "�����ֶ����򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println(ARRSIZE + "�����ֶ��������ʱ��ʱ�䣺" + simpleDateformat.format(endTime));
		*/

		HeapSortCraft2.heapSortIncrease(array);
		System.out.println("����������Ϊ��\t\t" + Arrays.toString(array));
	}
}
