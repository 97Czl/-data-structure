import java.io.*;
import java.util.Scanner;

public class ArrayQueueTest 
{
	public static void main(String[] args) 
	{
		//对数组实现的队列进行测试
		//创建一个长度是5的队列，进行初始化
		ArrayQueue arr = new ArrayQueue(5);
		boolean flag = true;
		Scanner scanner = new Scanner(System.in);

		while (flag)
		{
			//提示帮助信息
			System.out.println("a(addQueue):往队列中添加数字");
			System.out.println("g(getQueue):从队列中取出数字");
			System.out.println("h(showHead):显示队列中头元素");
			System.out.println("s(showQueue):显示队列中所有元素");
			System.out.println("e(exit):退出");

			switch (scanner.next().charAt(0))//next()返回的是String，然后charAt定位第一个字母
			{
			case 'a':
				try
				{
					arr.addQueue(scanner.nextInt());
				}
				catch (RuntimeException e)
				{
					System.out.println("队列已满，不可以添加数据");
					System.out.println(e.getMessage());
				}
				break;
			case 'g':
				try
				{
					System.out.println("取出的数据是：" + arr.getQueue());
				}
				catch (RuntimeException e)
				{
					System.out.println("队列是空的，无数据");
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try
				{
					System.out.println("队列头数据是：" + arr.getHead());
				}
				catch (RuntimeException e)
				{
					System.out.println("队列是空的，无数据");
					System.out.println(e.getMessage());
				}
				break;
			case 's':
				arr.showQueue();
				break;
			case 'e':
				flag = false;
				System.out.println("程序已退出！！");
				scanner.close();
				break;
			default:
				break;
			}
		}
	}
}

//实现队列的数组
class ArrayQueue
{
	//队列的基本要素
	private int maxSize;//队列的容量
	private int front;  //队列的头指针位置（指向队列头的前一个位置）
	private int rear;   //队列的尾指针位置（指向队列的最后一个数据）
	private int[] array;//存放队列数据的数组

	public ArrayQueue(int maxSize)
	{
		this.maxSize = maxSize;//不进行这一步 会使得一直为0，即队列始终为满
		//初始化数组
		array = new int[maxSize];
		//对指针初始化
		front = -1;
		rear = -1;
	}
		
	//判断队列是否为空
	public boolean isEmpty()
	{
		return front == rear;
	}

	//判断队列是否满
	public boolean isFull()
	{
		return rear == maxSize - 1;
	}

	//往队列添加数据
	public void addQueue(int data)
	{
		//如果队伍已满
		if (isFull())
		{
			throw new RuntimeException();
		}
		//如果队伍未满，即可以继续添加
		rear++;//如果添加数据 尾指针需要移动一位
		array[rear] = data;
	}

	//取出队列中的数据
	public int getQueue()
	{
		//如果队伍为空
		if (isEmpty())
		{
			throw new RuntimeException();
		}
		//如果队伍不空，取出数据
		front++;//取出数据时，头指针向前移动一位
		return array[front];
	}

	//打印队列中所有数据
	public void showQueue()
	{
		for (var i = 0; i < array.length; i++)
		{
			System.out.printf("array[%d] = %d\n", i, array[i]);
		}
	}

	//显示队列的头部数据
	public int getHead()
	{
		//如果队伍为空
		if (isEmpty())
		{
			throw new RuntimeException();
		}
		//如果队伍不空，显示队列的头部数据
		return array[front + 1];
	}
}
