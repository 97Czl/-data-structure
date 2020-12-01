import java.util.Arrays;

public class BubbleSortDemo 
{
	private static int ARRSIZE = 5;
	private static int MAXNUM = 100;
	private int[] arr = new int[ARRSIZE];
	
	//对数组进行初始化随机赋值
	private void init()
	{
		for (int i = 0; i < ARRSIZE; i++)
		{
			arr[i] = (int) (Math.random() * MAXNUM);
		}
	}

	public static void main(String[] args) 
	{
		BubbleSortDemo bsd = new BubbleSortDemo();
		bsd.init();
		System.out.println("原始数组为\n" + Arrays.toString(bsd.arr));

		//对数据进行排序
		//第一次排序,遍历元素为数组大小 - 1
		int temp = 0;										//负责交换值的临时辅助变量
		for (int j = 0; j < ARRSIZE - 1; j++)
		{
			if (bsd.arr[j] > bsd.arr[j + 1])						//如果两个元素不符合排序顺序
			{
				temp = bsd.arr[j];
				bsd.arr[j] = bsd.arr[j + 1];
				bsd.arr[j + 1] = temp;
			}
		}

		System.out.println("第一次排序的结果是\n" + Arrays.toString(bsd.arr));
	}
}
