import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HeapSort 
{
    private static int ARRSIZE = 8000000;
    private static int MAXNUM = ARRSIZE / 8 * 10;

	//��һ�����飨����������������һ���󶥶�
	/**
	 *���ܣ���ɽ���i��Ӧ�ķ�Ҷ�ӽڵ���������ɴ󶥶�
	 *���� int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => �õ� {4, 9, 8, 5, 6}
	 *����ٴε���adjustHeap�������i = 0 => �õ�{4, 9, 8, 5, 6} => {9, 6, 8, 5, 4}
	 *@param arr ������������
	 *@param i ��ʾ��Ҷ�ӽڵ��������е�����
	 *@param length ����Զ��ٸ�Ԫ�ؼ���������length�����𽥵ļ���
	 */
	private static void adjustHeap(int arr[], int i, int length)
	{
		int temp = arr[i];	//��ȡ����ǰ��Ԫ�أ���������ʱ����

		//��ʼ����
		//1�� k = 2 * i + 1 ��i�ڵ�����ӽڵ�
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1)
		{
			if (k + 1 < length && arr[k] < arr[k + 1]) //˵�����ӽڵ��ֵС�����ӽڵ��ֵ
			{
				k++;	//kָ��ϴ��ֵ
			}
			//������ʵȷ���˶��ڽڵ�i��˵�������ӽڵ��Ǹ��ӽڵ��ֵ�ϴ�
			//Ȼ�� ��һ��if��else��Ϊ�˽���Ӧ�Ľϴ���ӽڵ�����Ĵ�ֵ�����ƶ�һ��(ÿ�ε�������������нϴ���ӽڵ�ֻ�ƶ�һ�㣬i����Ԫ�ػ��ƶ��������ƶ�ʱ����ײ�)��ֱ�������ӽڵ㶼�Ƚ�С
			if (arr[k] > temp)	//����ӽڵ��ֵ���ڸ��ڵ�
			{
				arr[i] = arr[k];
				i = k;	//��������������������������������������������������������������ѭ���Ƚ�
			}
			else 
			{
				break;
			}
		}
		//forѭ����������iΪ���ڵ���������ֵ�Ѿ������˶���
		arr[i] = temp;

		//��ʵֻ������һ���ڵ�������ӽڵ㣬����ӽڵ��ֵ��������
	}

	public static void heapSort(int[] arr)
	{
		int temp = 0;
		//���ȵ�һ��������������е�����һ����
		for (int i = arr.length / 2 - 1; i >= 0; i--)
		{
			adjustHeap(arr, i, arr.length);
			System.out.println("����������Ϊ��\t\t" + Arrays.toString(array));
		}

		for (int j = arr.length - 1; j > 0; j--)
		{
			//��������
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
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

        //int[] array = HeapSort.getArray(ARRSIZE, MAXNUM);
        int[] array = new int[]{53, 3, 542, 748, 14, 214, 328, 9, 1000};

        //System.out.println("��ʼ����Ϊ��\t\t" + Arrays.toString(array));


        long time1 = System.currentTimeMillis();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        System.out.println(ARRSIZE + "�����ֶ�����ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));

        HeapSort.heapSort(array);

        System.out.println(ARRSIZE + "�����ֶ����򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time1) + "ms");
        Date endTime = new Date();
        System.out.println(ARRSIZE + "�����ֶ��������ʱ��ʱ�䣺" + simpleDateformat.format(endTime));

        //System.out.println("����������Ϊ��\t\t" + Arrays.toString(array));
    }
}
