import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SelectSort 
{
	private static int ARRSIZE = 10000;
	private static int MAXNUM = 100000;

	//����ѡ������ķ���
	public static void selectSort(int[] arr)
	{
		//������
		//��k�����򣺱���arr[k]~arr[n-1]����Ԫ�أ�Ȼ���ҳ���Сֵ����arr[k]����
		int minIndex = 0;														//��¼��Сֵ������
		int min = 0;															//������Сֵ
		for (int i = 0; i < arr.length - 1; i++)
		{
			minIndex = i;
			min = arr[i];
			for (int j = i + 1; j < arr.length; j++)
			{
				//�ҳ���Сֵ
				if (arr[minIndex] > arr[j])
				{
					minIndex = j;
					min = arr[minIndex];
				}
			}
			//�����ǰ��Сֵ���ǵ�ǰ����ֵ������Ҫ����
			if (minIndex != i)
			{
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
			//System.out.println("��" + (i + 1) + "������������Ϊ��\t" + Arrays.toString(arr));
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

		int[] array = SelectSort.getArray(ARRSIZE, MAXNUM);
		
		//System.out.println("��ʼ����Ϊ��\t\t" + Arrays.toString(array));
		
		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("ѡ������ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));


		SelectSort.selectSort(array);

		System.out.println("ѡ�����򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println("ѡ���������ʱ��ʱ�䣺" + simpleDateformat.format(endTime));
	}
}
