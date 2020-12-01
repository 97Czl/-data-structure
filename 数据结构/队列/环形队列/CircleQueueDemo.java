import java.io.*;
import java.util.Scanner;

public class CircleQueueDemo
{
	public static void main(String[] args) 
	{
		//������ʵ�ֵĶ��н��в���
		//����һ��������5�Ķ��У����г�ʼ��
		CircleQueue arr = new CircleQueue(5);
		boolean flag = true;
		Scanner scanner = new Scanner(System.in);

		while (flag)
		{
			//��ʾ������Ϣ
			System.out.println("a(addQueue):���������������");
			System.out.println("g(getQueue):�Ӷ�����ȡ������");
			System.out.println("h(showHead):��ʾ������ͷԪ��");
			System.out.println("s(showQueue):��ʾ����������Ԫ��");
			System.out.println("e(exit):�˳�");

			switch (scanner.next().charAt(0))//next()���ص���String��Ȼ��charAt��λ��һ����ĸ
			{
			case 'a':
				try
				{
					System.out.println("������һ�����֣�");
					arr.addQueue(scanner.nextInt());
				}
				catch (RuntimeException e)
				{
					System.out.println("�����������������������");
					System.out.println(e.getMessage());
				}
				break;
			case 'g':
				try
				{
					System.out.println("ȡ���������ǣ�" + arr.getQueue());
				}
				catch (RuntimeException e)
				{
					System.out.println("�����ǿյģ����޷�ȡ������");
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try
				{
					System.out.println("����ͷ�����ǣ�" + arr.getHead());
				}
				catch (RuntimeException e)
				{
					System.out.println("�����ǿյģ���");
					System.out.println(e.getMessage());
				}
				break;
			case 's':
				try
				{
					arr.showQueue();
				}
				catch (RuntimeException e)
				{
					System.out.println("�����ǿյģ����޷���ʾ����");
				}
				break;
			case 'e':
				flag = false;
				System.out.println("�������˳�����");
				scanner.close();
				break;
			default:
				break;
			}
		}
	}
}

//ʵ�ֶ��е�����
class CircleQueue
{
	//���еĻ���Ҫ��
	private int maxSize;//���е�����
	private int front;  //���е�ͷָ��λ�ã�ָ����еĵ�һ�����ݣ�
	private int rear;   //���е�βָ��λ�ã�ָ����е����һ�����ݵĺ�һ��λ�ã� /////��λ�ÿճ��� ��ΪԼ�� ���������
	private int[] array;//��Ŷ������ݵ�����

	public CircleQueue(int maxSize)
	{
		this.maxSize = maxSize;//��������һ�� ��ʹ��һֱΪ0��������ʼ��Ϊ��
		//��ʼ������
		array = new int[maxSize];
		//��ָ���ʼ��
		front = 0;
		rear = 0;
	}
		
	//�ж϶����Ƿ�Ϊ��
	public boolean isEmpty()
	{
		return front == rear;
	}

	//�ж϶����Ƿ���
	public boolean isFull()
	{
		return (rear + 1) % maxSize == front;
	}

	//�������������                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
	public void addQueue(int data)
	{
		//�����������
		if (isFull())
		{
			throw new RuntimeException();
		}
		//�������δ���������Լ������
		array[rear] = data;
		//���������� βָ����Ҫ�ƶ�һλ
		rear = (rear + 1) % maxSize;
	}

	//ȡ�������е�����
	public int getQueue()
	{
		//�������Ϊ��
		if (isEmpty())
		{
			throw new RuntimeException();
		}
		//������鲻�գ�ȡ������
		var returnData = array[front];
		front = (front + 1) % maxSize;//ȡ������ʱ��ͷָ����ǰ�ƶ�һλ,��ȡģ����Խ��
		return returnData;
	}

	//��ӡ��������������
	public void showQueue()
	{
		//�������Ϊ��
		if (isEmpty())
		{
			throw new RuntimeException();
		}
		for (var i = front; i < front + getSize(); i++)
		{
			System.out.printf("array[%d] = %d\n", i % maxSize, array[i % maxSize]);
		}
	}

	//���㵱ǰ������ݵĸ���
	public int getSize()
	{
		return (rear + maxSize - front) % maxSize;
	}

	//��ʾ���е�ͷ������
	public int getHead()
	{
		//�������Ϊ��
		if (isEmpty())
		{
			throw new RuntimeException();
		}
		//������鲻�գ���ʾ���е�ͷ������
		return array[front];
	}
}
