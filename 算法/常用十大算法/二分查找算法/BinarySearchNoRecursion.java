public class BinarySearchNoRecursion 
{
	//����Ҫ�ݹ�Ķ��ֲ����㷨
	public static int binarySearch(int[] arr, int target)
	{
		//���ұ߽��
		int left = 0;
		int right = arr.length - 1;

		while (left <= right)
		{
			//ֻҪ��Χ�ڻ�������
			int mid = (left + right) / 2;

			//�ж�Ҫ���ҵ�ֵ������һ����Χ��
			if (arr[mid] == target)
			{
				return mid;
			}
			else if (arr[mid] < target)
			{
				//���ֵ���м�ֵ���Ҳ�
				left = mid + 1;
			}
			else
			{
				//���ֵ���м�ֵ�����
				right = mid - 1;
			}
		}
		//������ҽ�������û�ҵ�
		return -1;
	}

	public static void main(String[] args) 
	{
		int arr[] = new int[]{1, 3, 8, 11, 15, 45, 56, 67};

		int index = binarySearch(arr, 5);

		System.out.println("index = " + index);
	}
}
