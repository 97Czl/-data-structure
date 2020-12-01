import java.util.Arrays;

public class BubbleSort 
{
	private static int ARRSIZE = 5;
	private static int MAXNUM = 100;
	
	//��������г�ʼ�������ֵ
	public int[] init()
	{
		int[] arr = new int[ARRSIZE];
		for (int i = 0; i < ARRSIZE; i++)
		{
			arr[i] = (int) (Math.random() * MAXNUM);
		}
		return arr;
	}

	//ð�������㷨
	public static int[] bubbleSort(int[] arr)
	{
		int temp = 0;											//���𽻻�ֵ����ʱ��������
		//�����ݽ�������
		for (int i = 0; i < arr.length - 1; i++)
		{				
			for (int j = 0; j < arr.length - 1 - i; j++)
			{
				if (arr[j] > arr[j + 1])						//�������Ԫ�ز���������˳��
				{
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			System.out.println("��" + (i + 1) + "������Ľ���ǣ�" + Arrays.toString(arr));
		}
		return arr;
	}

	public static void main(String[] args) 
	{
		BubbleSort bsd = new BubbleSort();
		int[] arr = bsd.init();
		System.out.println("ԭʼ����Ϊ��" + Arrays.toString(arr));

		BubbleSort.bubbleSort(arr);
	}
}
