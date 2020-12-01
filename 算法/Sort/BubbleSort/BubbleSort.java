import java.util.Arrays;

public class BubbleSort 
{
	private static int ARRSIZE = 5;
	private static int MAXNUM = 100;
	
	//对数组进行初始化随机赋值
	public int[] init()
	{
		int[] arr = new int[ARRSIZE];
		for (int i = 0; i < ARRSIZE; i++)
		{
			arr[i] = (int) (Math.random() * MAXNUM);
		}
		return arr;
	}

	//冒泡排序算法
	public static int[] bubbleSort(int[] arr)
	{
		int temp = 0;											//负责交换值的临时辅助变量
		//对数据进行排序
		for (int i = 0; i < arr.length - 1; i++)
		{				
			for (int j = 0; j < arr.length - 1 - i; j++)
			{
				if (arr[j] > arr[j + 1])						//如果两个元素不符合排序顺序
				{
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			System.out.println("第" + (i + 1) + "次排序的结果是：" + Arrays.toString(arr));
		}
		return arr;
	}

	public static void main(String[] args) 
	{
		BubbleSort bsd = new BubbleSort();
		int[] arr = bsd.init();
		System.out.println("原始数组为：" + Arrays.toString(arr));

		BubbleSort.bubbleSort(arr);
	}
}
