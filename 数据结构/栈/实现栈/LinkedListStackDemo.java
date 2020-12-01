import java.io.*;
import java.util.*;

public class LinkedListStackDemo 
{
	public static void main(String[] args) 
	{
		//首先创建一个栈，用输入流来测试功能
		LinkedListStack stack = new LinkedListStack(5);
		Scanner sc = new Scanner(System.in);

		boolean flag = true;

		//循环测试功能
		while (flag)
		{
			System.out.println("push:入栈--pop:出栈--show:展示栈--exit:退出");
			switch (sc.next())
			{
				case "push":
					try
					{
						System.out.println("请输入一个int类型的数据");
						int data = sc.nextInt();
						stack.push(data);
						break;
					}
					catch (Exception e)
					{
						System.out.println("数据格式错误，请重新选择");
					}
				case "pop":
					try
					{
						stack.pop();
						break;
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
				case "show":
					stack.list();
					break;
				case "exit":
					sc.close();
					flag = false;
					break;
				default:
					System.out.println("命令输入有误");
					break;
			}
		}
		System.out.println("程序已退出");
	}
}

class LinkedListStack
{
	//用数组实现 包括 容量、栈顶、存放数据的数组
	private int maxSize;
	private SingleLinkedList stack;
	private int top;

	public LinkedListStack(int maxSize)
	{
		try
		{
			this.maxSize = maxSize;
			stack = new SingleLinkedList();
			this.top = -1;
		}
		catch (Exception e)
		{
			System.out.println("栈的大小不符合规定！！");
		}
	}

	//判断栈是否非空
	public boolean ifEmpty()
	{
		return top == -1;
	}

	//判断栈是否是满的
	public boolean ifFull ()
	{
		return top == maxSize - 1;
	}

	//入栈操作(push)
	public void push (int data)
	{
		if (ifFull())
		{
			System.out.println("栈已满，无法入栈~~");
			return;
		}

		//如果栈没满，将top上移，然后放置数据
		top++;
		stack.add(data);
	}

	//出栈操作（pop）
	public int pop ()
	{
		if (ifEmpty())
		{
			throw new RuntimeException("栈为空");
		}

		//如果有数据，先将数据存储，然后将栈顶下移一位
		int result = stack.delete().getData();
		top--;
		return result;
	}

	public void list ()
	{
		if (ifEmpty())
		{
			System.out.println("目前栈为空");
		}
		//倒叙展示数组
		stack.inverseList();
	}
}

//每个链表存放的节点类型
class IntPoint
{
	private int data;
	private IntPoint next;
	
	public IntPoint(){}

	public IntPoint(int data)
	{
		this.data = data;
	}
	
	public void setData(int data)
	{
		this.data = data;
	}

	public int getData()
	{
		return this.data;
	}

	public void setNext(IntPoint next)
	{
		this.next = next;
	}

	public IntPoint getNext()
	{
		return this.next;
	}
}

//实现栈的链表
class SingleLinkedList
{
	private IntPoint head;

	public SingleLinkedList()
	{
		head = new IntPoint();
	}

	public IntPoint getHead()
	{
		return this.head;
	}

	//判断是否为空
	private boolean ifEmpty()
	{
		return head.getNext() == null;
	}

	//往链表尾部添加数据的方法
	public void add(int data)
	{
		//要添加进去的节点
		IntPoint point = new IntPoint(data);
		//定义一个辅助节点，对链表进行遍历
		IntPoint tool = head;
		while (true)
		{
			if (tool.getNext() == null)
			{
				break;
			}
			tool = tool.getNext();
		}

		//tool就是当前链表的最后一个节点
		tool.setNext(point);
	}

	//删除链表最后一个元素来实现出栈
	public IntPoint delete()
	{
		//如果链表为空，直接退出
		if (ifEmpty())
		{
			System.out.println("当前无数据~");
			return null;
		}

		//如果不为空，构造遍历节点，删除元素
		IntPoint tool = head;
		
		while (true)
		{
			//首先判断tool是不是要删除的节点的前一个节点
			if (tool.getNext().getNext() == null)
			{
				break;
			}
			//如果不是最后一个节点
			tool = tool.getNext();
		}
		//删除节点
		IntPoint result = tool.getNext();
		tool.setNext(null);
		return result;
	}

	//倒序显示链表
	public void inverseList()
	{
		if (ifEmpty()) return;

		//定义辅助节点遍历
		IntPoint tool = head;
		//定义栈存放数据
		Stack<IntPoint> stack = new Stack<>();
		//统计有多少数据
		int num = 0;

		//遍历存放数据
		while(true)
		{
			tool = tool.getNext();
			if (tool == null)
			{
				break;
			}

			stack.push(tool);
			num++;
		}
		
		while (num > 0)
		{
			System.out.println("stack[" + num-- + "] = " + stack.pop().getData());
		}
	}
}