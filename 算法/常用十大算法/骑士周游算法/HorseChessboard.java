/*
	��ʿ�����㷨��ָ һ�������� m*n �������ϣ��� �� ���ߣ���α������е�����
	������  DFS��������ȱ�����Deep First Search�� ��Ӧ�ã����ݹ����

	��1������ǰ�����п����ߵĵ㱣���һ���б����8���������ÿ��һ����step+1
	��2����������list�ĵ㣬��������ߵ�ͨ����������������
	��3�����������󣬿��Ƿ������
		3.1����Ѿ���������finished=true��
		3.2���û�н������ߵ���һ��˵���õ���·���ߣ���Ҫ�� ��λ�õĵ���Ϊ0��Ȼ��step-1��

	//***************************************************************************
	�Ż����������̱������޹���������ݹ�ʱ��ϳ��������� �б��ж�Ӧ�ĵ��� ���ߵĲ���Խ��Ӧ��Խ���ߣ��������Լ��ٵݹ����
	��̰���㷨��
		�ڻ�ȡ��ArrayList�б�󣬶��б��������Ȼ���Էǵݼ���˳����б�����������̱��������������㷨����
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
		//����һ��������
		ChessBoard cb = new ChessBoard(size, size);
		System.out.println("�㷨��ʼ��"); 
		long start = System.currentTimeMillis();
		cb.move(x, y, 1);
		System.out.println("�㷨�ܹ���ʱ��" + (System.currentTimeMillis() - start) + "ms");
	}
}

class ChessBoard
{
	//���̵ĳߴ磬X���У�Y����
	private final int x;
	private final int y;

	//��¼�Ƿ���ʹ�������
	//ֻҪ���ȶ�Ϊ X*Y �����ʹ��һά������ʵ�����̵ı��
	private static int[] visited;
	//��¼����
	private static int step;
	private static boolean finished = false;

	//���������Բ������г�ʼ��
	public ChessBoard(int row, int column)
	{
		this.x = row;
		this.y = column;
		visited = new int[x * y];
	}

	/**
	 *�����㷨��
	 *@param row ���ӵ�ǰ����λ�õ� x ����
	 *@param column ���ӵ�ǰ���ڵ� y ����
	 *@param step �Ѿ��ߵĲ�������ʼλ��Ϊ1
	 */
	public void move(int row, int column, int step)
	{
		if (finished)
		{
			return;
		}
		//���Ƚ���λ�ñ���߹�
		visited[row * x + column] = step;
		//��ȡ���е���һ�������ߵĵ�ļ���
		ArrayList<Point> list = getNext(new Point(row, column));
		
		//**************�Ż�*******************
		sort(list);
		
		//ֻҪû�б��������еı��ͼ���ִ��
		for (Point p : list)
		{
			//����ִ�иõ�ĵ�һ������λ��
			//����õ�û�߹�
			if (visited[p.x * x + p.y] == 0)
			{
				move(p.x, p.y, step + 1);
			}
		}

		//�������������Ҫô�����Ѿ���ͷ�ˣ�Ҫô������·
		if (step == x * y)
		{
			//���
			finished = true;
			show();
		}
		else
		{
			visited[row * x + column] = 0;
			step--;
		}
	}

	//�ɵ�ǰ���ȡ��һ���������������п��ߵ�ļ���
	private ArrayList<Point> getNext(Point p)
	{
		//���ȶ���һ���������ݵ�����
		ArrayList<Point> list = new ArrayList<>();
		Point p1 = new Point();
		//���ڵ�ǰ��������������8���㣬���ο��Ƿ���������
		//���Ͻ�������
		if ((p1.x = p.x - 2) >= 0 && (p1.y = p.y - 1) >= 0)
		{
			list.add(new Point(p1));
		}
		if ((p1.x = p.x - 1) >= 0 && (p1.y = p.y - 2) >= 0)
		{
			list.add(new Point(p1));
		}

		//���Ͻ�������
		if ((p1.x = p.x + 1) < x && (p1.y = p.y - 2) >= 0)
		{
			list.add(new Point(p1));
		}
		if ((p1.x = p.x + 2) < x && (p1.y = p.y - 1) >= 0)
		{
			list.add(new Point(p1));
		}

		//���½�������
		if ((p1.x = p.x + 2) < x && (p1.y = p.y + 1) < y)
		{
			list.add(new Point(p1));
		}
		if ((p1.x = p.x + 1) < x && (p1.y = p.y + 2) < y)
		{
			list.add(new Point(p1));
		}

		//���½�������
		if ((p1.x = p.x - 1) >= 0 && (p1.y = p.y + 2) < y)
		{
			list.add(new Point(p1));
		}
		if ((p1.x = p.x - 2) >= 0 && (p1.y = p.y + 1) < y)
		{
			list.add(new Point(p1));
		}
		
		//��ӽ����󷵻�
		return list;
	}

	//���б����������㷨
	private void sort(ArrayList<Point> al)
	{
		al.sort((Point p1, Point p2) ->
		{
			return getNext(p1).size() - getNext(p2).size();
		});
	}

	//���չʾ���̵��㷨
	private void show()
	{
		System.out.println("����Ϊ" + x + " * " + y + "�����̣����Ϊ��");
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