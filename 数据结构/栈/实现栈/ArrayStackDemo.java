import java.io.*;
import java.util.*;

public class ArrayStackDemo 
{
	public static void main(String[] args) 
	{
		//首先创建一个栈，用输入流来测试功能
		ArrayStack stack = new ArrayStack(5);
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

class ArrayStack
{
	//用数组实现 包括 容量、栈顶、存放数据的数组
	private int maxSize;
	private int[] stack;
	private int top;

	public ArrayStack(int maxSize)
	{
		try
		{
			this.maxSize = maxSize;
			stack = new int[this.maxSize];
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
		stack[top] = data;
	}

	//出栈操作（pop）
	public int pop ()
	{
		if (ifEmpty())
		{
			throw new RuntimeException("栈为空");
		}

		//如果有数据，先将数据存储，然后将栈顶下移一位
		int result = stack[top];
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
		for (var i = top; i >= 0; i--)
		{
			System.out.printf("stack[%d] = %d\n", i, stack[i]);
		}
	}
}
