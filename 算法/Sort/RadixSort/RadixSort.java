import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RadixSort 
{
	private static int ARRSIZE = 8000000;
	private static int MAXNUM = ARRSIZE / 8 * 10;

	//基数排序的方法，按照步骤先理解思路
	public static void radixSort(int[] arr)
	{
		//1.首先需要十个桶来 临时存放每个位的数值对应的原始数据
		//2.可以用二维数组来实现，数组的行代表该位的值，对应的列代表 该桶存放的数据的索引
		//3.这样就需要一个大小为10的数组来记录每一轮桶里存放了多少数据
		//---所谓基数排序，是使用空间来换取时间，获得更高的效率
		int[][] bucket = new int[10][arr.length];											//为了保证数组不会越界，考虑极端情况，所以数组大小必须设置成和原始数组一样大
		int[] bucketSize = new int[10];														//记录对应的每一个桶存放了多少数据
		
		//将多次循环封装成for循环
		//就需要确定循环的次数，即数组里的最高位
		//1.找到数组里的最大值
		int max = arr[0];
		for (int i = 0; i < arr.length; i++)
		{
			if (max < arr[i])
			{
				max = arr[i];
			}
		}
		//找到最大值后，求出他的长度
		//******************************妙啊*************************************
		int lengthOfMax = (max + "").length();
		//******************************妙啊*************************************

		//这里需要注意****************************如何确定每次的每一位，for循环可以添加多个变量
		for (int i = 0, n = 1; i < lengthOfMax; i++, n *= 10)
		{
			int element = 0;
			//第i轮，针对每个元素的该位值来将各个元素按照顺序放入对应的桶中
			//遍历结束后将所有元素放回数组
			for (int j = 0; j <arr.length; j++)
			{
				element = arr[j] / n % 10;
				bucket[element][bucketSize[element]] = arr[j];
				bucketSize[element] += 1; 
			}
			//此时已经将所有元素都按照要求放入桶中
			//接下来将所有元素再放回去
			int index = 0;																	//记录原始数组的下标索引
			for (int k = 0; k < bucketSize.length; k++)
			{
				if (bucketSize[k] != 0)														//如果该元素不为零，即对应的位数上面有元素放入
				{
					for (int l = 0; l < bucketSize[k]; l++)
					{
						arr[index] = bucket[k][l];
						index++;
					}
				}
				//该位的这个值的所有数字都放完后，将索引置零
				bucketSize[k] = 0;
			}
			//输出此时的数组
			//System.out.println("第一次排序后的数组：\t" + Arrays.toString(arr));
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

		int[] array = RadixSort.getArray(ARRSIZE, MAXNUM);
		//int[] array = new int[]{53, 3, 542, 748, 14, 214};
		
		//System.out.println("初始数组为：\t\t" + Arrays.toString(array));

		long time1 = System.currentTimeMillis();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date();
		System.out.println(ARRSIZE + "个数字基数排序开始时的时间：" + simpleDateformat.format(startTime));

		RadixSort.radixSort(array);

		System.out.println(ARRSIZE + "个数字基数排序花费的时间：" + (System.currentTimeMillis() - time1) + "ms");
		Date endTime = new Date();
		System.out.println(ARRSIZE + "个数字基数排序结束时的时间：" + simpleDateformat.format(endTime));
	}
}
