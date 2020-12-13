import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HeapSort 
{
    private static int ARRSIZE = 8000000;
    private static int MAXNUM = ARRSIZE / 8 * 10;

	//将一个数组（二叉树），调整成一个大顶堆
	/**
	 *功能：完成将以i对应的非叶子节点的数调整成大顶堆
	 *例如 int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
	 *如果再次调用adjustHeap传入的是i = 0 => 得到{4, 9, 8, 5, 6} => {9, 6, 8, 5, 4}
	 *@param arr 待调整的数组
	 *@param i 表示非叶子节点在数组中的索引
	 *@param length 代表对多少个元素继续调整，length是在逐渐的减少
	 */
	private static void adjustHeap(int arr[], int i, int length)
	{
		int temp = arr[i];	//先取出当前的元素，保存在临时变量

		//开始调整
		//1。 k = 2 * i + 1 是i节点的左子节点
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1)
		{
			if (k + 1 < length && arr[k] < arr[k + 1]) //说明左子节点的值小于右子节点的值
			{
				k++;	//k指向较大的值
			}
			//这里其实确定了对于节点i来说，左右子节点那个子节点的值较大
			//然后 下一个if和else是为了将对应的较大的子节点下面的大值往上移动一层(每次调用这个函数所有较大的子节点只移动一层，i处的元素会移动到不可移动时的最底层)，直到左右子节点都比较小
			if (arr[k] > temp)	//如果子节点的值大于父节点
			{
				arr[i] = arr[k];
				i = k;	//！！！！！！！！！！！！！！！！！！！！！！！！！！！！！继续循环比较
			}
			else 
			{
				break;
			}
		}
		//for循环结束后，以i为父节点的树的最大值已经放在了顶部
		arr[i] = temp;

		//其实只调整了一个节点的左右子节点，如果子节点的值大，则换上来
	}

	public static void heapSort(int[] arr)
	{
		int temp = 0;
		//首先第一步，将无序的序列调整成一个堆
		for (int i = arr.length / 2 - 1; i >= 0; i--)
		{
			adjustHeap(arr, i, arr.length);
			System.out.println("排序后的数组为：\t\t" + Arrays.toString(array));
		}

		for (int j = arr.length - 1; j > 0; j--)
		{
			//交换数字
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
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

        //int[] array = HeapSort.getArray(ARRSIZE, MAXNUM);
        int[] array = new int[]{53, 3, 542, 748, 14, 214, 328, 9, 1000};

        //System.out.println("初始数组为：\t\t" + Arrays.toString(array));


        long time1 = System.currentTimeMillis();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        System.out.println(ARRSIZE + "个数字堆排序开始时的时间：" + simpleDateformat.format(startTime));

        HeapSort.heapSort(array);

        System.out.println(ARRSIZE + "个数字堆排序花费的时间：" + (System.currentTimeMillis() - time1) + "ms");
        Date endTime = new Date();
        System.out.println(ARRSIZE + "个数字堆排序结束时的时间：" + simpleDateformat.format(endTime));

        //System.out.println("排序后的数组为：\t\t" + Arrays.toString(array));
    }
}
