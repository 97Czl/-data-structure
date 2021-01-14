import java.util.*;

public class GraphDemo 
{
	public static void main(String[] args) 
	{
		Graph graph = new Graph(5);

		//顶点名字命名为：A B C D E
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");

		//需要添加的边有  BC BE BA BD CA
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 4, 1);
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(0, 2, 1);

		//打印结果
		graph.show();
	}
}

class Graph
{
	//保存顶点
	private ArrayList<String> vertexList;
	//保存边
	private int[][] edges;
	//保存边的个数
	private int numOfEdges;

	//构造器
	public Graph(int n)
	{
		//初始化所有参数
		vertexList = new ArrayList<String>(5);
		edges = new int[n][n];
		numOfEdges = 0;
	}

	//给图添加边
	public void insertEdge(int v1, int v2, int weight)
	{
		//一条边对应两个权重
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}

	//添加顶点
	public void insertVertex(String vertex)
	{
		vertexList.add(vertex);
	}

	//获取图中的一条边
	public int getWeight(int v1, int v2)
	{
		return edges[v1][v2];
	}

	//获取当前图的边数
	public int getNumOfEdges()
	{
		return numOfEdges;
	}

	//显示所有权值矩阵
	public void show()
	{
		System.out.println("图的连接矩阵为：");
		for (int[] ele : edges)
		{
			System.out.println(Arrays.toString(ele));
		}
	}
}