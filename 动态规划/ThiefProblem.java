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

public class ThiefProblem 
{
	public static void main(String[] args) 
	{
		int[] roomInfo = new int[]{2,7,2,3,8};
		System.out.println("能偷盗的最大金额为：" + new ThiefProblem().steal(roomInfo));
		//递归方式
		System.out.println("能偷盗的最大金额为：" + new ThiefProblem().stealRecursion(roomInfo));
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
