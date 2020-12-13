import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HeapSortCraft2 
{
	private static int ARRSIZE = 8000000;
	private static int MAXNUM = ARRSIZE / 8 * 10;

	//升序堆排序的方法，使用大顶堆
	public static void heapSortIncrease(int[] arr)
	{
		//获取每一次需要处理元素的数量
		int number = arr.length;
		//交换位置的辅助变量
		int temp = 0;
		for (; number > 0 ; number--)
		{
			//第一次首先对数组全部元素遍历找出非叶子节点，并将树调整为大顶堆的格式
			for (int i = number - 1; i >= 0; i--)
			{
				//非叶子节点说明该节点的左右子节点都在数组范围内
				if ((2 * i + 1) < arr.length && (2 * i + 2) < arr.length)
				{
					//1.两边元素都还有待排序， 
					if ((2 * i + 1) < number && (2 * i + 2) < number)
					{
						//子节点谁大换谁
						if (arr[2 * i + 1] < arr[2 * i + 2] && arr[i] <  arr[2 * i + 2])
						{
							int tmp = arr[i];
							arr[i] = arr[2 * i + 2];
							arr[2 * i + 2] = tmp;
						}
						else if(arr[2 * i + 2] < arr[2 * i + 1] && arr[i] <  arr[2 * i + 1])
						{
							int tmp = arr[i];
							arr[i] = arr[2 * i + 1];
							arr[2 * i + 1] = tmp;
						}
					}
					//2.一边已经确定，不能交换，
					else if ((2 * i + 1) < number && arr[i] < arr[2 * i + 1])
					{
						//左边能换，且是较大值
						int tmp = arr[i];
						arr[i] = arr[2 * i + 1];
						arr[2 * i + 1] = tmp;
					}
					else if ((2 * i + 2) < number && arr[i] < arr[2 * i + 2])
					{
						//右边能换，且是较大值
						int tmp = arr[i];
						arr[i] = arr[2 * i + 2];
						arr[2 * i + 2] = tmp;
					}
					//3.两边都已经确定，不做处理
				}
			}
			//将最大的数字放到最后
			temp = arr[0];
			arr[0] = arr[number - 1];
			arr[number - 1] = temp;
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

		//int[] array = HeapSortCraft2.getArray(ARRSIZE, MAXNUM);
		int[] array = new int[]{53, 3, 542, 748, 14, 214, 328, 9, 1000};
		
		System.out.println("初始数组为：\t\t" + Arrays.toString(array));
		
		/*
		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println(ARRSIZE + "个数字堆排序开始时的时间：" + simpleDateformat.format(startTime));

		HeapSortCraft2.heapSortIncrease(array);

		System.out.println(ARRSIZE + "个数字堆排序花费的时间：" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println(ARRSIZE + "个数字堆排序结束时的时间：" + simpleDateformat.format(endTime));
		*/

		HeapSortCraft2.heapSortIncrease(array);
		System.out.println("排序后的数组为：\t\t" + Arrays.toString(array));
	}
}
