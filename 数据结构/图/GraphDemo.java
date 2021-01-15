import java.util.*;

public class GraphDemo 
{
	public static void main(String[] args) 
	{
		/*
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
		*/
		
		//              1
		//         2            3
		//       4   5         6--7 
		//         8
		Graph graph = new Graph(8);
		for (int i = 1; i < 9; i++)
		{
			graph.insertVertex("" + i);
		}
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		graph.insertEdge(3, 7, 1);
		graph.insertEdge(4, 7, 1);
		graph.insertEdge(2, 5, 1);
		graph.insertEdge(2, 6, 1);
		graph.insertEdge(5, 6, 1);


		//打印结果
		graph.show();

		//深度优先遍历
		System.out.println("深度优先遍历:");
		graph.dfs();
		
		System.out.println();
		//广度优先遍历
		System.out.println("广度优先遍历");
		graph.bfs();
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
	//保存当前深度优先遍历的遍历情况
	boolean[] isVisited_dfs;
	//保存当前广优先遍历的遍历情况
	boolean[] isVisited_bfs;

	//构造器
	public Graph(int n)
	{
		//初始化所有参数
		vertexList = new ArrayList<String>(5);
		edges = new int[n][n];
		numOfEdges = 0;

		isVisited_dfs = new boolean[n];
		isVisited_bfs = new boolean[n];
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

	//获取顶点
	public String getVertexByIndex(int index)
	{
		return vertexList.get(index);
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

	//**************************************广度优先遍历*********************************************
	public void bfs()
	{
		for (int i = 0; i < vertexList.size(); i++)
		{
			if (!isVisited_bfs[i])
			{
				bfs(isVisited_bfs, i);
			}
		}
	}
	private void bfs(boolean[] isVisited_bfs, int i)
	{
		//定义当前的节点 和 邻接节点
		int u;
		int w;
		//保存所有的节点，进行遍历，防止遍历了前面的节点，就无法遍历后续的节点
		LinkedList<Integer> queue = new LinkedList<Integer>();

		//首先遍历当前的节点，对于所有遍历的节点都是 1.访问 2.标记 3.入队
		System.out.print(getVertexByIndex(i) + " -> ");
		isVisited_bfs[i] = true;
		queue.addLast(i);

		//如果队列不是空就继续
		while (!queue.isEmpty())
		{
			//同上一步骤，获取当前节点和第一个邻接节点
			u = (Integer)queue.removeFirst();
			w = getFirstNeighbor(u);
			//如果w可以访问，那么就执行上述步骤
			while (w != -1)
			{
				//如果w还没访问过
				if (! isVisited_bfs[w])
				{
					System.out.print(getVertexByIndex(w) + " -> ");
					isVisited_bfs[w] = true;
					queue.addLast(w);
				}
				//如果已经访问过了，执行下一个
				w = getNextNeighbor(u, w);
			}
		}
	}

	//**************************************深度优先遍历*********************************************
	//实现深度优先遍历 Depth First Search
	public void dfs()
	{
		//依序对每一个元素进行遍历，如果当前元素深度优先遍历结束，进行下一个元素
		for (int i = 0; i < vertexList.size(); i++)
		{
			if (! isVisited_dfs[i])
			{
				dfs(isVisited_dfs, i);
			}
		}
	}
	//内部私有方法，对当前的一个节点进行深度优先遍历
	private void dfs(boolean[] isVisited_dfs, int i)
	{
		//找到初始节点v
		System.out.print(getVertexByIndex(i) + " -> ");
		//标记该结点已经遍历过
		isVisited_dfs[i] = true;

		//遍历i的邻接节点
		int w = getFirstNeighbor(i);
		//如果一直可以遍历i的邻接节点，就一直调用，否则换下一个邻接节点
		while(w != -1)
		{
			//是否已经遍历过
			if (! isVisited_dfs[w])
			{
				dfs(isVisited_dfs, w);
			}
			w = getNextNeighbor(i, w);
		}
	}
	
	//**************************************遍历工具方法*********************************************
	//dfs使用的两个内部方法，其中第一个是获取当前节点的第一个邻接节点 第二个方法是获取当前节点和邻接节点的下一个邻接节点
	private int getFirstNeighbor(int i)
	{
		for (int j = 0; j < vertexList.size(); j++)
		{
			if (edges[i][j] > 0)	return j;
		}
		return -1;
	}
	private int getNextNeighbor(int v, int n1)
	{
		for (int j = n1 + 1; j < vertexList.size(); j++)
		{
			if (edges[v][j] > 0)	return j;
		}
		return -1;
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