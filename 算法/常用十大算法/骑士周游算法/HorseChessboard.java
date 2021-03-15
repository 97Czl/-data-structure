/*
	骑士周游算法是指 一个棋子在 m*n 的棋盘上，以 日 字走，如何遍历所有的棋盘
	本质是  DFS（深度优先遍历，Deep First Search） 的应用，即递归回溯

	（1）将当前的所有可以走的点保存成一个列表（最多8种情况），每走一步，step+1
	（2）遍历所有list的点，如果可以走的通，则继续，否则回溯
	（3）遍历结束后，看是否结束，
		3.1如果已经结束，则finished=true；
		3.2如果没有结束，走到这一步说明该点无路可走，需要将 该位置的点置为0，然后将step-1；

	//***************************************************************************
	优化：上述过程遍历是无规则遍历，递归时间较长，理论上 列表中对应的点所 可走的步数越少应该越先走，这样可以减少递归次数
	即贪心算法：
		在获取了ArrayList列表后，对列表进行排序，然后以非递减的顺序进行遍历，大幅缩短遍历次数，提升算法性能
*/

import java.awt.*;
import java.util.*;

public class HorseChessboard 
{
	public static void main(String[] args) 
	{
		int size = 8;
		int x = 0;
		int y = 0;
		//定义一个的棋盘
		ChessBoard cb = new ChessBoard(size, size);
		System.out.println("算法开始："); 
		long start = System.currentTimeMillis();
		cb.move(x, y, 1);
		System.out.println("算法总共用时：" + (System.currentTimeMillis() - start) + "ms");
	}
}

class ChessBoard
{
	//棋盘的尺寸，X是行，Y是列
	private final int x;
	private final int y;

	//记录是否访问过的数组
	//只要长度定为 X*Y 则可以使用一维数组来实现棋盘的标记
	private static int[] visited;
	//记录步数
	private static int step;
	private static boolean finished = false;

	//构造器，对参数进行初始化
	public ChessBoard(int row, int column)
	{
		this.x = row;
		this.y = column;
		visited = new int[x * y];
	}

	/**
	 *周游算法：
	 *@param row 棋子当前所在位置的 x 坐标
	 *@param column 棋子当前所在的 y 坐标
	 *@param step 已经走的步数。初始位置为1
	 */
	public void move(int row, int column, int step)
	{
		if (finished)
		{
			return;
		}
		//首先将该位置标记走过
		visited[row * x + column] = step;
		//获取所有的下一步可以走的点的集合
		ArrayList<Point> list = getNext(new Point(row, column));
		
		//**************优化*******************
		sort(list);
		
		//只要没有遍历完所有的表，就继续执行
		for (Point p : list)
		{
			//尝试执行该点的第一个可走位置
			//如果该点没走过
			if (visited[p.x * x + p.y] == 0)
			{
				move(p.x, p.y, step + 1);
			}
		}

		//如果遍历结束后，要么就是已经到头了，要么就是死路
		if (step == x * y)
		{
			//输出
			finished = true;
			show();
		}
		else
		{
			visited[row * x + column] = 0;
			step--;
		}
	}

	//由当前点获取下一步满足条件的所有可走点的集合
	private ArrayList<Point> getNext(Point p)
	{
		//首先定义一个保存数据的链表
		ArrayList<Point> list = new ArrayList<>();
		Point p1 = new Point();
		//对于当前点的最多可以满足的8个点，依次看是否满足条件
		//左上角两个点
		if ((p1.x = p.x - 2) >= 0 && (p1.y = p.y - 1) >= 0)
		{
			list.add(new Point(p1));
		}
		if ((p1.x = p.x - 1) >= 0 && (p1.y = p.y - 2) >= 0)
		{
			list.add(new Point(p1));
		}

		//右上角两个点
		if ((p1.x = p.x + 1) < x && (p1.y = p.y - 2) >= 0)
		{
			list.add(new Point(p1));
		}
		if ((p1.x = p.x + 2) < x && (p1.y = p.y - 1) >= 0)
		{
			list.add(new Point(p1));
		}

		//右下角两个点
		if ((p1.x = p.x + 2) < x && (p1.y = p.y + 1) < y)
		{
			list.add(new Point(p1));
		}
		if ((p1.x = p.x + 1) < x && (p1.y = p.y + 2) < y)
		{
			list.add(new Point(p1));
		}

		//左下角两个点
		if ((p1.x = p.x - 1) >= 0 && (p1.y = p.y + 2) < y)
		{
			list.add(new Point(p1));
		}
		if ((p1.x = p.x - 2) >= 0 && (p1.y = p.y + 1) < y)
		{
			list.add(new Point(p1));
		}
		
		//添加结束后返回
		return list;
	}

	//对列表进行排序的算法
	private void sort(ArrayList<Point> al)
	{
		al.sort((Point p1, Point p2) ->
		{
			return getNext(p1).size() - getNext(p2).size();
		});
	}

	//最后展示棋盘的算法
	private void show()
	{
		System.out.println("长度为" + x + " * " + y + "的棋盘，结果为：");
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				System.out.print(visited[i * x + j] + "\t");
			}
			System.out.println();
		}
	}

}