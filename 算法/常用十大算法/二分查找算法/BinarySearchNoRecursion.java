public class BinarySearchNoRecursion 
{
	//不需要递归的二分查找算法
	public static int binarySearch(int[] arr, int target)
	{
		//左右边界点
		int left = 0;
		int right = arr.length - 1;

		while (left <= right)
		{
			//只要范围内还有数据
			int mid = (left + right) / 2;

			//判断要查找的值是在哪一个范围内
			if (arr[mid] == target)
			{
				return mid;
			}
			else if (arr[mid] < target)
			{
				//如果值在中间值的右侧
				left = mid + 1;
			}
			else
			{
				//如果值在中间值的左侧
				right = mid - 1;
			}
		}
		//如果查找结束还是没找到
		return -1;
	}

	public static void main(String[] args) 
	{
		int arr[] = new int[]{1, 3, 8, 11, 15, 45, 56, 67};

		int index = binarySearch(arr, 5);

		System.out.println("index = " + index);
	}
}
