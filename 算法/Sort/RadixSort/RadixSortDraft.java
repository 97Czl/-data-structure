import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RadixSortDraft 
{
	private static int ARRSIZE = 80000;
	private static int MAXNUM = 100000;

	//��������ķ��������ղ��������˼·
	public static void radixSort(int[] arr)
	{
		//1.������Ҫʮ��Ͱ�� ��ʱ���ÿ��λ����ֵ��Ӧ��ԭʼ����
		//2.�����ö�ά������ʵ�֣�������д����λ��ֵ����Ӧ���д��� ��Ͱ��ŵ����ݵ�����
		//3.��������Ҫһ����СΪ10����������¼ÿһ��Ͱ�����˶�������
		//---��ν����������ʹ�ÿռ�����ȡʱ�䣬��ø��ߵ�Ч��
		int[][] bucket = new int[10][arr.length];										//Ϊ�˱�֤���鲻��Խ�磬���Ǽ�����������������С�������óɺ�ԭʼ����һ����
		int[] bucketSize = new int[10];													//��¼��Ӧ��ÿһ��Ͱ����˶�������


		//��һ�֣����ÿ��Ԫ�صĸ�λֵ��������Ԫ�ذ���˳������Ӧ��Ͱ��
		//��������������Ԫ�طŻ�����
		for (int j = 0; j <arr.length; j++)
		{
			int element = arr[j] % 10;
			bucket[element][bucketSize[element]] = arr[j];
			bucketSize[element] += 1; 
		}
		//��ʱ�Ѿ�������Ԫ�ض�����Ҫ�����Ͱ��
		//������������Ԫ���ٷŻ�ȥ
		int index = 0;																	//��¼ԭʼ������±�����
		for (int k = 0; k < bucketSize.length; k++)
		{
			if (bucketSize[k] != 0)														//�����Ԫ�ز�Ϊ�㣬����Ӧ��λ��������Ԫ�ط���
			{
				for (int l = 0; l < bucketSize[k]; l++)
				{
					arr[index] = bucket[k][l];
					index++;
				}
			}
			//��λ�����ֵ���������ֶ�����󣬽���������
			bucketSize[k] = 0;
		}
		//�����ʱ������
		System.out.println("��һ�����������飺\t" + Arrays.toString(arr));

		//�ڶ��֣����ÿ��Ԫ�ص�ʮλֵ��������Ԫ�ذ���˳������Ӧ��Ͱ��
		//��������������Ԫ�طŻ�����
		for (int j = 0; j <arr.length; j++)
		{
			int element = arr[j] / 10 % 10;
			bucket[element][bucketSize[element]] = arr[j];
			bucketSize[element] += 1; 
		}
		//��ʱ�Ѿ�������Ԫ�ض�����Ҫ�����Ͱ��
		//������������Ԫ���ٷŻ�ȥ
		index = 0;																		//��¼ԭʼ������±�����
		for (int k = 0; k < bucketSize.length; k++)
		{
			if (bucketSize[k] != 0)														//�����Ԫ�ز�Ϊ�㣬����Ӧ��λ��������Ԫ�ط���
			{
				for (int l = 0; l < bucketSize[k]; l++)
				{
					arr[index] = bucket[k][l];
					index++;
				}
			}
			//��λ�����ֵ���������ֶ�����󣬽���������
			bucketSize[k] = 0;
		}
		//�����ʱ������
		System.out.println("�ڶ������������飺\t" + Arrays.toString(arr));


		//�����֣����ÿ��Ԫ�صİ�λֵ��������Ԫ�ذ���˳������Ӧ��Ͱ��
		//��������������Ԫ�طŻ�����
		for (int j = 0; j <arr.length; j++)
		{
			int element = arr[j] / 100 % 10;
			bucket[element][bucketSize[element]] = arr[j];
			bucketSize[element] += 1; 
		}
		//��ʱ�Ѿ�������Ԫ�ض�����Ҫ�����Ͱ��
		//������������Ԫ���ٷŻ�ȥ
		index = 0;																		//��¼ԭʼ������±�����
		for (int k = 0; k < bucketSize.length; k++)	
		{
			if (bucketSize[k] != 0)														//�����Ԫ�ز�Ϊ�㣬����Ӧ��λ��������Ԫ�ط���
			{
				for (int l = 0; l < bucketSize[k]; l++)
				{
					arr[index] = bucket[k][l];
					index++;
				}
			}
			//��λ�����ֵ���������ֶ�����󣬽���������
			bucketSize[k] = 0;
		}
		//�����ʱ������
		System.out.println("���������������飺\t" + Arrays.toString(arr));

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

		//int[] array = RadixSortDraft.getArray(ARRSIZE, MAXNUM);
		int[] array = new int[]{53, 3, 542, 748, 14, 214};
		
		System.out.println("��ʼ����Ϊ��\t\t" + Arrays.toString(array));

		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		//System.out.println("ϣ������ʼʱ��ʱ�䣺" + simpleDateformat.format(startTime));


		RadixSortDraft.radixSort(array);

		//System.out.println("ϣ�����򻨷ѵ�ʱ�䣺" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		//System.out.println("ϣ���������ʱ��ʱ�䣺" + simpleDateformat.format(endTime));
	}
}
