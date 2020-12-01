public class AllFunction 
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
		DuLinkedList sll = new DuLinkedList();
		sll.addByOrder(heroNode1);
		sll.addByOrder(heroNode4);
		sll.addByOrder(heroNode2);
		sll.addByOrder(heroNode3);
		sll.addByOrder(heroNode5);

		sll.showLinkedList();
		sll.delete(2);sll.delete(5);


		//չʾ����
		sll.showLinkedList();
	}
}

class DuLinkedList
{
	//˫���������һ��ͷ�ڵ�
	private HeroNode head = new HeroNode();

	//--------------------------------------------------��ȡ����ͷ�ڵ�-------------------------------------------------------
	public HeroNode getHead()
	{
		return this.head;
	}

	//--------------------------------------------------�������������-------------------------------------------------------
	//Ĭ����ӵ����������
	public void addDefault(HeroNode hero)
	{
		//���帨�������ڵ�
		HeroNode toolMan = this.head;
		//�жϸýڵ��Ƿ����������Ѿ�����
		boolean ifExist = false;
		//����
		while (true)
		{
			if (toolMan.getNo() == hero.getNo())
			{
				ifExist = true;
				break;
			}
			if (toolMan.getNext() == null)
			{
				break;
			}
			toolMan = toolMan.getNext();
		}
		//��toolMan�������Ľڵ�
		//�������
		if (ifExist)
		{
			//���Ҫ��ӵ������Ѿ���������
			System.out.println("�ýڵ��Ѿ����ڣ����ʧ�ܣ���");
			return;
		}
		hero.setPre(toolMan);
		hero.setNext(toolMan.getNext());
		toolMan.setNext(hero);
	}

	//����˳�����Ԫ��
	public void addByOrder(HeroNode hero)
	{
		//���帨���ڵ�������
		HeroNode toolMan = this.head;
		//�жϵ�ǰ�����Ƿ��Ѿ����ڸýڵ�
		boolean ifExist = false;
		//����
		while (true)
		{
			if (toolMan.getNo() == hero.getNo())
			{
				ifExist = true;
				break;
			}
			if (toolMan.getNext() == null)
			{
				break;
			}
			if (toolMan.getNext().getNo() > hero.getNo())
			{
				break;
			}
			toolMan = toolMan.getNext();
		}
		//��toolMan���ǲ���λ��ǰһ���Ľڵ�
		//�������
		if (ifExist)
		{
			//���Ҫ��ӵ������Ѿ���������
			System.out.println("�ýڵ��Ѿ����ڣ����ʧ�ܣ���");
			return;
		}
		hero.setPre(toolMan);
		hero.setNext(toolMan.getNext());
		toolMan.setNext(hero);
	}

	//--------------------------------------------------�༭����-------------------------------------------------------
	public void edit(int no, String name, String nickName)
	{
		//���帨���ڵ�������
		HeroNode toolMan = this.head;
		//�жϵ�ǰ�����Ƿ���ڸýڵ�
		boolean ifExist = false;
		//����
		while (true)
		{
			if (toolMan.getNo() == no)
			{
				ifExist = true;
				break;
			}
			if (toolMan.getNext() == null)
			{
				break;
			}
			toolMan = toolMan.getNext();
		}
		//��toolMan����Ҫ�޸ĵĽڵ�
		if (ifExist)
		{
			toolMan.setName(name);
			toolMan.setNickName(nickName);
			System.out.println("�޸ĳɹ�~~");
			return;
		}
		else 
		{
			System.out.printf("������û�����Ϊ%d��Ӣ��,�޸�ʧ��~\n", no);
			return;
		}
	}

	//--------------------------------------------------ɾ������-------------------------------------------------------
	public void delete(int no)
	{
		//���帨���ڵ�������
		HeroNode toolMan = this.head;
		//�жϵ�ǰ�����Ƿ���ڸýڵ�
		boolean ifExist = false;
		//����
		while (true)
		{
			if (toolMan.getNo() == no)
			{
				ifExist = true;
				break;
			}
			if (toolMan.getNext() == null)
			{
				break;
			}
			toolMan = toolMan.getNext();
		}
		//��toolMan����Ҫɾ���Ľڵ�
		if (ifExist)
		{
			toolMan.getPre().setNext(toolMan.getNext());
			//���Ҫɾ���������һ���ڵ㣬��������������ָ���쳣
			if (toolMan.getNext() != null)
			{
				toolMan.getNext().setPre(toolMan.getPre());
			}
			System.out.println("ɾ���ɹ�~~");
			return;
		}
		else 
		{
			System.out.printf("������û�����Ϊ%d��Ӣ��,ɾ��ʧ��~\n", no);
			return;
		}
	}

	//--------------------------------------------------��ʾ����-------------------------------------------------------
	//��ʾ����
	public void showLinkedList()
	{
		HeroNode toolMan = this.head.getNext();
		while (true)
		{
			System.out.println(toolMan);
			if (toolMan.getNext() == null)
			{
				break;
			}
			toolMan = toolMan.getNext();
		}
	}
}

//����һ�������Ԫ����
class HeroNode
{
	//���ݰ��������������֣��ºţ���һ��Ӣ�۵ĵ�ַ��ǰһ��Ӣ�۵ĵ�ַ
	private int no;
	private String name;
	private String nickName;
	private HeroNode next;
	private HeroNode pre;
	
	//�޲ι�����
	public HeroNode(){}

	//�вι�����
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

	//����name
	public void setName(String name)
	{
		this.name = name;
	}

	//��ѯname
	public String getName()
	{
		return this.name;
	}

	//����nickName
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	//��ѯnickName
	public String getNickName()
	{
		return this.nickName;
	}

	//����next
	public void setNext(HeroNode next)
	{
		this.next = next;
	}

	//��ѯnext
	public HeroNode getNext()
	{
		return this.next;
	}

	//����pre
	public void setPre(HeroNode pre)
	{
		this.pre = pre;
	}

	//��ѯpre
	public HeroNode getPre()
	{
		return this.pre;
	}

	//��дtoString������������
	public String toString()
	{
		return "HeroNode[no = " + no + ",name = " + name + ",nickName = " + nickName + "]";
	}
} 