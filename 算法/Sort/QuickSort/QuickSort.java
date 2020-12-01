import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class QuickSort 
{
	private static int ARRSIZE = 800000;
	private static int MAXNUM = ARRSIZE / 8 * 10;

	//���������㷨
	public static void quickSort(int[] array, int left, int right)
	{
		//��ѡѡȡ�м�λ�õ�Ԫ����Ϊ�ԱȻ�׼
		//��������������������������ֵ�������޸�
		int l = left;
		int r = right;
		int pivot = array[(left + right) / 2];
		int temp = 0;														//��Ϊ�����ĸ�����ʱ����

		//�� l��r û�����棬������û��ȫ�������꣬�����
		while (l < r)
		{
			//������� l ��ʼ�������ҵ��� pivot ���Ԫ�أ����ߵ���pivot�������˳�
			//�˳�ѭ��ʱ����ʱ�� l ����ֵ�� ���ڵ��� pivot
			while (array[l] < pivot)
			{
				//�����ǰ��ֵ����������l ������һλ
				l++;
			}

			//�����ұ� r ��ʼ�������ҵ��� pivot С��Ԫ�أ����ߵ���pivot�������˳�
			//�˳�ѭ��ʱ����ʱ�� r ����ֵ�� С�ڵ��� pivot
			while (array[r] > pivot)
			{
				//�����ǰ��ֵ����������r ��ǰ��һλ
				r--;
			}

			//�ҵ��˵�ǰ��ֵ����� l == r,��û��Ҫ��������ֱ���˳�
			if (l == r)
			{
				break;
			}

			//������ǶԱ굽��ͬһ��Ԫ�أ�������Ԫ�ؽ���
			temp = array[l];
			array[l] = array[r];
			array[r] = temp;
			
			
			//�����Ժ��������Ԫ�ص����м�������ֵ����÷����Ѿ���ͷ�ˣ���һ��λ����Ҫ�ƶ�һ��
			//����������˸պö����ֵ������ֵ������ѭ����
			if (array[l] == pivot)
			{
				r--;
			}
			if (array[r] == pivot)
			{
				l++;
			}
		}

		//���ƶ�������l �� r ָ��ͬһ��λ�ã����� Ӧ�ø����ƶ�һ�£��������ѭ��
		if (l == r)
		{
			l++;
			r--;
		}
		//System.out.println("��ǰ����ǣ� \t" + Arrays.toString(array));
		
		//�����ߵ�Ԫ�ػ��д���һ���ģ�˵������Ҫ�����ݹ�
		if (left < r)
		{
			//������������߱���
			quickSort(array, left, r);
		}
		
		//����ұߵ�Ԫ�ػ��д���һ���ģ�˵������Ҫ�����ݹ�
		//���������ȣ�˵��Ԫ�ر�������
		if (l < right)
		{
			//�ұ߱���
			quickSort(array, l, right);
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
		int[] array = QuickSort.getArray(ARRSIZE, MAXNUM);
		//int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		
		//System.out.println("��ʼ����Ϊ��\t\t" + Arrays.toString(array));

		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("��������ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));


		QuickSort.quickSort(array, 0, array.length - 1);

		System.out.println("�������򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println("�����������ʱ��ʱ�䣺" + simpleDateformat.format(endTime));
	}
}
