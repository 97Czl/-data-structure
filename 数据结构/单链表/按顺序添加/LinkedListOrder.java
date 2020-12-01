public class LinkedListOrder 
{
	public static void main(String[] args) 
	{
		//�½�����Ӣ�۽ڵ� ��ӵ�����
		HeroNode heroNode1 = new HeroNode(1, "�ν�", "��ʱ��");
		HeroNode heroNode2 = new HeroNode(2, "¬����", "������");
		HeroNode heroNode3 = new HeroNode(3, "����", "�Ƕ���");
		HeroNode heroNode4 = new HeroNode(4, "����ʤ", "������");
		HeroNode heroNode5 = new HeroNode(5, "����", "������");

		//����һ������
		SingleLinkedList sll = new SingleLinkedList();
		sll.addByOrder(heroNode1);
		sll.addByOrder(heroNode4);
		sll.addByOrder(heroNode2);
		sll.addByOrder(heroNode3);
		sll.addByOrder(heroNode5);

		//չʾ����
		sll.showLinkedList();
	}
}


//����һ�������������
class SingleLinkedList
{
	//����һ����������ݵ�ͷ�ڵ�
	private HeroNode head = new HeroNode(0, null, null);

	//������������ݵķ���
	public void addElement(HeroNode data)
	{
		//�����ǰ����Ϊ�գ���ֱ�����
		if (head.getNext() == null)
		{
			head.setNext(data);
			return;
		}
		//�����ǰ����Ϊ�գ�
		//1������������ҵ�β����
		//2��������β���������

		//����һ�������ڵ� ����������
		HeroNode toolMan = head;
		while (true)
		{
			toolMan = toolMan.getNext();
			if (toolMan.getNext() == null)
			{
				//�����������β��
				toolMan.setNext(data);
				return;
			}
		}
	}

	// ����˳���������
	public void addByOrder(HeroNode data)
	{
		//���������ڵ㣬��ʵ������������ҵ�Ҫ��ӵ�λ��
		HeroNode toolMan = head;
		//�ж��Ƿ��ظ�
		boolean ifExist = false;
		
		//���������ҵ�Ҫ�����λ��
		while (true)
		{
			if (toolMan.getNext() == null)//˵���Ѿ�����������β��
			{
				break;
			}
			else if (toolMan.getNo() == data.getNo())
			{
				//�����Ѿ����ڸ����� 
				ifExist = true;
				break;
			}
			else if (toolMan.getNext().getNo() > data.getNo())
			{
				//toolMan��next�м����data��Ҫ�����λ��
				break;
			}
			//�Ա����ڵ㸳ֵ���������ѭ��
			toolMan = toolMan.getNext();
		}
		//����Ѿ���������
		if (ifExist)
		{
			System.out.printf("���Ϊ %d ��Ӣ���Ѿ����ڣ��޷���ӣ���\n", data.getNo());
			return;
		}
		//������ӵĻ�����������ӵ������ڵ��next�м�
		data.setNext(toolMan.getNext());//��Ҫ����Ľڵ��next���óɱ����ڵ��next
		toolMan.setNext(data);//�������ڵ��next���ó�Ҫ����Ľڵ�
	}

	public void showLinkedList()
	{
		//�����ø����ڵ����
		HeroNode toolMan = head;
		while (true)
		{
			toolMan = toolMan.getNext();
			System.out.println(toolMan);
			if (toolMan.getNext() == null)
			{
				//�����������β��
				return;
			}
		}
	}
}

//����һ�������Ԫ����
class HeroNode
{
	//���ݰ��������������֣��ºţ���һ��Ӣ�۵ĵ�ַ
	private int no;
	private String name;
	private String nickName;
	private HeroNode next;

	//������
	public HeroNode(int no, String name, String nickName)
	{
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}

	public int getNo()
	{
		return this.no;
	}

	//����next
	public void setNext(HeroNode next)
	{
		this.next = next;
	}

	//��ѯnext
	public HeroNode getNext()
	{
		return next;
	}

	//��дtoString������������
	public String toString()
	{
		return "HeroNode[no = " + no + ",name = " + name + ",nickName = " + nickName + "]";
	}
} 