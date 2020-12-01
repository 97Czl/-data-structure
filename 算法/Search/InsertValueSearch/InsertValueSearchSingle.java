//�ó���ֻ�����״��ҵ���Ҫ���ҵ�Ԫ�ص�����

public class InsertValueSearchSingle
{
	private static int[] arr = {1, 8, 10, 89, 1000, 1234};
	
	//��ֵ���ҷ�Ҫ���������Ϊ�������飬��������������飬��Ҫ���ȶ������������
	//��ֵ���ҷ���˼�뼴ÿ�ν����鰴�ղ�ֵ˼��ȷ��midֵ
	//�����ݹ�󣬼���������ֵ��λ�ã����߲����ڸ�ֵ
	//1.���������ֵ����mid �� ������ֵmidValue
	//2.�ж�midValue �� findValue�Ĵ�С
	// 2.1��� midValue > findValue����˵��Ҫ�ҵ�ֵλ�ڵ�ǰ�������࣬�������ݹ�
	// 2.2��� midValue < findValue����˵��Ҫ�ҵ�ֵλ�ڵ�ǰ������Ҳ࣬�����Ҳ�ݹ�
	// 2.3��� midValue = findValue���򷵻ص�ǰ����
	//3.�˳�������
	// 3.1�ҵ������ͬ2.3
	// 3.2û���ҵ������ݹ��left > right
	public static int insertValueSearch(int[] arr, int left, int right, int findValue)
	{
		//����Ӧ���жϵ�ǰʱ�����ִ�и��㷨����3.2�����Ƿ�����
		//=================================================
		//������Ҫ�жϲ��ҵ�ֵ�ڲ��ڷ�Χ������mid�����
		//=================================================
		if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1])
		{
			return -1;
		}
		//��������Լ������ҵĻ������1�е�����ֵ
		int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
		int midValue = arr[mid];

		//ִ��2���ж������Ȼ��ѡ��·��
		//��ʱ��mid +1/-1 �Ĳ����Ǳ�����һ�εݹ�����Ȼ�ж�mid����ֵ����������˷�
		if (midValue > findValue)
		{
			return insertValueSearch(arr, left, mid - 1, findValue);
		}
		else if (midValue < findValue)
		{
			return insertValueSearch(arr, mid + 1, right, findValue);
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
		System.out.println(value1 +"�������е�λ���ǣ�" + InsertValueSearchSingle.insertValueSearch(arr, 0, arr.length - 1, value1));
		System.out.println(value2 +"�������е�λ���ǣ�" + InsertValueSearchSingle.insertValueSearch(arr, 0, arr.length - 1, value2));
		System.out.println(value3 +"�������е�λ���ǣ�" + InsertValueSearchSingle.insertValueSearch(arr, 0, arr.length - 1, value3));
	}
}