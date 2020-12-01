import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ShellSortInsert
{
	private static int ARRSIZE = 80000;
	private static int MAXNUM = 100000;

	//进行希尔排序的方法
	public static void shellSort(int[] arr)
	{
		int index = 0;																//当前判断的索引值
		int value = 0;																//需要插入排序的变量

		//修改成for循环
		//对不在同一组的所有前面元素后一位开始遍历
		for (int gap = arr.length / 2; gap > 0; gap = gap / 2)
		{
			//第count+1次排序：gap = gap / 2,数组被分为length / gap组，所以从第gap个元素开始，即下标是gap
			for (int i = gap; i < arr.length; i++)
			{
				index = i;
				value = arr[index];
				//如果当前的元素比前一个（同组）的元素小
				//对该元素进行插入排序，该元素之前同组元素必为有序集合
				//保证 1)不越界 2)进行插入算法
				while (index - gap >= 0 && value < arr[index - gap])
				{
					//把数组的值往后移一位
					arr[index] = arr[index - gap];
					index -= gap;
				}
				if (index != i)
				{
					//将value插入到合适的位置
					arr[index] = value;
				}
			}
				
			//System.out.println("第x次排序后的数组为：\t" + Arrays.toString(arr));
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

		int[] array = ShellSortInsert.getArray(ARRSIZE, MAXNUM);
		//int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		
		//System.out.println("初始数组为：\t\t" + Arrays.toString(array));

		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("希尔插入排序开始时的时间：" + simpleDateformat.format(startTime));


		ShellSortInsert.shellSort(array);

		System.out.println("希尔插入排序花费的时间：" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println("希尔插入排序结束时的时间：" + simpleDateformat.format(endTime)); 
	}
}