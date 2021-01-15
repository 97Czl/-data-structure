import java.util.*;

public class GraphDemo 
{
	public static void main(String[] args) 
	{
		/*
		Graph graph = new Graph(5);
		//������������Ϊ��A B C D E
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");

		//��Ҫ��ӵı���  BC BE BA BD CA
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


		//��ӡ���
		graph.show();

		//������ȱ���
		System.out.println("������ȱ���:");
		graph.dfs();
		
		System.out.println();
		//������ȱ���
		System.out.println("������ȱ���");
		graph.bfs();
	}
}

class Graph
{
	//���涥��
	private ArrayList<String> vertexList;
	//�����
	private int[][] edges;
	//����ߵĸ���
	private int numOfEdges;
	//���浱ǰ������ȱ����ı������
	boolean[] isVisited_dfs;
	//���浱ǰ�����ȱ����ı������
	boolean[] isVisited_bfs;

	//������
	public Graph(int n)
	{
		//��ʼ�����в���
		vertexList = new ArrayList<String>(5);
		edges = new int[n][n];
		numOfEdges = 0;

		isVisited_dfs = new boolean[n];
		isVisited_bfs = new boolean[n];
	}

	//��ͼ��ӱ�
	public void insertEdge(int v1, int v2, int weight)
	{
		//һ���߶�Ӧ����Ȩ��
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}

	//��Ӷ���
	public void insertVertex(String vertex)
	{
		vertexList.add(vertex);
	}

	//��ȡ����
	public String getVertexByIndex(int index)
	{
		return vertexList.get(index);
	}

	//��ȡͼ�е�һ����
	public int getWeight(int v1, int v2)
	{
		return edges[v1][v2];
	}

	//��ȡ��ǰͼ�ı���
	public int getNumOfEdges()
	{
		return numOfEdges;
	}

	//**************************************������ȱ���*********************************************
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
		//���嵱ǰ�Ľڵ� �� �ڽӽڵ�
		int u;
		int w;
		//�������еĽڵ㣬���б�������ֹ������ǰ��Ľڵ㣬���޷����������Ľڵ�
		LinkedList<Integer> queue = new LinkedList<Integer>();

		//���ȱ�����ǰ�Ľڵ㣬�������б����Ľڵ㶼�� 1.���� 2.��� 3.���
		System.out.print(getVertexByIndex(i) + " -> ");
		isVisited_bfs[i] = true;
		queue.addLast(i);

		//������в��ǿվͼ���
		while (!queue.isEmpty())
		{
			//ͬ��һ���裬��ȡ��ǰ�ڵ�͵�һ���ڽӽڵ�
			u = (Integer)queue.removeFirst();
			w = getFirstNeighbor(u);
			//���w���Է��ʣ���ô��ִ����������
			while (w != -1)
			{
				//���w��û���ʹ�
				if (! isVisited_bfs[w])
				{
					System.out.print(getVertexByIndex(w) + " -> ");
					isVisited_bfs[w] = true;
					queue.addLast(w);
				}
				//����Ѿ����ʹ��ˣ�ִ����һ��
				w = getNextNeighbor(u, w);
			}
		}
	}

	//**************************************������ȱ���*********************************************
	//ʵ��������ȱ��� Depth First Search
	public void dfs()
	{
		//�����ÿһ��Ԫ�ؽ��б����������ǰԪ��������ȱ���������������һ��Ԫ��
		for (int i = 0; i < vertexList.size(); i++)
		{
			if (! isVisited_dfs[i])
			{
				dfs(isVisited_dfs, i);
			}
		}
	}
	//�ڲ�˽�з������Ե�ǰ��һ���ڵ����������ȱ���
	private void dfs(boolean[] isVisited_dfs, int i)
	{
		//�ҵ���ʼ�ڵ�v
		System.out.print(getVertexByIndex(i) + " -> ");
		//��Ǹý���Ѿ�������
		isVisited_dfs[i] = true;

		//����i���ڽӽڵ�
		int w = getFirstNeighbor(i);
		//���һֱ���Ա���i���ڽӽڵ㣬��һֱ���ã�������һ���ڽӽڵ�
		while(w != -1)
		{
			//�Ƿ��Ѿ�������
			if (! isVisited_dfs[w])
			{
				dfs(isVisited_dfs, w);
			}
			w = getNextNeighbor(i, w);
		}
	}
	
	//**************************************�������߷���*********************************************
	//dfsʹ�õ������ڲ����������е�һ���ǻ�ȡ��ǰ�ڵ�ĵ�һ���ڽӽڵ� �ڶ��������ǻ�ȡ��ǰ�ڵ���ڽӽڵ����һ���ڽӽڵ�
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


	//��ʾ����Ȩֵ����
	public void show()
	{
		System.out.println("ͼ�����Ӿ���Ϊ��");
		for (int[] ele : edges)
		{
			System.out.println(Arrays.toString(ele));
		}
	}
}