import java.util.Arrays;

public class FibonacciSearch 
{
	private static int MAXSIZE = 20;
	
	//产生MAXSIZE大小的斐波那契数列
	public static int[] fibs(int maxSize)
	{
		int f[] = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++)
		{
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}
	
	//斐波那契查找方法
	/**
	 *@para arr 要查找的数组，需有序数组
	 *@para key 要查找的值
	 */
	public static int fibonacciSearch(int[] arr, int key)
	{
		//首先定义关于数组的变量
		int low = 0;														//数组的下界
		int high = arr.length - 1;											//数组的上界
		int mid = 0;														//定位比较值的索引
		
		//关于斐波那契数列的变量
		int k = 0;															//根据数组大小获取的要用到的斐波那契数列的下标索引值
		int[] f = fibs(MAXSIZE);											//斐波那契数组

		//根据数组长度，获取 k 的值
		while (high > f[k] - 1)
		{
			k++;
		}
		//当前数组长度是小于等于f[k]的，所以需要扩充数组
		int[] temp = Arrays.copyOf(arr, f[k]);
		//为了实现有序数组，需要将多出来的位置全部赋成数组的最大值
		for (int i = high + 1; i < f[k]; i++)
		{
			temp[i] = arr[high];
		}

		//递归找到key的索引
		while (low <= high)
		{
			//首先确定比较的值
			mid = low + f[k - 1] - 1;
			if (temp[mid] > key)
			{
				//此时说明值在左边区域，即在0.618的范围内，是斐波那契数列前一个值，所以，需要 k--
				high = mid - 1;
				k -= 1;
			}
			else if (temp[mid] < key)
			{
				//此时说明值在右边区域，即在0.372的范围内，是斐波那契数列前两个值，所以，需要 k = k - 2
				low = mid + 1;
				k -= 2;
			}
			else 
			{
				//因为扩充数组的原因，放置越界
				if (mid <= high)
				{
					return mid;
				}
				else return high;
			}
		}
		//如果一直没找到
		return -1;
	}

	public static void main(String[] args) 
	{
		int[] arr = {1, 8, 10, 89, 1000, 1234};
		
		int key = 1234;
		System.out.println(key + "的索引是：" + FibonacciSearch.fibonacciSearch(arr, key));
	}
}