import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MergeSort 
{
	private static int ARRSIZE = 800000;
	private static int MAXNUM = ARRSIZE / 8 * 10;

	public static void mergeSort(int[] arr, int left, int right, int[] temp)
	{
		//�����߻�С���ұ�������˵�����黹���Լ�������
		if (left < right)
		{
			//���ȶ����м�ֵ
			int mid = (left + right) / 2;
			//�ݹ�ֱ����ߺ��ұߵ����ݼ������з�
			mergeSort(arr, left, mid, temp);
			mergeSort(arr, mid + 1, right, temp);
			merge(arr, left, mid, right, temp);
		}
	}

	//���Ѿ��ֵ�������к�
	public static void merge(int[] arr, int left, int mid, int right, int[] temp)
	{
		int i = left;													//��������������������ֵ
		int j = mid + 1;												//�����ұ��������������ֵ
		int t = 0;														//��ʱ���������ֵ

		//1.������������������б���������ȡ����С��Ԫ�أ�ֱ��һ�ߴﵽβ��
		while (i <= mid && j <= right)
		{
			//�ж�ȡ����С��Ԫ�ط�����ʱ������
			if (arr[i] < arr[j])
			{
				temp[t] = arr[i];
				t += 1;
				i += 1;
			}
			else 
			{
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}

		//�����Ѿ���һ���ֵ�����β����˵����һ���ֶ�������ֵ����������ģ�ֱ�ӷ�����ʱ����Ϳ�
		//2.�ж��ıߵ��˶�β����һ��ֱ�ӷ�����ʱ����
		while (i <= mid)
		{
			temp[t] = arr[i];
			t += 1;
			i += 1;
		}
		while (j <= right)
		{
			temp[t] = arr[j];
			t += 1;
			j += 1;
		}

		//3.��ʱ�����Ѿ�ȫ�����������ֻ�轫�������ʱ���� copy �������������
		t = 0;																//��ʱ���������ֵ
		int tempLeft = left;												//��Ϊ�������������ֵ����֤Ԫ��λ�õ���ȷ��
		while (tempLeft <= right)
		{
			arr[tempLeft] = temp[t];
			tempLeft += 1;
			t += 1;
		}

		//�����ǰ����
		//System.out.println("��ǰ���飺\t" + Arrays.toString(arr));
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
		int[] array = MergeSort.getArray(ARRSIZE, MAXNUM);
		//int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		int[] temp = new int[ARRSIZE];
		
		//System.out.println("��ʼ����Ϊ��\t\t" + Arrays.toString(array));

		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("��������ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));


		MergeSort.mergeSort(array, 0, array.length - 1, temp);

		System.out.println("�������򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println("�����������ʱ��ʱ�䣺" + simpleDateformat.format(endTime));
	}
}
