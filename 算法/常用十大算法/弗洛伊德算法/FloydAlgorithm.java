/*
	floyd算法是计算图中每一个结点到所有结点的最短路径
	不同于迪杰斯特拉算法，该算法中所有节点都要作为开始结点参与计算
*/
import java.util.*;

public class FloydAlgorithm 
{
	public static void main(String[] args) 
	{
		//新建图
		char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = Integer.MAX_VALUE;
		matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
		matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
		matrix[2] = new int[]{7, N, 0, N, 8, N, N};
		matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
		matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
		matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
		matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

		Graph g = new Graph(vertex, matrix);

		g.floyd();
	}
}


//图 类  以及对应的Floyd方法
class Graph
{
	//图的顶点
	private char[] vertex;
	//图的距离矩阵
	private int[][] matrix;

	//用来实现Floyd算法的两个数组，分别记录 前驱结点和对应的距离
	private int[][] pre;
	private int[][] dis;
	private final static int N = Integer.MAX_VALUE;

	//构造器
	public Graph(char[] vertex, int[][] matrix)
	{
		this.vertex = vertex;
		this.matrix = matrix;
	}

	//Floyd算法
	public void floyd()
	{
		//首先将数据初始化
		pre = new int[vertex.length][vertex.length];
		for (int i = 0; i < pre.length; i++)
		{
			//所有结点刚开始的前驱结点都默认为自己
			Arrays.fill(pre[i], i);
		}
		dis = new int[vertex.length][vertex.length];
		for (int i = 0; i < dis.length; i++)
		{
			//所有结点刚开始的距离是与matrix同步的
			for (int j = 0; j < dis.length; j++)
			{
				dis[i][j] = matrix[i][j];
				dis[j][i] = matrix[i][j];
			}
		}
		//将每一个结点作为中间结点，起始结点，终点来三重循环实现遍历更新，只要通过当前结点的路径优于原来两个点的距离，则更新距离和前驱结点信息
		//由于同一个结点可以同时充当三个作用，所以保证了自己到自己 以及 自己到相邻节点的距离也会计算进去
		//i 中间结点  j 起始结点  k  终点
		for (int i = 0; i < vertex.length; i++ )
		{
			for (int j = 0; j < vertex.length; j++)
			{
				for (int k = 0; k < vertex.length; k++)
				{
					//如果i 与 j k互通才执行，不然没必要比较
					if(dis[i][j] != N && dis[i][k] != N)
					{
						//计算j 通过 i 到达 k 的距离，判断是否要更新
						int len = dis[i][j] + dis[i][k];
						if (len <= dis[j][k])
						{
							//更新前驱结点和距离
							updatePre(j, k, i);
							updateDis(j, k, len);
						}
					}
				}
			}
		}

		//最后展示结果
		showResult();
	}

	/**
	 *更新前驱结点的工具方法
	 *@param index 更新的是index的前驱结点
	 *@param target index到target结点的最短距离是通过pre结点实现的
	 *@param pre index的前驱结点
	 */
	private void updatePre(int index, int target, int preNode)
	{
		//对于结点index来说，与所有结点的最短距离是 dis[index]那一行，如何到达则需要更新对于pre[index] 来说 去每一个点 target 的前驱结点pre
		pre[index][target] = preNode;
	}

	//更新距离 同上
	private void updateDis(int index, int target, int len)
	{
		dis[index][target] = len;
		dis[target][index] = len;
	}

	//展示算法结果
	private void showResult()
	{
		for (int i = 0; i < vertex.length; i++)
		{
			System.out.println("顶点" + vertex[i] + "的信息：");
			for (int j = 0; j < vertex.length; j++)
			{
				//输出前驱结点
				System.out.print("(" + vertex[i] + "->" + vertex[pre[i][j]] + "->" + vertex[j] + ") \t");
			}
			System.out.println();
			//输出距离信息
			for (int j = 0; j < vertex.length; j++)
			{
				System.out.print("(" + vertex[i] + "<->" + vertex[j] + "=" + dis[i][j] + ") \t");
			}
			System.out.println("\n");
		}
	}
}