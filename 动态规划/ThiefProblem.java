/*
	打家劫舍问题：给定一个数组代表每户人家的可偷窃的金额，其中每两家之间有报警器，不能同时偷相邻的两家，否则会触发报警器
	如[1, 2, 3, 1, 3] 最多能偷取的金额是1,3,5户人家，总共1+3+3=7

	动态规划解决步骤
	1.定义原问题和子问题
	 原问题：前n个房间能偷盗的最大金额
	 子问题：前k(k<n)个房间能偷盗的最大金额
	2.定义状态
	 前k个房间能偷盗的最大金额为f(k) k即为状态
	3.定义状态转移方程
	         M1,						k==1;
	 f(n) =  max(M1, M2),				k==2;
			 max(f(k-1), f(k-2) + Mk),	k>=3;
	 k>=3时，要么k-1个房间最大值，要么k-2的最大值加上k房间
*/
import java.util.Arrays;
import java.util.Stack;

public class ThiefProblem 
{
	public static void main(String[] args) 
	{
		int[] roomInfo = new int[]{1,4,1,2,5,6,3};
		System.out.println("(DP)能偷盗的最大金额为：" + new ThiefProblem().steal(roomInfo));
		//递归方式
		System.out.println("(DG)能偷盗的最大金额为：" + new ThiefProblem().stealRecursion(roomInfo));
		//压缩状态
		System.out.println("(OP)能偷盗的最大金额为：" + new ThiefProblem().stealOptimal(roomInfo));
		//输出房间信息
		System.out.println("(GR)偷盗的房间 编号 为：" + Arrays.toString(new ThiefProblem().stealGetRoom(roomInfo)));
	}

	public int steal(int[] roomInfo)
	{
		int length = roomInfo.length;
		//采用自底向上的编程方式
		if(length == 1) return roomInfo[0];
		else if(length == 2) return Math.max(roomInfo[0], roomInfo[1]);
		else 
		{
			int[] maxRMB = new int[length];
			maxRMB[0] = roomInfo[0];
			maxRMB[1] = Math.max(roomInfo[0], roomInfo[1]);
			for (int k = 2; k < length; k++)
			{
				//如果偷取k-1
				int fore = maxRMB[k - 1];
				//偷取k-2 + k
				int rear = maxRMB[k - 2] + roomInfo[k];
				maxRMB[k] = Math.max(fore, rear);
			}
			return maxRMB[length - 1];
		}
	}

	//优化1：状态压缩，纵观计算步骤，用到的状态其实只有f(k-1) 和 f(k-2)所以只需要保存这两个即可
	public int stealOptimal(int[] roomInfo)
	{
		int length = roomInfo.length;
		//采用自底向上的编程方式
		if(length == 1) return roomInfo[0];
		else if(length == 2) return Math.max(roomInfo[0], roomInfo[1]);
		else 
		{
			int k_2 = roomInfo[0];
			int k_1 = Math.max(roomInfo[0], roomInfo[1]);
			int current = 0;
			for (int k = 2; k < length; k++)
			{
				//这里应该先计算再更新状态
				current = Math.max(k_1, k_2 + roomInfo[k]);
				k_2 = k_1;
				k_1 = current;
			}
			return current;
		}
	}

	//版本2：返回偷窃的房屋编号 M=[1,4,1,2,5,6,3]  FF=[1,4,4,6,9,12,12]
	/*可以得到：
		(1)对于任意的i，FF(i) >= M(i)
		(2)FF递增
	步骤:
		1.找FF中最大值第一次出现的位置index，即小偷偷窃的最后一间房
		2.比较FF[index]和M[index]大小，可能会有两种结果
			1)FF[index] == M[index] 没有偷窃前面的房子，返回
			2)FF[index] > M[index] 前面还有偷窃的房子，更新最大的偷取金额 max=FF[index] - M[index]
			   往回退index-=2，首先满足不能是相邻的房子,然后到2,直到输出最终结果 
    */
	public int[] stealGetRoom(int[] roomInfo)
	{
		int length = roomInfo.length;
		//采用自底向上的编程方式
		if(length == 1) return new int[]{0};
		else if(length == 2) 
		{
			if(roomInfo[0] > roomInfo[1])
				return new int[]{0};
			else
				return new int[]{1};
		}
		else 
		{
			//房间数大于2的情况
			int[] maxRMB = new int[length];
			maxRMB[0] = roomInfo[0];
			maxRMB[1] = Math.max(roomInfo[0], roomInfo[1]);
			for (int k = 2; k < length; k++)
			{
				//如果偷取k-1
				int fore = maxRMB[k - 1];
				//偷取k-2 + k
				int rear = maxRMB[k - 2] + roomInfo[k];
				maxRMB[k] = Math.max(fore, rear);
			}


			//记录房间信息
			Stack<Integer> room = new Stack<>();
			int index = length - 1;
			System.out.println("(GR)能偷盗的最大金额为：" + maxRMB[length - 1]);
			while (index >= 0)
			{
				//重复的值就继续
				if(index > 0 && maxRMB[index] == maxRMB[index - 1])
				{
					index--;
					continue;
				}
				if (maxRMB[index] == roomInfo[index])
				{
					//再往前已经没再偷了
					room.push(index);
					break;
				}
				else
				{
					//首先保存当前的索引
					room.push(index);
					//保障不相邻 
					index -= 2;
				}
			}
			//将栈转换成数组
			int[] roomIndex = new int[room.size()];
			for (int i = 0; i < roomIndex.length; i++)
			{
				roomIndex[i] = room.pop();
			}
			return roomIndex;
		}
	}

	//递归版本
	public int stealRecursion(int[] roomInfo)
	{
		int length = roomInfo.length;

		if(length == 1) return roomInfo[0];
		else if(length == 2) return Math.max(roomInfo[0], roomInfo[1]);
		else 
		{
			int[] foreArr = Arrays.copyOf(roomInfo, length - 1);
			int[] rearArr = Arrays.copyOf(roomInfo, length - 2);
			//如果偷取k-1
			int fore = stealRecursion(foreArr);
			//偷取k-2 + k
			int rear = stealRecursion(rearArr) + roomInfo[length - 1];
			return Math.max(fore, rear);
		}
	}
}
