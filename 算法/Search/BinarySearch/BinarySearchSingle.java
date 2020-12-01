//�ó���ֻ�����״��ҵ���Ҫ���ҵ�Ԫ�ص�����

public class BinarySearchSingle
{
	private static int[] arr = {1, 8, 10, 89, 1000, 1234};
	
	//���ַ�Ҫ���������Ϊ�������飬��������������飬��Ҫ���ȶ������������
	//���ַ���˼�뼴ÿ�ν�����һ��Ϊ����ͨ���ж��м�ֵ��ȷ��Ҫ���ҵ�ֵ����һ������
	//�����ݹ�󣬼���������ֵ��λ�ã����߲����ڸ�ֵ
	//1.��������м�����mid �� �м�������ֵmidValue
	//2.�ж�midValue �� findValue�Ĵ�С
	// 2.1��� midValue > findValue����˵��Ҫ�ҵ�ֵλ�ڵ�ǰ�������࣬�������ݹ�
	// 2.2��� midValue < findValue����˵��Ҫ�ҵ�ֵλ�ڵ�ǰ������Ҳ࣬�����Ҳ�ݹ�
	// 2.3��� midValue = findValue���򷵻ص�ǰ����
	//3.�˳�������
	// 3.1�ҵ������ͬ2.3
	// 3.2û���ҵ������ݹ��left > right
	public static int binarySearch(int[] arr, int left, int right, int findValue)
	{
		//����Ӧ���жϵ�ǰʱ�����ִ�и��㷨����3.2�����Ƿ�����
		if (left > right)
		{
			return -1;
		}
		//��������Լ������ҵĻ������1�е�����ֵ
		int mid = (left + right) / 2;
		int midValue = arr[mid];

		//ִ��2���ж������Ȼ��ѡ��·��
		//��ʱ��mid +1/-1 �Ĳ����Ǳ�����һ�εݹ�����Ȼ�ж�mid����ֵ����������˷�
		if (midValue > findValue)
		{
			return binarySearch(arr, left, mid - 1, findValue);
		}
		else if (midValue < findValue)
		{
			return binarySearch(arr, mid + 1, right, findValue);
		}
		else 
		{
			//��ʱ˵��midValue = findValue�����ص�ǰ����
			return mid;
		}
	}

	public static void main(String[] args) 
	{
		int value1 = 8;
		int value2 = -2;
		int value3 = 10;
		System.out.println(value1 +"�������е�λ���ǣ�" + BinarySearchSingle.binarySearch(arr, 0, arr.length, value1));
		System.out.println(value2 +"�������е�λ���ǣ�" + BinarySearchSingle.binarySearch(arr, 0, arr.length, value2));
		System.out.println(value3 +"�������е�λ���ǣ�" + BinarySearchSingle.binarySearch(arr, 0, arr.length, value3));
	}
}
