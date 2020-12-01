import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class QuickSort 
{
	private static int ARRSIZE = 800000;
	private static int MAXNUM = ARRSIZE / 8 * 10;

	//快速排序算法
	public static void quickSort(int[] array, int left, int right)
	{
		//首选选取中间位置的元素作为对比基准
		//并创建两个变量保存两个输入值，放置修改
		int l = left;
		int r = right;
		int pivot = array[(left + right) / 2];
		int temp = 0;														//作为交换的辅助临时变量

		//当 l和r 没有碰面，即遍历没有全部遍历完，则继续
		while (l < r)
		{
			//首先左边 l 开始遍历，找到比 pivot 大的元素，或者到达pivot本身，即退出
			//退出循环时，此时的 l 处的值就 大于等于 pivot
			while (array[l] < pivot)
			{
				//如果当前的值满足条件，l 往后移一位
				l++;
			}

			//首先右边 r 开始遍历，找到比 pivot 小的元素，或者到达pivot本身，即退出
			//退出循环时，此时的 r 处的值就 小于等于 pivot
			while (array[r] > pivot)
			{
				//如果当前的值满足条件，r 往前移一位
				r--;
			}

			//找到了当前的值，如果 l == r,则没必要操作，可直接退出
			if (l == r)
			{
				break;
			}

			//如果不是对标到了同一个元素，则将两个元素交换
			temp = array[l];
			array[l] = array[r];
			array[r] = temp;
			
			
			//换完以后，如果换的元素等于中间索引的值，则该方向已经到头了，另一个位置需要移动一下
			//否则如果两端刚好都出现等于这个值，则死循环了
			if (array[l] == pivot)
			{
				r--;
			}
			if (array[r] == pivot)
			{
				l++;
			}
		}

		//当移动结束后，l 和 r 指向同一个位置，所以 应该各自移动一下，否则会死循环
		if (l == r)
		{
			l++;
			r--;
		}
		//System.out.println("当前结果是： \t" + Arrays.toString(array));
		
		//如果左边的元素还有大于一个的，说明还需要继续递归
		if (left < r)
		{
			//遍历结束后，左边遍历
			quickSort(array, left, r);
		}
		
		//如果右边的元素还有大于一个的，说明还需要继续递归
		//否则二者相等，说明元素遍历结束
		if (l < right)
		{
			//右边遍历
			quickSort(array, l, right);
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
		int[] array = QuickSort.getArray(ARRSIZE, MAXNUM);
		//int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		
		//System.out.println("初始数组为：\t\t" + Arrays.toString(array));

		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("快速排序开始时的时间：" + simpleDateformat.format(startTime));


		QuickSort.quickSort(array, 0, array.length - 1);

		System.out.println("快速排序花费的时间：" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println("快速排序结束时的时间：" + simpleDateformat.format(endTime));
	}
}
