public class LinkedListTest 
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
		sll.addElement(heroNode1);
		sll.addElement(heroNode2);
		sll.addElement(heroNode3);
		sll.addElement(heroNode4);
		sll.addElement(heroNode5);

		//--------------
		//�����ظ���ӣ������������ѭ������Ϊ�ظ����ʱ���Ὣ����next��Ϣ�Ľڵ���ӽ�ȥ
		//--------------

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