import java.util.Arrays;

public class FibonacciSearch 
{
	private static int MAXSIZE = 20;
	
	//����MAXSIZE��С��쳲���������
	public static int[] fibs(int maxSize)
	{
		int f[] = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++)
		{
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}
	
	//쳲��������ҷ���
	/**
	 *@para arr Ҫ���ҵ����飬����������
	 *@para key Ҫ���ҵ�ֵ
	 */
	public static int fibonacciSearch(int[] arr, int key)
	{
		//���ȶ����������ı���
		int low = 0;														//������½�
		int high = arr.length - 1;											//������Ͻ�
		int mid = 0;														//��λ�Ƚ�ֵ������
		
		//����쳲��������еı���
		int k = 0;															//���������С��ȡ��Ҫ�õ���쳲��������е��±�����ֵ
		int[] f = fibs(MAXSIZE);											//쳲���������

		//�������鳤�ȣ���ȡ k ��ֵ
		while (high > f[k] - 1)
		{
			k++;
		}
		//��ǰ���鳤����С�ڵ���f[k]�ģ�������Ҫ��������
		int[] temp = Arrays.copyOf(arr, f[k]);
		//Ϊ��ʵ���������飬��Ҫ���������λ��ȫ��������������ֵ
		for (int i = high + 1; i < f[k]; i++)
		{
			temp[i] = arr[high];
		}

		//�ݹ��ҵ�key������
		while (low <= high)
		{
			//����ȷ���Ƚϵ�ֵ
			mid = low + f[k - 1] - 1;
			if (temp[mid] > key)
			{
				//��ʱ˵��ֵ��������򣬼���0.618�ķ�Χ�ڣ���쳲���������ǰһ��ֵ�����ԣ���Ҫ k--
				high = mid - 1;
				k -= 1;
			}
			else if (temp[mid] < key)
			{
				//��ʱ˵��ֵ���ұ����򣬼���0.372�ķ�Χ�ڣ���쳲���������ǰ����ֵ�����ԣ���Ҫ k = k - 2
				low = mid + 1;
				k -= 2;
			}
			else 
			{
				//��Ϊ���������ԭ�򣬷���Խ��
				if (mid <= high)
				{
					return mid;
				}
				else return high;
			}
		}
		//���һֱû�ҵ�
		return -1;
	}

	public static void main(String[] args) 
	{
		int[] arr = {1, 8, 10, 89, 1000, 1234};
		
		int key = 1234;
		System.out.println(key + "�������ǣ�" + FibonacciSearch.fibonacciSearch(arr, key));
	}
}