public class SequenceSearch 
{
	private static int[] arr = {1, 8, 10, 89, 1000, 1234};
	

	//����������Ϊ��
	//˼����ǰ���˳��������飬�ҵ���Ҫ�ҵ���ֵ�ͷ��ش�ʱ������ֵ����
	//���򷵻�-1
	public static int seqSearch(int[] arr, int findValue)
	{
		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] == findValue)
			{
				return i;
			}
		}
		return  -1;
	}

	public static void main(String[] args) 
	{
		int value1 = 8;
		int value2 = -2;
		int value3 = 10;
		System.out.println(value1 +"�������е�λ���ǣ�" + SequenceSearch.seqSearch(arr, value1));
		System.out.println(value2 +"�������е�λ���ǣ�" + SequenceSearch.seqSearch(arr, value2));
		System.out.println(value3 +"�������е�λ���ǣ�" + SequenceSearch.seqSearch(arr, value3));
	}
}
