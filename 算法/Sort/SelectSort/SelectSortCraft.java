import java.util.Arrays;

public class SelectSortCraft 
{
	//进行选择排序的方法
	public static void selectSort(int[] arr)
	{
		//方法：
		//第k次排序：遍历arr[k]~arr[n-1]所有元素，然后找出最小值，与arr[k]交换
		int index = -1;														//记录最小值的索引
		int temp = 0;														//交换最小值和arr[k]的临时辅助变量
		for (int i = 0; i < arr.length - 1; i++)
		{
			index = i;
			for (int j = i + 1; j < arr.length; j++)
			{
				//找出最小值
				if (arr[index] > arr[j])
				{
					index = j;
				}
			}
			//如果当前最小值不是当前的数值，则需要交换
			if (index != -1)
			{
				temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
			}
			else
			{
				//如果不需要交换，初始化变量
				index = -1;
			}

			System.out.println("第" + (i + 1) + "次排序后的数组为：\t" + Arrays.toString(arr));
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
		int[] array = SelectSortCraft.getArray(20, 100);

		System.out.println("初始数组为：\t\t" + Arrays.toString(array));

		SelectSortCraft.selectSort(array);
	}
}
