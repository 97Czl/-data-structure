import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;

public class InsertSortCraft 
{
	
	private static int ARRSIZE = 10;
	private static int MAXNUM = 100;

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
	
	//插入排序算法
	public static int[] insertSort(int[] arr)
	{
		//定义辅助变量
		int insertVal = 0;
		int insertIndex = 0;
		//逐步来分析

		//第一步：
		//1.需要被插入的元素从第二个开始 即保存当前的主角值 insertVal = arr[1];
		//2.往回遍历，如果小，就交换位置，一直到合适的位置，所以插入的索引值应该减一  insertIndex = 1 - 1；
		//3.找到合适的位置，最后将数值插入
		insertVal = arr[1];
		insertIndex = 1 - 1;
		//insertIndex >= 0  -------------- 防止数组越界
		//insertVal < arr[insertIndex] ---------- 当前的值没有在正确的位置，则需要继续往前遍历
		while (insertIndex >= 0 && insertVal < arr[insertIndex])  
		{
			//如果indexVal 没有在合适的位置，即他比他前一个元素要小，则需要将前一位往后移，然后将索引前置
			arr[insertIndex + 1] = arr[insertIndex];					//将前面的数值往后移一位
			insertIndex--;												//将索引往前一位
		}

		//最后找到了合适的位置，当前索引的值 和 索引+1 的值 在insertVal中间，
		//所以需要将要插入的值insertVal插入到insertIndex + 1的位置
		arr[insertIndex + 1] = insertVal;

		//输出当前数组
		System.out.println("第一次遍历后的数组为：" + Arrays.toString(arr) + "\n");

		//第二步：
		//1.需要被插入的元素从第三个开始 即保存当前的主角值 insertVal = arr[2];
		//2.往回遍历，如果小，就交换位置，一直到合适的位置，所以插入的索引值应该减一  insertIndex = 2 - 1；
		//3.找到合适的位置，最后将数值插入
		insertVal = arr[2];
		insertIndex = 2 - 1;
		//insertIndex >= 0  -------------- 防止数组越界
		//insertVal < arr[insertIndex] ---------- 当前的值没有在正确的位置，则需要继续往前遍历
		while (insertIndex >= 0 && insertVal < arr[insertIndex])  
		{
			//如果indexVal 没有在合适的位置，即他比他前一个元素要小，则需要将前一位往后移，然后将索引前置
			arr[insertIndex + 1] = arr[insertIndex];					//将前面的数值往后移一位
			insertIndex--;												//将索引往前一位
		}

		//最后找到了合适的位置，当前索引的值 和 索引+1 的值 在insertVal中间，
		//所以需要将要插入的值insertVal插入到insertIndex + 1的位置
		arr[insertIndex + 1] = insertVal;

		//输出当前数组
		System.out.println("第二次遍历后的数组为：" + Arrays.toString(arr) + "\n");


		//第三步：
		//1.需要被插入的元素从第四个开始 即保存当前的主角值 insertVal = arr[3];
		//2.往回遍历，如果小，就交换位置，一直到合适的位置，所以插入的索引值应该减一  insertIndex = 3 - 1；
		//3.找到合适的位置，最后将数值插入
		insertVal = arr[3];
		insertIndex = 3 - 1;
		//insertIndex >= 0  -------------- 防止数组越界
		//insertVal < arr[insertIndex] ---------- 当前的值没有在正确的位置，则需要继续往前遍历
		while (insertIndex >= 0 && insertVal < arr[insertIndex])  
		{
			//如果indexVal 没有在合适的位置，即他比他前一个元素要小，则需要将前一位往后移，然后将索引前置
			arr[insertIndex + 1] = arr[insertIndex];					//将前面的数值往后移一位
			insertIndex--;												//将索引往前一位
		}

		//最后找到了合适的位置，当前索引的值 和 索引+1 的值 在insertVal中间，
		//所以需要将要插入的值insertVal插入到insertIndex + 1的位置
		arr[insertIndex + 1] = insertVal;

		//输出当前数组
		System.out.println("第三次遍历后的数组为：" + Arrays.toString(arr) + "\n");


		//第四步：
		//1.需要被插入的元素从第五个开始 即保存当前的主角值 insertVal = arr[4];
		//2.往回遍历，如果小，就交换位置，一直到合适的位置，所以插入的索引值应该减一  insertIndex = 4 - 1；
		//3.找到合适的位置，最后将数值插入
		insertVal = arr[4];
		insertIndex = 4 - 1;
		//insertIndex >= 0  -------------- 防止数组越界
		//insertVal < arr[insertIndex] ---------- 当前的值没有在正确的位置，则需要继续往前遍历
		while (insertIndex >= 0 && insertVal < arr[insertIndex])  
		{
			//如果indexVal 没有在合适的位置，即他比他前一个元素要小，则需要将前一位往后移，然后将索引前置
			arr[insertIndex + 1] = arr[insertIndex];					//将前面的数值往后移一位
			insertIndex--;												//将索引往前一位
		}

		//最后找到了合适的位置，当前索引的值 和 索引+1 的值 在insertVal中间，
		//所以需要将要插入的值insertVal插入到insertIndex + 1的位置
		arr[insertIndex + 1] = insertVal;

		//输出当前数组
		System.out.println("第四次遍历后的数组为：" + Arrays.toString(arr) + "\n");


		
		return arr;
	}


	public static void main(String[] args) 
	{
		//======================11111111111111111===================
		//保留最开始的时间
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		//System.out.println("程序最开始时的时间：" + simpleDateformat.format(startTime));

		//======================22222222222222222===================
		long time1 = System.currentTimeMillis();

		//对数组初始化
		int[] arr = InsertSortCraft.getArray(ARRSIZE, MAXNUM);

		//输出数组
		System.out.println("原始数组为：" + Arrays.toString(arr) + "\n");
		
		//======================22222222222222222===================
		//统计初始化结束的时间
		long time2 = System.currentTimeMillis();
		//System.out.println("大小为" + ARRSIZE + "的数组生成的时间是" + (time2 - time1) + "ms");

		//======================11111111111111111===================
		//插入开始的时间
		Date startBubbleTime = new Date();
		//System.out.println("开始插入排序的时间：" + simpleDateformat.format(startBubbleTime));

		//插入排序
		InsertSortCraft.insertSort(arr);

		//======================22222222222222222===================
		//System.out.println("插入排序花费的时间：" + (System.currentTimeMillis() - time2) + "ms");
		//======================11111111111111111===================
		//插入结束的时间
		Date endTime = new Date();
		//System.out.println("插入排序结束的时间：" + simpleDateformat.format(endTime));
	}
}
