import java.util.*;
/*
	克鲁斯卡尔算法作用是求图中的最小生成树。
	与Prim算法的区别之处在于：Prim算法是以节点为遍历顺序，早到当前已经连接的点往外连的最小的边，而Kruskal算法是按照边的权重为遍历顺序，按照从小到大的顺序依次添加满足条件的所有边
	问题：1)需要对所有的边进行排序 2)需要确定不构成回路，具体实现：每次添加的时候 两个顶点在最小生成树中的所对应的边的终点如果一致，该边则不可以添加进去
	主要做法：构建一个只含n个顶点的森林，然后依权值从小到大从连通网中选择边加入到森林中，并使不产生回路，直至森林变成一棵树为止
*/
public class KruskalAlgorithm 
{
	//一个图对应的参数
	char[] vertex;	//保存所有顶点
	int[][] weight;	//保存边的权重矩阵
	int numOfEdges;	//边的数量
	private static final int INF = Integer.MAX_VALUE;

	//构造器
	public KruskalAlgorithm(char[] vertex, int[][] weight)
	{
		this.vertex = new char[vertex.length];
		//依次拷贝赋值
		for (int i = 0; i < vertex.length; i++)
		{
			this.vertex[i] = vertex[i];
		}
		this.weight = new int[vertex.length][vertex.length];
		for (int i = 0; i < vertex.length; i++)
		{
			for (int j = 0; j < vertex.length; j++)
			{
				this.weight[i][j] = weight[i][j];
			}
		}
		numOfEdges = 0;
		for (int i = 0; i < vertex.length; i++)
		{
			for (int j = i + 1; j < vertex.length; j++)
			{
				if (weight[i][j] != INF)
				{
					numOfEdges++;
				}
			}
		}
	}

	//算法代码
	public void kruskalCode()
	{
		//定义一个遍历的索引
		int index = 0;
		//保存当前最小生成树中所有点对应的终点
		int[] ends = new int[vertex.length];
		//保存最终结果，即最小生成树对应的所有边
		EData[] result = new EData[vertex.length - 1];

		//由当前的图获取所有边的集合
		EData[] edges = getEdges();
		//System.out.println(Arrays.toString(edges));
		sortEdges(edges);
		//System.out.println(Arrays.toString(edges));

		//开始遍历添加数据
		for (int i = 0; i < edges.length; i++)
		{
			//获取这条边的两个端点
			int p1 = getPosition(edges[i].start);
			int p2 = getPosition(edges[i].end);
			//获取两点各自的终点
			int m = getEnd(ends, p1);
			int n = getEnd(ends, p2);

			//如果不构成回路
			if (m != n)
			{
				//首先更新终点表
				ends[m] = n;
				//将该条边添加进去
				result[index++] = edges[i];
			}
		}

		//最终输出结果
		System.out.println("最终的最小生成树包括的边有：");
		System.out.println(Arrays.toString(result));
	}
	
//************************************************工具方法代码段****************************************************
	//用来获取所有边的对应的集合
	private EData[] getEdges()
	{
		int index = 0;
		EData[] edges = new EData[numOfEdges];
		for (int i = 0; i < vertex.length; i++)
		{
			for (int j = i + 1; j < vertex.length; j++)
			{
				//如果是有效边，添加数据
				if (weight[i][j] != INF)
				{
					edges[index++] = new EData(vertex[i], vertex[j], weight[i][j]);
				}
			}
		}
		return edges;
	}
	//对边的集合组成的数组进行排序，采用冒泡排序，从小到大
	private void sortEdges(EData[] edges)
	{
		for (int i = 0; i < edges.length - 1; i++)
		{
			for (int j = 0; j < edges.length - 1 - i; j++)
			{
				if (edges[j].weight > edges[j + 1].weight)
				{
					//如果大小关系不符合，交换顺序
					EData temp = edges[j + 1];
					edges[j + 1] = edges[j];
					edges[j] = temp;
				}
			}
		}
	}

	//根据顶点名字获取对应的位置，如果没找到返回-1
	private int getPosition(char ch)
	{
		//遍历获取下标
		for (int i = 0; i < vertex.length; i++)
		{
			if (vertex[i] == ch)
			{
				return i;
			}
		}
		return -1;
	}

	//可以根据数组返回当前顶点对应的终点，来辅助是否是回路的判断
	private int getEnd(int[] ends, int start)
	{
		while (ends[start] != 0)
		{
			start = ends[start];
		}
		return start;
	}
//************************************************工具方法代码段****************************************************

	public static void main(String[] args)
	{
		char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] matrix = {
			{0, 12, INF, INF, INF, 16, 14},
			{12, 0, 10, INF, INF, 7, INF},
			{INF, 10, 0, 3, 5, 6, INF},
			{INF, INF, 3, 0, 4, INF, INF},
			{INF, INF, 5, 4, 0, 2, 8},
			{16, 7, 6, INF, 2, 0, 9},
			{14, INF, INF, INF, 8, 9 ,0}};
		//创建对象
		KruskalAlgorithm kruskal = new KruskalAlgorithm(vertex, matrix);

		kruskal.kruskalCode();
	}
}

//用来存放每一条边的数据
class EData
{
	public char start;
	public char end;
	public int weight;

	//构造器
	public EData(char start, char end, int weight)
	{
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	//重写toString方法
	public String toString()
	{
		return "<" + start + ", " + end + "> = " + weight;
	}
}