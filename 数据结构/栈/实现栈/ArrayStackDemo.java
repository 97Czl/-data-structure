import java.io.*;
import java.util.*;

public class ArrayStackDemo 
{
	public static void main(String[] args) 
	{
		//���ȴ���һ��ջ���������������Թ���
		ArrayStack stack = new ArrayStack(5);
		Scanner sc = new Scanner(System.in);

		boolean flag = true;

		//ѭ�����Թ���
		while (flag)
		{
			System.out.println("push:��ջ--pop:��ջ--show:չʾջ--exit:�˳�");
			switch (sc.next())
			{
				case "push":
					try
					{
						System.out.println("������һ��int���͵�����");
						int data = sc.nextInt();
						stack.push(data);
						break;
					}
					catch (Exception e)
					{
						System.out.println("���ݸ�ʽ����������ѡ��");
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
					System.out.println("������������");
					break;
			}
		}
		System.out.println("�������˳�");
	}
}

class ArrayStack
{
	//������ʵ�� ���� ������ջ����������ݵ�����
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
			System.out.println("ջ�Ĵ�С�����Ϲ涨����");
		}
	}

	//�ж�ջ�Ƿ�ǿ�
	public boolean ifEmpty()
	{
		return top == -1;
	}

	//�ж�ջ�Ƿ�������
	public boolean ifFull ()
	{
		return top == maxSize - 1;
	}

	//��ջ����(push)
	public void push (int data)
	{
		if (ifFull())
		{
			System.out.println("ջ�������޷���ջ~~");
			return;
		}

		//���ջû������top���ƣ�Ȼ���������
		top++;
		stack[top] = data;
	}

	//��ջ������pop��
	public int pop ()
	{
		if (ifEmpty())
		{
			throw new RuntimeException("ջΪ��");
		}

		//��������ݣ��Ƚ����ݴ洢��Ȼ��ջ������һλ
		int result = stack[top];
		top--;
		return result;
	}

	public void list ()
	{
		if (ifEmpty())
		{
			System.out.println("ĿǰջΪ��");
		}
		//����չʾ����
		for (var i = top; i >= 0; i--)
		{
			System.out.printf("stack[%d] = %d\n", i, stack[i]);
		}
	}
}
