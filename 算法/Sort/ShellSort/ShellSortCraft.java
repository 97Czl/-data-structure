import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ShellSortCraft 
{
	private static int ARRSIZE = 10;
	private static int MAXNUM = 100;

	//进行希尔排序的方法
	public static void shellSortCraft(int[] arr)
	{
		int j = 0;																//当前判断的索引值
		int temp = 0;															//交换的临时变量
		//方法：
		//第1次排序：gap = length / 2,数组被分为5组，所以从第六个元素开始，即下标是5
		for (int i = 5; i < arr.length; i++)
		{
			//刚开始 j = i , 然后让它与同组的前面的值进行判断
			//需要保证j - gap不会越界
			for (j = i; j - 5 >= 0; j -= 5)
			{
				if (arr[j] < arr[j - 5])
				{
					//如果arr[j] < arr[j - gap]，那么将两个值交换
					temp = arr[j];
					arr[j] = arr[j - 5];
					arr[j - 5] = temp;
				}
			}
		}
			
		System.out.println("第1次排序后的数组为：\t" + Arrays.toString(arr));

		//第2次排序：gap = gap / 2 = 2,数组被分为2组，所以从第3个元素开始，即下标是2
		for (int i = 2; i < arr.length; i++)
		{
			//刚开始 j = i , 然后让它与同组的前面的值进行判断
			//需要保证j - gap不会越界
			for (j = i; j - 2 >= 0; j -= 2)
			{
				if (arr[j] < arr[j - 2])
				{
					//如果arr[j] < arr[j - gap]，那么将两个值交换
					temp = arr[j];
					arr[j] = arr[j - 2];
					arr[j - 2] = temp;
				}
			}
		}
			
		System.out.println("第2次排序后的数组为：\t" + Arrays.toString(arr));

		//第3次排序：gap = gap / 2 = 1,数组被分为10组，所以从第2个元素开始，即下标是1
		for (int i = 1; i < arr.length; i++)
		{
			//刚开始 j = i , 然后让它与同组的前面的值进行判断
			//需要保证j - gap不会越界
			for (j = i; j - 1 >= 0; j -= 1)
			{
				if (arr[j] < arr[j - 1])
				{
					//如果arr[j] < arr[j - gap]，那么将两个值交换
					temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
		}
			
		System.out.println("第3次排序后的数组为：\t" + Arrays.toString(arr));
	}

	//对数组进行初始化随机赋值
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

		//int[] array = ShellSortCraft.getArray(ARRSIZE, MAXNUM);
		int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		
		System.out.println("初始数组为：\t\t" + Arrays.toString(array));
		
		ShellSortCraft.shellSortCraft(array);
	}
}
