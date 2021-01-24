import java.util.*;

/*
	����ķ�㷨һ������Ӧ�þ�����С��������Minimum Cost Spanning Tree��, ������С��������ֹ����������ķ�㷨��Ҳ�����ÿ�³˹���㷨
	�������ڸ�����ͼ���޽���·��Ҫ��
		1.ʵ�����д�ׯ������
		2.���޽����ܵĹ���������
	�㷨��
		1.��G=(V,E)����ͨ������ԭͼ��T=(U,D)����С��������V��U�ֱ�������ͼ��Ӧ�Ķ��㼯�ϣ�D��E������ͼ��Ӧ�ıߵļ���
		2.���Ӷ���u��ʼ������С�����������V��ȡ������u����ӵ�U�У����ҽ�visited[u] = 1
		3.�������U�� V-U �ļ��ϸ��Ե�����Ԫ�ض�Ӧ����С�ıߣ���ô����Ӧδ���ʵĵ�v��ӵ�U�У����ҽ���С�ı���ӵ���С�������У����visited[v] = 1
		4.�ظ�3��ֱ��U��V�ļ�����ȣ���������Ԫ�ض���ӵ�����С�������У���ʱ����С������Ӧ����n-1���ߣ�nΪͼ�ж���ĸ���
*/
public class PrimAlgorithmDemo 
{
	public static void main(String[] args) 
	{
		//����ԭʼͼ�Ķ���
		ArrayList<String> vertex = new ArrayList<>();
		for (char i = 'A'; i < 'H'; i++)
		{
			vertex.add("" + i);
		}
		//����ԭʼͼ��Ȩ�ر�
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

		//ԭʼͼ
		Graph origin = new Graph(vertex, edges);

		//������С������
		Graph mst = MST.prim(origin, 0);
		mst.showEdges();
	}
}

//��С����������
class MST
{
	/**
	 *@param graph ԭʼ��ͼ
	 *@param v ������С�������Ŀ�ʼ��
	 *@return ����������ɵ���С����������Ӧ��ͼ
	 */
	public static Graph prim(Graph graph, int v)
	{
		//���ȶ���һ�����շ��ص�������
		Graph result = new Graph(graph.getVertexList());
		//���������±����С��Ȩ�أ���¼��ǰ���ŵĽ��
		int h1 = -1;
		int h2 = -1;
		int minWeight = 10000;
		//���屣�浱ǰ���ʼ�¼������
		int[] visited = new int[graph.getVertexList().size()];
		visited[v] = 1;
		for (int k = 1; k < graph.getVertexList().size(); k++)
		{
			//��Ϊ��С�������ж���n�����㣬ֻ��n-1���ߣ���������ѭ����Ҫ��һ��
			for (int i = 0; i < graph.getVertexList().size(); i++)
			{
				for (int j = 0; j < graph.getVertexList().size(); j++)
				{
					//���е㶼Ҫ����������ֻ���� i Ϊ�Ѿ��������ģ� j ���Ǳ������ģ�����i��j֮��Ȩ���ǵ�ǰ����ֵ���Ż����
					if (visited[i] == 1 && visited[j] == 0 && graph.getWeight(i, j) < minWeight)
					{
						//���¼�¼�Ĳ���
						h1 = i;
						h2 = j;
						minWeight = graph.getWeight(i, j);
					}
				}
			}

			//���������󣬼��ҵ��˵�ǰ�����Ž��
			//������������� ��Ƿ��ʵĵ㣻��¼��С��������·������ԭ��СȨ��
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

	//������������ʺ϶��ֹ������
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
			System.out.println("���������ͱߵ�������ƥ�䣡δ��������");
		}
	}
	//��ʼ�����б�Ȩ��Ϊ10000
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

	//��ȡ��ǰ�����в���
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

	//��ͼ��ӱ�
	public void insertEdge(int i, int j, int weight)
	{
		edges[i][j] = weight;
		edges[j][i] = weight;
		numOfEdges++;
	}

	//չʾ��ǰ��ͼ
	public void showGraph()
	{
		for (int[] line : edges)
		{
			System.out.println(Arrays.toString(line));
		}
	}

	//չʾ��ǰ��ͼ����Ч·��
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