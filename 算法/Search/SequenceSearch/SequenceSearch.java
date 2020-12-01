public class SequenceSearch 
{
	private static int[] arr = {1, 8, 10, 89, 1000, 1234};
	

	//线性搜索较为简单
	//思想就是按照顺序遍历数组，找到需要找到的值就返回此时的索引值即可
	//否则返回-1
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
		System.out.println(value1 +"在数组中的位置是：" + SequenceSearch.seqSearch(arr, value1));
		System.out.println(value2 +"在数组中的位置是：" + SequenceSearch.seqSearch(arr, value2));
		System.out.println(value3 +"在数组中的位置是：" + SequenceSearch.seqSearch(arr, value3));
	}
}
