import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SelectSort 
{
	private static int ARRSIZE = 10000;
	private static int MAXNUM = 100000;

	//进行选择排序的方法
	public static void selectSort(int[] arr)
	{
		//方法：
		//第k次排序：遍历arr[k]~arr[n-1]所有元素，然后找出最小值，与arr[k]交换
		int minIndex = 0;														//记录最小值的索引
		int min = 0;															//交换最小值
		for (int i = 0; i < arr.length - 1; i++)
		{
			minIndex = i;
			min = arr[i];
			for (int j = i + 1; j < arr.length; j++)
			{
				//找出最小值
				if (arr[minIndex] > arr[j])
				{
					minIndex = j;
					min = arr[minIndex];
				}
			}
			//如果当前最小值不是当前的数值，则需要交换
			if (minIndex != i)
			{
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
			//System.out.println("第" + (i + 1) + "次排序后的数组为：\t" + Arrays.toString(arr));
		}
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

		int[] array = SelectSort.getArray(ARRSIZE, MAXNUM);
		
		//System.out.println("初始数组为：\t\t" + Arrays.toString(array));
		
		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("选择排序开始时的时间：" + simpleDateformat.format(startTime));


		SelectSort.selectSort(array);

		System.out.println("选择排序花费的时间：" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println("选择排序结束时的时间：" + simpleDateformat.format(endTime));
	}
}
