/*
dijkstra算法是实现当前图下 某一个起始结点 到所有结点的最短距离的算法
采用了 广度优先遍历  方式
总的来说 利用三个参数 记录起始结点的所有可达结点的最短距离，然后再广度优先，依次遍历起始结点的所有
可达结点，分别更新他们的距离（看起始结点直达距离短还是 通过当前结点到达短） 并更新前驱结点
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
 *记录遍历的类，该类包含三个参数  1.visited记录结点是否被访问过 2.distance记录到起始结点的距离 3.preVertex记录前驱结点，记录路线顺序
 */
class VertexCondition
{
    //对应的三个参数
    public int[] visited;
    private int[] distance;
    private int[] preVertex;

    //构造器
    public VertexCondition(int length, int index)
    {
        //默认所有结点都没访问过
        visited = new int[length];
        //刚开始距离默认全部都是最大值
        distance = new int[length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[index] = 0;
        //默认所有前驱结点都是0
        preVertex = new int[length];
    }

    /**
     *判断当前结点是否访问过
     *@param index 需要判断的顶点索引
     *return 返回Boolean类型结果
     */
    public boolean in(int index)
    {
        return visited[index] == 1;
    }

    /**
     *获取起始结点到索引结点的距离
     *@param index 需要求值的顶点索引
     *return 返回距离
     */
    public int getDis(int index)
    {
        return distance[index];
    }

    /**
     *获取索引结点的前驱结点
     *@param index 需要求值的顶点索引
     *return 返回当前结点的前驱结点
     */
    public int getPre(int index)
    {
        return preVertex[index];
    }

    //更新index顶点的dis
    public void updateDis(int index, int dis)
    {
        distance[index] = dis;
    }

    //更新index顶点的pre
    public void updatePre(int index, int pre)
    {
        preVertex[index] = pre;
    }

    //********************************************************************************************************
    //获取当前状态下距离最小的点，即将当前距离最小的点进行返回，由于保证了未访问，所以实现了广度优先遍历
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

    //输出
    public void show(char[] vertex)
    {
        //输出三个参数
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
 *实现图的类
 */
class Graph
{
    //保存顶点信息
    private char[] vertex;
    //保存邻接矩阵
    private int[][] matrix;
    //定义对应的状态类
    private VertexCondition vc;

    //构造器
    public Graph(char[] vertex, int[][] matrix)
    {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //dijkstra算法
    public void dijkstra(int index)
    {
        vc = new VertexCondition(vertex.length, index);
        //首先更新起始结点
        update(index);
        //遍历所有结点
        for (int i = 1; i < vertex.length; i++)
        {
            int num = vc.getNext();
            update(num);
        }
        showResult(vertex);
    }

    /**
     *更新当前点对应的数据
     *@param index 当前更新点的索引
     */
    public void update(int index)
    {
        //首先标记已经访问过该点了
        vc.visited[index] = 1;

        int len = 0;
        //更新该结点的所有邻接结点的距离
        for (int i = 0; i < matrix[index].length; i++)
        {
            //如果index的邻接结点没有访问过了， 前面保证了是未访问，后面保证了是邻接节点
            if(!vc.in(i) && matrix[index][i] != Integer.MAX_VALUE)
            {
                //len代表了出发结点到index的距离 + index到它的邻接结点的距离之和
                len = vc.getDis(index) + matrix[index][i];
                //如果未访问过该结点，且走这条路优于之前的记录，更新
                if (len < vc.getDis(i))
                {
                    //更新距离和前驱结点
                    vc.updateDis(i, len);
                    vc.updatePre(i, index);
                }
            }
        }
    }
    //输出最终结果
    public void showResult(char[] vertex)
    {
        vc.show(vertex);
    }

    //展示图的方法
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