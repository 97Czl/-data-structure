public class Josephu 
{
	public static void main(String[] args) 
	{
		CircleSingleLinkedList csll = new CircleSingleLinkedList();
		csll.countBoy(3, 4, 6);
		csll.showList();

	}
}


//����һ�������������
class CircleSingleLinkedList
{
	private Boy first = null;

	//�������������
	public void add(int num)
	{
		if (num < 1)
		{
			System.out.println("����Ԫ�صĸ��������Ϲ淶~~");
			return;
		}
		//���������Ԫ��
		//����һ������������ʵ�ֱ���
		Boy current = first;
		for (var i = 1; i <= num; i++)
		{
			Boy boy = new Boy(i);
			if (i == 1)//����ǵ�һ��Ԫ�أ���firstָ���� �γ�һ���ջ�
			{
				first = boy;
				first.setNext(first);
				current = first;
				continue;
			}
			//������������룬��current��ʾ��Ҫ�����ǰһ���ڵ�
			current.setNext(boy);
			boy.setNext(first);
			current = current.getNext();
		}
	}

	/**
	 *@param startNo �ʼ�����Ľڵ�
	 *@param countNum ÿ�μ����ĸ���
	 *@param nums ��������
	 */
	public void countBoy(int startNo, int countNum, int nums)
	{
		//�ȴ�������nums�����ӵĻ�������
		if (nums < startNo || startNo < 1 || countNum < 1)
		{
			System.out.println("���������Ϲ淶");
			return;
		}
		add(nums);
		//���帨���ڵ㣬��¼first��Ľڵ㣬��ʵ�ֳ���
		Boy helper = first;
		//���������һ��,ָ��helper
		while (true)
		{
			if (helper.getNext() == first)
			{
				break;
			}
			helper = helper.getNext();
		}
		//�ӵ�startNo�����ӿ�ʼ������helper��first��Ҫ�����ƶ�startNo��λ��
		for (var i = 0; i < startNo - 1; i++)
		{
			helper = helper.getNext();
			first = first.getNext();
		}

		//��ʼ����
		while (true)
		{
			if (first == helper)//��������ھ�ʣһ��Ԫ�أ���first == helper��ֱ���˳�ѭ����
			{
				break;
			}
			//first��helper�������ƶ�countNum��λ�ã�Ȼ��firstָ����һ����ɾ����first��helper�м��Ԫ��
			for (var j = 0; j < countNum - 1; j++)
			{
				helper = helper.getNext();
				first = first.getNext();
			}
			System.out.println(first + "�ѳ���");
			first = first.getNext();
			helper.setNext(first);
		}
		//����ʣ��һ��Ԫ�س���
		System.out.println(first + "�ѳ���");
		first = null;
		return;
	}

	//չʾ����Ԫ��
	public void showList()
	{
		//�������Ϊ��
		if (first == null)
		{
			System.out.println("����Ϊ��~");
			return;
		}

		//���帨���ڵ�
		Boy current = first;
		while (true)
		{
			System.out.println(current);
			if (current.getNext() == first)
			{
				break;
			}
			current = current.getNext();
		}
	}
}

//����һ�������Ԫ����
class Boy
{
	//���ݰ�������������һ��С���ĵ�ַ
	private int no;
	private Boy next;

	//������
	public Boy(int no)
	{
		this.no = no;
	}

	public int getNo()
	{
		return this.no;
	}

	//����next
	public void setNext(Boy next)
	{
		this.next = next;
	}

	//��ѯnext
	public Boy getNext()
	{
		return next;
	}

	//��дtoString������������
	public String toString()
	{
		return "Boy[no = " + no + "]";
	}
} 