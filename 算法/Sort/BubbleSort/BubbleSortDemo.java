import java.util.Arrays;

public class BubbleSortDemo 
{
	private static int ARRSIZE = 5;
	private static int MAXNUM = 100;
	private int[] arr = new int[ARRSIZE];
	
	//��������г�ʼ�������ֵ
	private void init()
	{
		for (int i = 0; i < ARRSIZE; i++)
		{
			arr[i] = (int) (Math.random() * MAXNUM);
		}
	}

	public static void main(String[] args) 
	{
		BubbleSortDemo bsd = new BubbleSortDemo();
		bsd.init();
		System.out.println("ԭʼ����Ϊ\n" + Arrays.toString(bsd.arr));

		//�����ݽ�������
		//��һ������,����Ԫ��Ϊ�����С - 1
		int temp = 0;										//���𽻻�ֵ����ʱ��������
		for (int j = 0; j < ARRSIZE - 1; j++)
		{
			if (bsd.arr[j] > bsd.arr[j + 1])						//�������Ԫ�ز���������˳��
			{
				temp = bsd.arr[j];
				bsd.arr[j] = bsd.arr[j + 1];
				bsd.arr[j + 1] = temp;
			}
		}

		System.out.println("��һ������Ľ����\n" + Arrays.toString(bsd.arr));
	}
}
