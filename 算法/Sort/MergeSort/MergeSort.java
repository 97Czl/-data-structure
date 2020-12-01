import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MergeSort 
{
	private static int ARRSIZE = 800000;
	private static int MAXNUM = ARRSIZE / 8 * 10;

	public static void mergeSort(int[] arr, int left, int right, int[] temp)
	{
		//如果左边还小于右边索引，说明数组还可以继续划分
		if (left < right)
		{
			//首先定义中间值
			int mid = (left + right) / 2;
			//递归分别对左边和右边的数据继续进行分
			mergeSort(arr, left, mid, temp);
			mergeSort(arr, mid + 1, right, temp);
			merge(arr, left, mid, right, temp);
		}
	}

	//对已经分的数组进行合
	public static void merge(int[] arr, int left, int mid, int right, int[] temp)
	{
		int i = left;													//代表左边有序数组的索引值
		int j = mid + 1;												//代表右边有序数组的索引值
		int t = 0;														//临时数组的索引值

		//1.对左右两部分数组进行遍历，挨个取出较小的元素，直到一边达到尾部
		while (i <= mid && j <= right)
		{
			//判断取出较小的元素放入临时数组中
			if (arr[i] < arr[j])
			{
				temp[t] = arr[i];
				t += 1;
				i += 1;
			}
			else 
			{
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}

		//这里已经有一部分到达了尾部，说明另一部分都是最大的值，且是有序的，直接放入临时数组就可
		//2.判断哪边到了队尾，另一边直接放入临时数组
		while (i <= mid)
		{
			temp[t] = arr[i];
			t += 1;
			i += 1;
		}
		while (j <= right)
		{
			temp[t] = arr[j];
			t += 1;
			j += 1;
		}

		//3.此时数组已经全部排序结束，只需将有序的临时数组 copy 到数组参数即可
		t = 0;																//临时数组的索引值
		int tempLeft = left;												//作为传入数组的索引值，保证元素位置的正确性
		while (tempLeft <= right)
		{
			arr[tempLeft] = temp[t];
			tempLeft += 1;
			t += 1;
		}

		//输出当前数组
		//System.out.println("当前数组：\t" + Arrays.toString(arr));
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
		int[] array = MergeSort.getArray(ARRSIZE, MAXNUM);
		//int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		int[] temp = new int[ARRSIZE];
		
		//System.out.println("初始数组为：\t\t" + Arrays.toString(array));

		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("快速排序开始时的时间：" + simpleDateformat.format(startTime));


		MergeSort.mergeSort(array, 0, array.length - 1, temp);

		System.out.println("快速排序花费的时间：" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println("快速排序结束时的时间：" + simpleDateformat.format(endTime));
	}
}
