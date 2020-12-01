import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;

public class InsertSortCraft 
{
	
	private static int ARRSIZE = 10;
	private static int MAXNUM = 100;

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
		//��������

		//��һ����
		//1.��Ҫ�������Ԫ�شӵڶ�����ʼ �����浱ǰ������ֵ insertVal = arr[1];
		//2.���ر��������С���ͽ���λ�ã�һֱ�����ʵ�λ�ã����Բ��������ֵӦ�ü�һ  insertIndex = 1 - 1��
		//3.�ҵ����ʵ�λ�ã������ֵ����
		insertVal = arr[1];
		insertIndex = 1 - 1;
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
		arr[insertIndex + 1] = insertVal;

		//�����ǰ����
		System.out.println("��һ�α����������Ϊ��" + Arrays.toString(arr) + "\n");

		//�ڶ�����
		//1.��Ҫ�������Ԫ�شӵ�������ʼ �����浱ǰ������ֵ insertVal = arr[2];
		//2.���ر��������С���ͽ���λ�ã�һֱ�����ʵ�λ�ã����Բ��������ֵӦ�ü�һ  insertIndex = 2 - 1��
		//3.�ҵ����ʵ�λ�ã������ֵ����
		insertVal = arr[2];
		insertIndex = 2 - 1;
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
		arr[insertIndex + 1] = insertVal;

		//�����ǰ����
		System.out.println("�ڶ��α����������Ϊ��" + Arrays.toString(arr) + "\n");


		//��������
		//1.��Ҫ�������Ԫ�شӵ��ĸ���ʼ �����浱ǰ������ֵ insertVal = arr[3];
		//2.���ر��������С���ͽ���λ�ã�һֱ�����ʵ�λ�ã����Բ��������ֵӦ�ü�һ  insertIndex = 3 - 1��
		//3.�ҵ����ʵ�λ�ã������ֵ����
		insertVal = arr[3];
		insertIndex = 3 - 1;
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
		arr[insertIndex + 1] = insertVal;

		//�����ǰ����
		System.out.println("�����α����������Ϊ��" + Arrays.toString(arr) + "\n");


		//���Ĳ���
		//1.��Ҫ�������Ԫ�شӵ������ʼ �����浱ǰ������ֵ insertVal = arr[4];
		//2.���ر��������С���ͽ���λ�ã�һֱ�����ʵ�λ�ã����Բ��������ֵӦ�ü�һ  insertIndex = 4 - 1��
		//3.�ҵ����ʵ�λ�ã������ֵ����
		insertVal = arr[4];
		insertIndex = 4 - 1;
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
		arr[insertIndex + 1] = insertVal;

		//�����ǰ����
		System.out.println("���Ĵα����������Ϊ��" + Arrays.toString(arr) + "\n");


		
		return arr;
	}


	public static void main(String[] args) 
	{
		//======================11111111111111111===================
		//�����ʼ��ʱ��
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		//System.out.println("�����ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));

		//======================22222222222222222===================
		long time1 = System.currentTimeMillis();

		//�������ʼ��
		int[] arr = InsertSortCraft.getArray(ARRSIZE, MAXNUM);

		//�������
		System.out.println("ԭʼ����Ϊ��" + Arrays.toString(arr) + "\n");
		
		//======================22222222222222222===================
		//ͳ�Ƴ�ʼ��������ʱ��
		long time2 = System.currentTimeMillis();
		//System.out.println("��СΪ" + ARRSIZE + "���������ɵ�ʱ����" + (time2 - time1) + "ms");

		//======================11111111111111111===================
		//���뿪ʼ��ʱ��
		Date startBubbleTime = new Date();
		//System.out.println("��ʼ���������ʱ�䣺" + simpleDateformat.format(startBubbleTime));

		//��������
		InsertSortCraft.insertSort(arr);

		//======================22222222222222222===================
		//System.out.println("�������򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time2) + "ms");
		//======================11111111111111111===================
		//���������ʱ��
		Date endTime = new Date();
		//System.out.println("�������������ʱ�䣺" + simpleDateformat.format(endTime));
	}
}
