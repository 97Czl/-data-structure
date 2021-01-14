import java.util.*;

public class GraphDemo 
{
	public static void main(String[] args) 
	{
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

		//��ӡ���
		graph.show();
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

	//������
	public Graph(int n)
	{
		//��ʼ�����в���
		vertexList = new ArrayList<String>(5);
		edges = new int[n][n];
		numOfEdges = 0;
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