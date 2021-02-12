import java.util.*;
/*
	��³˹�����㷨��������ͼ�е���С��������
	��Prim�㷨������֮�����ڣ�Prim�㷨���Խڵ�Ϊ����˳���絽��ǰ�Ѿ����ӵĵ�����������С�ıߣ���Kruskal�㷨�ǰ��ձߵ�Ȩ��Ϊ����˳�򣬰��մ�С�����˳����������������������б�
	���⣺1)��Ҫ�����еı߽������� 2)��Ҫȷ�������ɻ�·������ʵ�֣�ÿ����ӵ�ʱ�� ������������С�������е�����Ӧ�ıߵ��յ����һ�£��ñ��򲻿�����ӽ�ȥ
	��Ҫ����������һ��ֻ��n�������ɭ�֣�Ȼ����Ȩֵ��С�������ͨ����ѡ��߼��뵽ɭ���У���ʹ��������·��ֱ��ɭ�ֱ��һ����Ϊֹ
*/
public class KruskalAlgorithm 
{
	//һ��ͼ��Ӧ�Ĳ���
	char[] vertex;	//�������ж���
	int[][] weight;	//����ߵ�Ȩ�ؾ���
	int numOfEdges;	//�ߵ�����
	private static final int INF = Integer.MAX_VALUE;

	//������
	public KruskalAlgorithm(char[] vertex, int[][] weight)
	{
		this.vertex = new char[vertex.length];
		//���ο�����ֵ
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

	//�㷨����
	public void kruskalCode()
	{
		//����һ������������
		int index = 0;
		//���浱ǰ��С�����������е��Ӧ���յ�
		int[] ends = new int[vertex.length];
		//�������ս��������С��������Ӧ�����б�
		EData[] result = new EData[vertex.length - 1];

		//�ɵ�ǰ��ͼ��ȡ���бߵļ���
		EData[] edges = getEdges();
		//System.out.println(Arrays.toString(edges));
		sortEdges(edges);
		//System.out.println(Arrays.toString(edges));

		//��ʼ�����������
		for (int i = 0; i < edges.length; i++)
		{
			//��ȡ�����ߵ������˵�
			int p1 = getPosition(edges[i].start);
			int p2 = getPosition(edges[i].end);
			//��ȡ������Ե��յ�
			int m = getEnd(ends, p1);
			int n = getEnd(ends, p2);

			//��������ɻ�·
			if (m != n)
			{
				//���ȸ����յ��
				ends[m] = n;
				//����������ӽ�ȥ
				result[index++] = edges[i];
			}
		}

		//����������
		System.out.println("���յ���С�����������ı��У�");
		System.out.println(Arrays.toString(result));
	}
	
//************************************************���߷��������****************************************************
	//������ȡ���бߵĶ�Ӧ�ļ���
	private EData[] getEdges()
	{
		int index = 0;
		EData[] edges = new EData[numOfEdges];
		for (int i = 0; i < vertex.length; i++)
		{
			for (int j = i + 1; j < vertex.length; j++)
			{
				//�������Ч�ߣ��������
				if (weight[i][j] != INF)
				{
					edges[index++] = new EData(vertex[i], vertex[j], weight[i][j]);
				}
			}
		}
		return edges;
	}
	//�Աߵļ�����ɵ�����������򣬲���ð�����򣬴�С����
	private void sortEdges(EData[] edges)
	{
		for (int i = 0; i < edges.length - 1; i++)
		{
			for (int j = 0; j < edges.length - 1 - i; j++)
			{
				if (edges[j].weight > edges[j + 1].weight)
				{
					//�����С��ϵ�����ϣ�����˳��
					EData temp = edges[j + 1];
					edges[j + 1] = edges[j];
					edges[j] = temp;
				}
			}
		}
	}

	//���ݶ������ֻ�ȡ��Ӧ��λ�ã����û�ҵ�����-1
	private int getPosition(char ch)
	{
		//������ȡ�±�
		for (int i = 0; i < vertex.length; i++)
		{
			if (vertex[i] == ch)
			{
				return i;
			}
		}
		return -1;
	}

	//���Ը������鷵�ص�ǰ�����Ӧ���յ㣬�������Ƿ��ǻ�·���ж�
	private int getEnd(int[] ends, int start)
	{
		while (ends[start] != 0)
		{
			start = ends[start];
		}
		return start;
	}
//************************************************���߷��������****************************************************

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
		//��������
		KruskalAlgorithm kruskal = new KruskalAlgorithm(vertex, matrix);

		kruskal.kruskalCode();
	}
}

//�������ÿһ���ߵ�����
class EData
{
	public char start;
	public char end;
	public int weight;

	//������
	public EData(char start, char end, int weight)
	{
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	//��дtoString����
	public String toString()
	{
		return "<" + start + ", " + end + "> = " + weight;
	}
}