import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class BubbleSortOptimization 
{
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

	//冒泡排序算法
	public static int[] bubbleSort(int[] arr)
	{
		int temp = 0;											//负责交换值的临时辅助变量
		boolean flag = false;									//判断该轮有没有交换， 如果没有则代表排序已经完成，不需要继续
		//对数据进行排序
		for (int i = 0; i < arr.length - 1; i++)
		{				
			for (int j = 0; j < arr.length - 1 - i; j++)
			{
				if (arr[j] > arr[j + 1])						//如果两个元素不符合排序顺序
				{
					flag = true;								//如果进行了交换，则置为true
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}

			//每一次遍历结束，判断是否排序已经结束
			if (flag == false)
			{
				return arr;
			}
			else
			{
				flag = false;									//排序还未完成，继续将flag置为默认值
			}

			//System.out.println("当前进度：" + ((float) ((i + 1) * 1.0 / arr.length)) + "%");
		}
		return arr;
	}

	public static void main(String[] args) 
	{
		//======================11111111111111111===================
		//保留最开始的时间
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println("程序最开始时的时间：" + simpleDateformat.format(startTime));

		//======================22222222222222222===================
		long time1 = System.currentTimeMillis();

		//对数组初始化
		int arrSize = 80000;
		int[] arr = BubbleSortOptimization.getArray(arrSize, 100000);
		
		//======================22222222222222222===================
		//统计初始化结束的时间
		long time2 = System.currentTimeMillis();
		System.out.println("大小为" + arrSize + "的数组生成的时间是" + (time2 - time1) + "ms");

		//======================11111111111111111===================
		//冒泡开始的时间
		Date startBubbleTime = new Date();
		System.out.println("开始冒泡排序的时间：" + simpleDateformat.format(startBubbleTime));

		//冒泡排序
		BubbleSortOptimization.bubbleSort(arr);

		//======================22222222222222222===================
		System.out.println("冒泡排序花费的时间：" + (System.currentTimeMillis() - time2) + "ms");
		//======================11111111111111111===================
		//冒泡结束的时间
		Date endTime = new Date();
		System.out.println("冒泡排序结束的时间：" + simpleDateformat.format(endTime));
	}
}
