/*
	floyd�㷨�Ǽ���ͼ��ÿһ����㵽���н������·��
	��ͬ�ڵϽ�˹�����㷨�����㷨�����нڵ㶼Ҫ��Ϊ��ʼ���������
*/
import java.util.*;

public class FloydAlgorithm 
{
	public static void main(String[] args) 
	{
		//�½�ͼ
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


//ͼ ��  �Լ���Ӧ��Floyd����
class Graph
{
	//ͼ�Ķ���
	private char[] vertex;
	//ͼ�ľ������
	private int[][] matrix;

	//����ʵ��Floyd�㷨���������飬�ֱ��¼ ǰ�����Ͷ�Ӧ�ľ���
	private int[][] pre;
	private int[][] dis;
	private final static int N = Integer.MAX_VALUE;

	//������
	public Graph(char[] vertex, int[][] matrix)
	{
		this.vertex = vertex;
		this.matrix = matrix;
	}

	//Floyd�㷨
	public void floyd()
	{
		//���Ƚ����ݳ�ʼ��
		pre = new int[vertex.length][vertex.length];
		for (int i = 0; i < pre.length; i++)
		{
			//���н��տ�ʼ��ǰ����㶼Ĭ��Ϊ�Լ�
			Arrays.fill(pre[i], i);
		}
		dis = new int[vertex.length][vertex.length];
		for (int i = 0; i < dis.length; i++)
		{
			//���н��տ�ʼ�ľ�������matrixͬ����
			for (int j = 0; j < dis.length; j++)
			{
				dis[i][j] = matrix[i][j];
				dis[j][i] = matrix[i][j];
			}
		}
		//��ÿһ�������Ϊ�м��㣬��ʼ��㣬�յ�������ѭ��ʵ�ֱ������£�ֻҪͨ����ǰ����·������ԭ��������ľ��룬����¾����ǰ�������Ϣ
		//����ͬһ��������ͬʱ�䵱�������ã����Ա�֤���Լ����Լ� �Լ� �Լ������ڽڵ�ľ���Ҳ������ȥ
		//i �м���  j ��ʼ���  k  �յ�
		for (int i = 0; i < vertex.length; i++ )
		{
			for (int j = 0; j < vertex.length; j++)
			{
				for (int k = 0; k < vertex.length; k++)
				{
					//���i �� j k��ͨ��ִ�У���Ȼû��Ҫ�Ƚ�
					if(dis[i][j] != N && dis[i][k] != N)
					{
						//����j ͨ�� i ���� k �ľ��룬�ж��Ƿ�Ҫ����
						int len = dis[i][j] + dis[i][k];
						if (len <= dis[j][k])
						{
							//����ǰ�����;���
							updatePre(j, k, i);
							updateDis(j, k, len);
						}
					}
				}
			}
		}

		//���չʾ���
		showResult();
	}

	/**
	 *����ǰ�����Ĺ��߷���
	 *@param index ���µ���index��ǰ�����
	 *@param target index��target������̾�����ͨ��pre���ʵ�ֵ�
	 *@param pre index��ǰ�����
	 */
	private void updatePre(int index, int target, int preNode)
	{
		//���ڽ��index��˵�������н�����̾����� dis[index]��һ�У���ε�������Ҫ���¶���pre[index] ��˵ ȥÿһ���� target ��ǰ�����pre
		pre[index][target] = preNode;
	}

	//���¾��� ͬ��
	private void updateDis(int index, int target, int len)
	{
		dis[index][target] = len;
		dis[target][index] = len;
	}

	//չʾ�㷨���
	private void showResult()
	{
		for (int i = 0; i < vertex.length; i++)
		{
			System.out.println("����" + vertex[i] + "����Ϣ��");
			for (int j = 0; j < vertex.length; j++)
			{
				//���ǰ�����
				System.out.print("(" + vertex[i] + "->" + vertex[pre[i][j]] + "->" + vertex[j] + ") \t");
			}
			System.out.println();
			//���������Ϣ
			for (int j = 0; j < vertex.length; j++)
			{
				System.out.print("(" + vertex[i] + "<->" + vertex[j] + "=" + dis[i][j] + ") \t");
			}
			System.out.println("\n");
		}
	}
}