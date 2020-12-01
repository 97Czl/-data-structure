import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;

public class InsertSort 
{
	
	private static int ARRSIZE = 80000;
	private static int MAXNUM = 100000;

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
	
	//���������㷨
	public static int[] insertSort(int[] arr)
	{
		//���帨������
		int insertVal = 0;
		int insertIndex = 0;
		
		//��forѭ������
		for (int i = 1; i < arr.length; i++)
		{
			//1.��Ҫ�������Ԫ�شӵ� i ����ʼ �����浱ǰ������ֵ insertVal = arr[i];
			//2.���ر��������С���ͽ���λ�ã�һֱ�����ʵ�λ�ã����Բ��������ֵӦ�ü�һ  insertIndex = i - 1��
			//3.�ҵ����ʵ�λ�ã������ֵ����
			insertVal = arr[i];
			insertIndex = i - 1;
			//insertIndex >= 0  -------------- ��ֹ����Խ��
			//insertVal < arr[insertIndex] ---------- ��ǰ��ֵû������ȷ��λ�ã�����Ҫ������ǰ����
			while (insertIndex >= 0 && insertVal < arr[insertIndex])  
			{
				//���indexVal û���ں��ʵ�λ�ã���������ǰһ��Ԫ��ҪС������Ҫ��ǰһλ�����ƣ�Ȼ������ǰ��
				arr[insertIndex + 1] = arr[insertIndex];					//��ǰ�����ֵ������һλ
				insertIndex--;												//��������ǰһλ
			}

			//����ҵ��˺��ʵ�λ�ã���ǰ������ֵ �� ����+1 ��ֵ ��insertVal�м䣬
			//������Ҫ��Ҫ�����ֵinsertVal���뵽insertIndex + 1��λ��
			if (insertIndex + 1 != i)
			{
				arr[insertIndex + 1] = insertVal;
			}

			//�����ǰ����
			//System.out.println("��" + i +"�α����������Ϊ��\t" + Arrays.toString(arr) + "\n");
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
		int[] arr = InsertSort.getArray(ARRSIZE, MAXNUM);

		//�������
		//System.out.println("ԭʼ����Ϊ��\t\t" + Arrays.toString(arr) + "\n");
		
		//======================22222222222222222===================
		//ͳ�Ƴ�ʼ��������ʱ��
		long time2 = System.currentTimeMillis();
		System.out.println("��СΪ" + ARRSIZE + "���������ɵ�ʱ����" + (time2 - time1) + "ms");

		//======================11111111111111111===================
		//���뿪ʼ��ʱ��
		Date startBubbleTime = new Date();
		System.out.println("��ʼ���������ʱ�䣺" + simpleDateformat.format(startBubbleTime));

		//��������
		InsertSort.insertSort(arr);

		//======================22222222222222222===================
		System.out.println("�������򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time2) + "ms");
		//======================11111111111111111===================
		//���������ʱ��
		Date endTime = new Date();
		System.out.println("�������������ʱ�䣺" + simpleDateformat.format(endTime));

		System.out.println("����������Ϊ��\t" + Arrays.toString(arr) + "\n");
	}
}
