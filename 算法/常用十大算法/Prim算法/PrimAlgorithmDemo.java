import java.util.*;

/*
	普利姆算法一个典型应用就是最小生成树（Minimum Cost Spanning Tree）, 但是最小生成树不止可以用普里姆算法，也可以用克鲁斯尔算法
	场景：在给定的图中修建公路，要求：
		1.实现所有村庄的连接
		2.所修建的总的公里数最少
	算法：
		1.设G=(V,E)是连通网，即原图，T=(U,D)是最小生成树。V和U分别是两个图对应的顶点集合，D和E是两个图对应的边的集合
		2.若从顶点u开始构造最小生成树，则从V中取出顶点u，添加到U中，并且将visited[u] = 1
		3.如果集合U和 V-U 的集合各自的所有元素对应有最小的边，那么将对应未访问的点v添加到U中，并且将最小的边添加到最小生成树中，标记visited[v] = 1
		4.重复3，直到U和V的集合相等，即将所有元素都添加到了最小生成树中，此时，最小生成树应该有n-1条边，n为图中顶点的个数
*/
public class PrimAlgorithmDemo 
{
	public static void main(String[] args) 
	{
		//定义原始图的顶点
		ArrayList<String> vertex = new ArrayList<>();
		for (char i = 'A'; i < 'H'; i++)
		{
			vertex.add("" + i);
		}
		//定义原始图的权重表
		int[][] edges = new int[][]
		{
			{10000, 5, 7, 10000, 10000, 10000, 2},
			{5, 10000, 10000, 9, 10000, 10000, 3},
			{7, 10000, 10000, 10000, 8, 10000, 10000},
			{10000, 9, 10000, 10000, 10000, 4, 10000},
			{10000, 10000, 8, 10000, 10000, 5, 4},
			{10000, 10000, 10000, 4, 5, 10000, 6},
			{2, 3, 10000, 10000, 4, 6, 10000}
		};

		//原始图
		Graph origin = new Graph(vertex, edges);

		//生成最小生成树
		Graph mst = MST.prim(origin, 0);
		mst.showEdges();
	}
}

//最小生成树的类
class MST
{
	/**
	 *@param graph 原始的图
	 *@param v 构建最小生成树的开始点
	 *@return 返回最后生成的最小生成树所对应的图
	 */
	public static Graph prim(Graph graph, int v)
	{
		//首先定义一个最终返回的生成树
		Graph result = new Graph(graph.getVertexList());
		//定义两个下标和最小的权重，记录当前最优的结果
		int h1 = -1;
		int h2 = -1;
		int minWeight = 10000;
		//定义保存当前访问记录的数组
		int[] visited = new int[graph.getVertexList().size()];
		visited[v] = 1;
		for (int k = 1; k < graph.getVertexList().size(); k++)
		{
			//因为最小生成树中对于n个顶点，只有n-1个边，所以上述循环需要少一次
			for (int i = 0; i < graph.getVertexList().size(); i++)
			{
				for (int j = 0; j < graph.getVertexList().size(); j++)
				{
					//所有点都要遍历，但是只考虑 i 为已经遍历过的， j 不是遍历过的，并且i和j之间权重是当前最优值，才会更新
					if (visited[i] == 1 && visited[j] == 0 && graph.getWeight(i, j) < minWeight)
					{
						//更新记录的参数
						h1 = i;
						h2 = j;
						minWeight = graph.getWeight(i, j);
					}
				}
			}

			//遍历结束后，即找到了当前的最优结果
			//操作步骤包括： 标记访问的点；记录最小生成树的路径；还原最小权重
			visited[h2] = 1;
			result.insertEdge(h1, h2, minWeight);
			//System.out.println("[" + graph.getVertexList().get(h1) + "] <===> [" + graph.getVertexList().get(h2) + "]  weight = " + graph.getWeight(h1, h2));
			minWeight = 10000;
		}

		return result;
	}
}

class Graph
{
	private ArrayList<String> vertexList;
	private int[][] edges;
	private int numOfEdges;

	//多个构造器，适合多种构建情况
	public Graph(Collection<String> vertexs)
	{
		vertexList = new ArrayList<String>(vertexs);
		edges = new int[vertexList.size()][vertexList.size()];
		initWeight();
		numOfEdges = 0;
	}
	public Graph(Collection<String> vertexs, int[][] edges)
	{
		if (vertexs.size() == edges.length)
		{
			this.vertexList = new ArrayList<String>(vertexs);
			this.edges = edges;
			for (int i = 0; i < edges.length; i++)
			{
				for (int j = i; j < edges[0].length; j++)
				{
					if (edges[i][j] < 10000)
					{
						numOfEdges++;
					}
				}
			}
		}
		else
		{
			System.out.println("顶点数量和边的数量不匹配！未创建对象");
		}
	}
	//初始化所有边权重为10000
	private void initWeight()
	{
		for (int i = 0; i < edges.length; i++)
		{
			for (int j = 0; j < edges[0].length; j++)
			{
				edges[i][j] = 10000;
			}
		}
	}

	//获取当前的所有参数
	public ArrayList<String> getVertexList()
	{
		return this.vertexList;
	}
	public int[][] getEdges()
	{
		return this.edges;
	}
	public int getNumOfEdges()
	{
		return this.numOfEdges;
	}

	public int getWeight(int i, int j)
	{
		return edges[i][j];
	}

	//给图添加边
	public void insertEdge(int i, int j, int weight)
	{
		edges[i][j] = weight;
		edges[j][i] = weight;
		numOfEdges++;
	}

	//展示当前的图
	public void showGraph()
	{
		for (int[] line : edges)
		{
			System.out.println(Arrays.toString(line));
		}
	}

	//展示当前的图的有效路径
	public void showEdges()
	{
		for (int i = 0; i < edges.length; i++)
		{
			for (int j = i; j < edges[0].length; j++)
			{
				if (edges[i][j] < 10000)
				{
					System.out.println("[" + vertexList.get(i) + "] <===> [" + vertexList.get(j) + "]  weight = " + getWeight(i, j));
				}
			}
		}
	}
}