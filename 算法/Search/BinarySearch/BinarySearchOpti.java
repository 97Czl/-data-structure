import java.util.ArrayList;
//该程序可以找到需要查找的元素的所有索引

public class BinarySearchOpti
{
	private static int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
	
	//二分法要求数组必须为有序数组，如果不是有序数组，需要首先对数组进行排列
	//二分法的思想即每次将数组一分为二，通过判断中间值来确定要查找的值在哪一个区间
	//经过递归后，即可锁定该值的位置，或者不存在该值
	//1.首先求出中间索引mid 和 中间索引的值midValue
	//2.判断midValue 和 findValue的大小
	// 2.1如果 midValue > findValue，则说明要找的值位于当前数组的左侧，即向左侧递归
	// 2.2如果 midValue < findValue，则说明要找的值位于当前数组的右侧，即向右侧递归
	// 2.3如果 midValue = findValue，则返回当前索引
	//3.退出的条件
	// 3.1******************************************************************************************
	//		找到结果，这里需要多一步，判断当前元素左边和右边有没有相同的值，如果有，添加到链表中返回
	//**********************************************************************************************
	// 3.2没有找到，即递归后left > right
	public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int findValue)
	{
		//首先应该判断当前时候继续执行该算法，即3.2条件是否满足
		if (left > right)
		{
			return new ArrayList<Integer>();
		}
		//如果还可以继续查找的话，求出1中的两个值
		int mid = (left + right) / 2;
		int midValue = arr[mid];

		//执行2，判断情况，然后选择路径
		//此时对mid +1/-1 的操作是避免下一次递归是仍然判断mid处的值，造成运算浪费
		if (midValue > findValue)
		{
			return binarySearch(arr, left, mid - 1, findValue);
		}
		else if (midValue < findValue)
		{
			return binarySearch(arr, mid + 1, right, findValue);
		}
		else 
		{
			//辅助遍历的索引
			int temp = mid - 1;
			//此时说明midValue = findValue，返回当前索引
			ArrayList<Integer> list = new ArrayList<>();
			//首先看mid左边的值有没有相等的，如果有添加到链表中
			while (true)
			{
				//如果遍历到了数组最前面还没有，或者已经找到了和当前midValue不等的值，遍历结束
				if (temp < 0 || arr[temp] != findValue)
				{
					break;
				}
				//如果当前值满足条件，添加到链表，并更新遍历索引‘
				list.add(temp);
				temp -= 1;
			}

			//左边结束后，将最初找的值加进去
			list.add(mid);
			temp = mid + 1;
			//其次看mid右边的值有没有相等的，如果有添加到链表中
			while (true)
			{
				//如果遍历到了数组最尾部还没有，或者已经找到了和当前midValue不等的值，遍历结束
				if (temp > arr.length || arr[temp] != findValue)
				{
					break;
				}
				//如果当前值满足条件，添加到链表，并更新遍历索引‘
				list.add(temp);
				temp += 1;
			}
			return list;
		}
	}

	public static void main(String[] args) 
	{
		int value1 = 8;
		int value2 = -2;
		int value3 = 1000;
		System.out.println(value1 +"在数组中的位置是：" + BinarySearchOpti.binarySearch(arr, 0, arr.length, value1));
		System.out.println(value2 +"在数组中的位置是：" + BinarySearchOpti.binarySearch(arr, 0, arr.length, value2));
		System.out.println(value3 +"在数组中的位置是：" + BinarySearchOpti.binarySearch(arr, 0, arr.length, value3));
	}
}
