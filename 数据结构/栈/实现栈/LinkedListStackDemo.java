import java.io.*;
import java.util.*;

public class LinkedListStackDemo 
{
	public static void main(String[] args) 
	{
		//���ȴ���һ��ջ���������������Թ���
		LinkedListStack stack = new LinkedListStack(5);
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

class LinkedListStack
{
	//������ʵ�� ���� ������ջ����������ݵ�����
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
		stack.add(data);
	}

	//��ջ������pop��
	public int pop ()
	{
		if (ifEmpty())
		{
			throw new RuntimeException("ջΪ��");
		}

		//��������ݣ��Ƚ����ݴ洢��Ȼ��ջ������һλ
		int result = stack.delete().getData();
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
		stack.inverseList();
	}
}

//ÿ�������ŵĽڵ�����
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

//ʵ��ջ������
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

	//�ж��Ƿ�Ϊ��
	private boolean ifEmpty()
	{
		return head.getNext() == null;
	}

	//������β��������ݵķ���
	public void add(int data)
	{
		//Ҫ��ӽ�ȥ�Ľڵ�
		IntPoint point = new IntPoint(data);
		//����һ�������ڵ㣬��������б���
		IntPoint tool = head;
		while (true)
		{
			if (tool.getNext() == null)
			{
				break;
			}
			tool = tool.getNext();
		}

		//tool���ǵ�ǰ��������һ���ڵ�
		tool.setNext(point);
	}

	//ɾ���������һ��Ԫ����ʵ�ֳ�ջ
	public IntPoint delete()
	{
		//�������Ϊ�գ�ֱ���˳�
		if (ifEmpty())
		{
			System.out.println("��ǰ������~");
			return null;
		}

		//�����Ϊ�գ���������ڵ㣬ɾ��Ԫ��
		IntPoint tool = head;
		
		while (true)
		{
			//�����ж�tool�ǲ���Ҫɾ���Ľڵ��ǰһ���ڵ�
			if (tool.getNext().getNext() == null)
			{
				break;
			}
			//����������һ���ڵ�
			tool = tool.getNext();
		}
		//ɾ���ڵ�
		IntPoint result = tool.getNext();
		tool.setNext(null);
		return result;
	}

	//������ʾ����
	public void inverseList()
	{
		if (ifEmpty()) return;

		//���帨���ڵ����
		IntPoint tool = head;
		//����ջ�������
		Stack<IntPoint> stack = new Stack<>();
		//ͳ���ж�������
		int num = 0;

		//�����������
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