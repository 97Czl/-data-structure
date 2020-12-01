import java.util.Arrays;

public class SelectSortCraft 
{
	//����ѡ������ķ���
	public static void selectSort(int[] arr)
	{
		//������
		//��k�����򣺱���arr[k]~arr[n-1]����Ԫ�أ�Ȼ���ҳ���Сֵ����arr[k]����
		int index = -1;														//��¼��Сֵ������
		int temp = 0;														//������Сֵ��arr[k]����ʱ��������
		for (int i = 0; i < arr.length - 1; i++)
		{
			index = i;
			for (int j = i + 1; j < arr.length; j++)
			{
				//�ҳ���Сֵ
				if (arr[index] > arr[j])
				{
					index = j;
				}
			}
			//�����ǰ��Сֵ���ǵ�ǰ����ֵ������Ҫ����
			if (index != -1)
			{
				temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
			}
			else
			{
				//�������Ҫ��������ʼ������
				index = -1;
			}

			System.out.println("��" + (i + 1) + "������������Ϊ��\t" + Arrays.toString(arr));
		}
	}

	//��������г�ʼ�������ֵ
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

		System.out.println("��ʼ����Ϊ��\t\t" + Arrays.toString(array));

		SelectSortCraft.selectSort(array);
	}
}
