import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ShellSortInsert
{
	private static int ARRSIZE = 80000;
	private static int MAXNUM = 100000;

	//����ϣ������ķ���
	public static void shellSort(int[] arr)
	{
		int index = 0;																//��ǰ�жϵ�����ֵ
		int value = 0;																//��Ҫ��������ı���

		//�޸ĳ�forѭ��
		//�Բ���ͬһ�������ǰ��Ԫ�غ�һλ��ʼ����
		for (int gap = arr.length / 2; gap > 0; gap = gap / 2)
		{
			//��count+1������gap = gap / 2,���鱻��Ϊlength / gap�飬���Դӵ�gap��Ԫ�ؿ�ʼ�����±���gap
			for (int i = gap; i < arr.length; i++)
			{
				index = i;
				value = arr[index];
				//�����ǰ��Ԫ�ر�ǰһ����ͬ�飩��Ԫ��С
				//�Ը�Ԫ�ؽ��в������򣬸�Ԫ��֮ǰͬ��Ԫ�ر�Ϊ���򼯺�
				//��֤ 1)��Խ�� 2)���в����㷨
				while (index - gap >= 0 && value < arr[index - gap])
				{
					//�������ֵ������һλ
					arr[index] = arr[index - gap];
					index -= gap;
				}
				if (index != i)
				{
					//��value���뵽���ʵ�λ��
					arr[index] = value;
				}
			}
				
			//System.out.println("��x������������Ϊ��\t" + Arrays.toString(arr));
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

		int[] array = ShellSortInsert.getArray(ARRSIZE, MAXNUM);
		//int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		
		//System.out.println("��ʼ����Ϊ��\t\t" + Arrays.toString(array));

		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("ϣ����������ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));


		ShellSortInsert.shellSort(array);

		System.out.println("ϣ���������򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println("ϣ�������������ʱ��ʱ�䣺" + simpleDateformat.format(endTime)); 
	}
}