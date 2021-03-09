/*
dijkstra�㷨��ʵ�ֵ�ǰͼ�� ĳһ����ʼ��� �����н�����̾�����㷨
������ ������ȱ���  ��ʽ
�ܵ���˵ ������������ ��¼��ʼ�������пɴ������̾��룬Ȼ���ٹ�����ȣ����α�����ʼ��������
�ɴ��㣬�ֱ�������ǵľ��루����ʼ���ֱ�����̻��� ͨ����ǰ��㵽��̣� ������ǰ�����
*/
import java.util.Arrays;

public class DijkstraAlgorithm
{
    private static final int N = Integer.MAX_VALUE;

    public static void main(String[] args)
    {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph g = new Graph(vertex, matrix);

        g.dijkstra(6);
    }
}

/**
 *��¼�������࣬���������������  1.visited��¼����Ƿ񱻷��ʹ� 2.distance��¼����ʼ���ľ��� 3.preVertex��¼ǰ����㣬��¼·��˳��
 */
class VertexCondition
{
    //��Ӧ����������
    public int[] visited;
    private int[] distance;
    private int[] preVertex;

    //������
    public VertexCondition(int length, int index)
    {
        //Ĭ�����н�㶼û���ʹ�
        visited = new int[length];
        //�տ�ʼ����Ĭ��ȫ���������ֵ
        distance = new int[length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[index] = 0;
        //Ĭ������ǰ����㶼��0
        preVertex = new int[length];
    }

    /**
     *�жϵ�ǰ����Ƿ���ʹ�
     *@param index ��Ҫ�жϵĶ�������
     *return ����Boolean���ͽ��
     */
    public boolean in(int index)
    {
        return visited[index] == 1;
    }

    /**
     *��ȡ��ʼ��㵽�������ľ���
     *@param index ��Ҫ��ֵ�Ķ�������
     *return ���ؾ���
     */
    public int getDis(int index)
    {
        return distance[index];
    }

    /**
     *��ȡ��������ǰ�����
     *@param index ��Ҫ��ֵ�Ķ�������
     *return ���ص�ǰ����ǰ�����
     */
    public int getPre(int index)
    {
        return preVertex[index];
    }

    //����index�����dis
    public void updateDis(int index, int dis)
    {
        distance[index] = dis;
    }

    //����index�����pre
    public void updatePre(int index, int pre)
    {
        preVertex[index] = pre;
    }

    //********************************************************************************************************
    //��ȡ��ǰ״̬�¾�����С�ĵ㣬������ǰ������С�ĵ���з��أ����ڱ�֤��δ���ʣ�����ʵ���˹�����ȱ���
    //********************************************************************************************************
    public int getNext()
    {
        int result = 0;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < visited.length; i++)
        {
            if (visited[i] == 0 && distance[i] < minLen)
            {
                minLen = distance[i];
                result = i;
            }
        }
        return result;
    }

    //���
    public void show(char[] vertex)
    {
        //�����������
        System.out.println(Arrays.toString(visited));
        System.out.println(Arrays.toString(preVertex));
        for (int i  = 0; i < distance.length; i++)
        {
            System.out.print(vertex[i] + "(" + distance[i] + ")\t");
        }
        System.out.println();
    }
}

/**
 *ʵ��ͼ����
 */
class Graph
{
    //���涥����Ϣ
    private char[] vertex;
    //�����ڽӾ���
    private int[][] matrix;
    //�����Ӧ��״̬��
    private VertexCondition vc;

    //������
    public Graph(char[] vertex, int[][] matrix)
    {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //dijkstra�㷨
    public void dijkstra(int index)
    {
        vc = new VertexCondition(vertex.length, index);
        //���ȸ�����ʼ���
        update(index);
        //�������н��
        for (int i = 1; i < vertex.length; i++)
        {
            int num = vc.getNext();
            update(num);
        }
        showResult(vertex);
    }

    /**
     *���µ�ǰ���Ӧ������
     *@param index ��ǰ���µ������
     */
    public void update(int index)
    {
        //���ȱ���Ѿ����ʹ��õ���
        vc.visited[index] = 1;

        int len = 0;
        //���¸ý��������ڽӽ��ľ���
        for (int i = 0; i < matrix[index].length; i++)
        {
            //���index���ڽӽ��û�з��ʹ��ˣ� ǰ�汣֤����δ���ʣ����汣֤�����ڽӽڵ�
            if(!vc.in(i) && matrix[index][i] != Integer.MAX_VALUE)
            {
                //len�����˳�����㵽index�ľ��� + index�������ڽӽ��ľ���֮��
                len = vc.getDis(index) + matrix[index][i];
                //���δ���ʹ��ý�㣬��������·����֮ǰ�ļ�¼������
                if (len < vc.getDis(i))
                {
                    //���¾����ǰ�����
                    vc.updateDis(i, len);
                    vc.updatePre(i, index);
                }
            }
        }
    }
    //������ս��
    public void showResult(char[] vertex)
    {
        vc.show(vertex);
    }

    //չʾͼ�ķ���
    public void showGraph()
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                System.out.print((matrix[i][j] == Integer.MAX_VALUE ? "N" : matrix[i][j]) + "\t");
            }
        }
    }
}