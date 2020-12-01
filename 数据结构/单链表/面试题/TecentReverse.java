import java.util.*;

public class TecentReverse 
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

		System.out.println("\n�����ӡ:");
		
		//�����ӡ����
		SingleLinkedList.inversePrint(sll.getHead());
	}
}


//����һ�������������
class SingleLinkedList
{
	//����һ����������ݵ�ͷ�ڵ�
	private HeroNode head = new HeroNode();

	//----------------������---------------------
	public SingleLinkedList(){}

	public SingleLinkedList(HeroNode head)
	{
		this.head = head;
	}
	
	/**
	 *@param head �����ͷ�ڵ�
	 *@return �������Ч�ڵ����
	 */
	public static int getNumber(HeroNode head)
	{
		//�������Ϊ��
		if (head.getNext() == null)
		{
			return 0;
		}
		//�������Ϊ�գ���������
		HeroNode toolMan = head;
		//ͳ����Ч�ڵ����
		int num = 0;
		while (true)
		{
			if (toolMan.getNext() != null)
			{
				num++;
			}
			else break;
			toolMan = toolMan.getNext();
		}
		return num;
	}

	/**
	 *��Ѷ����������з�ת
	 *@param head �����ͷ�ڵ�
	 *@return ������ͷ�ڵ�
	 */
	public static SingleLinkedList inverse(HeroNode head)
	{
		//�������Ϊ��
		if (head.getNext() == null)
		{
			return new SingleLinkedList();
		}
		//�������Ϊ��
		//1�����帨���ڵ�����ʾ��ǰ�ڵ㣬
		//2��������һ���ڵ�����ʾ��һ���ڵ����һ���ڵ�
		//3������һ���ڵ��nextָ��ǰ�ڵ�
		//4������״̬��ֱ����β
		//����һ���µ�ͷ�ڵ�
		HeroNode newHead = new HeroNode();
		//�����ڵ㣬��������б���
		//1.�Ա���������ֵ
		HeroNode toolMan = head.getNext();
		HeroNode toolMan_next = toolMan.getNext();
		HeroNode toolMan_next2 = toolMan_next.getNext();
		//����󣬽���һ��Ԫ�ص�next��Ϊnull;
		toolMan.setNext(null);
		while (true)
		{
			if (toolMan_next == null)
			{
				//������˶�β�����µ�ͷ�ڵ���ӽ�ȥ���˳�
				newHead.setNext(toolMan);
				break;
			}
			//2.ʵ�ַ�ת
			toolMan_next.setNext(toolMan);
			//3.����״̬
			toolMan = toolMan_next;
			toolMan_next = toolMan_next2;
			if (toolMan_next2 != null)
			{
				toolMan_next2 = toolMan_next2.getNext();
			}
		}
		return new SingleLinkedList(newHead);
	}
	public static SingleLinkedList inverseDemo(HeroNode head)
	{
		//�������Ϊ��
		if (head.getNext() == null)
		{
			return new SingleLinkedList();
		}
		//�������Ϊ��
		//1��������һ��ͷ�ڵ�����������������
		//2��ʹ�õ�ǰͷ�ڵ���������ǰ������
		//3����ԭ������������ȡ���ڵ������뵽1��������
		//4������״̬��ֱ����β
		//������һ��ͷ�ڵ�����������������
		HeroNode newHead = new HeroNode();
		//�����ڵ㣬��������б���
		HeroNode toolMan = head.getNext();
		while (true)
		{
			if (toolMan == null)
			{
				//������˶�β�����µ�ͷ�ڵ���ӽ�ȥ���˳�
				break;
			}
			//��һ��ͷ�ڵ�����������������
			head.setNext(toolMan.getNext());
			//��ԭ������������ȡ���ڵ������뵽1��������
			toolMan.setNext(newHead.getNext());
			newHead.setNext(toolMan);
			
			//3.����״̬
			toolMan = head.getNext();
		}
		head.setNext(newHead.getNext());//�����Ὣԭ�����������򣬸���Դ����
		return new SingleLinkedList(head);
	}

	/**
	 *��Ѷ�������⣬����������������ӡ����(����ջ)
	 *@param head �����ͷ�ڵ�
	 */
	public static void inversePrint(HeroNode head)
	{
		//�������Ϊ��
		if (head.getNext() == null)
		{
			System.out.println("������Ϊ��~~");
			return;
		}
		//���帨���ڵ��������������ȫ��ѹ��ջ��
		HeroNode toolMan = head.getNext();
		//����һ������ΪHoreNode��ջ
		Stack<HeroNode> stack = new Stack<>();
		while (toolMan != null)
		{
			//������ѹ��ջ��
			stack.push(toolMan);
			toolMan = toolMan.getNext();
		}

		//���ջ�������ݣ�������ӡ����
		while (!stack.empty())
		{
			System.out.println(stack.pop());
		}
	}

	//-----------------------------------------����༭ͷ�ڵ㹦��--------------------------------------------
	public HeroNode getHead()
	{
		return this.head;
	}

	//-----------------------------------------�����������ݹ���--------------------------------------------
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
	
	//-----------------------------------------����ɾ������--------------------------------------------
	//�������ɾ������
	public void delete(int no)
	{
		//�������Ϊ��
		if (head.getNext() == null)
		{
			System.out.println("����Ϊ��~~~~~");
		}
		//����һ�������ڵ�
		var toolMan = head;
		//��ʶ�����Ƿ��иýڵ�
		boolean ifExist = false;
		//��������
		while (true)
		{
			if (toolMan.getNext() == null) //���˶�β
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//���next�ڵ����Ҫɾ���Ľڵ�
				ifExist = true;//�����д��ڸýڵ�
				break;
			}
			toolMan = toolMan.getNext();
		}

		//�ж��Ƿ����ɾ��
		if (ifExist)
		{
			//����������и�Ԫ��
			toolMan.setNext(toolMan.getNext().getNext());
			return;
		}
		//������������޷�ɾ��
		else {
			System.out.printf("�����в��������Ϊ %d �Ľڵ㣬�޷�ɾ������\n", no);
			return;
		}
	}
	//-----------------------------------------�����޸Ĺ���--------------------------------------------
	//�������ݵ��޸Ĺ���--------------ֻ�޸�����
	public void editName(int no, String name)
	{
		//�������Ϊ��
		if (head.getNext() == null)
		{
			System.out.println("����Ϊ��~~~~~");
			return;
		}
		//����һ�������ڵ�
		var toolMan = head;
		//��ʶ�����Ƿ��иýڵ�
		boolean ifExist = false;
		//��������
		while (true)
		{
			if (toolMan.getNext() == null) //���˶�β
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//�����ǰ�ڵ����Ҫ�޸ĵĽڵ�
				ifExist = true;//�����д��ڸýڵ�
				toolMan = toolMan.getNext();
				break;
			}
			toolMan = toolMan.getNext();
		}

		//�ж��Ƿ�����޸�
		if (ifExist)
		{
			//����������и�Ԫ��
			toolMan.setName(name);
			return;
		}
		//������������޷��޸�
		else {
			System.out.printf("�����в��������Ϊ %d �Ľڵ㣬�޷��޸���Ϣ����\n", no);
			return;
		}
	}

	//�������ݵ��޸Ĺ���--------------ֻ�޸Ĵº�
	public void editNickName(int no, String nickName)
	{
		//�������Ϊ��
		if (head.getNext() == null)
		{
			System.out.println("����Ϊ��~~~~~");
			return;
		}
		//����һ�������ڵ�
		var toolMan = head;
		//��ʶ�����Ƿ��иýڵ�
		boolean ifExist = false;
		//��������
		while (true)
		{
			if (toolMan.getNext() == null) //���˶�β
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//�����ǰ�ڵ����Ҫ�޸ĵĽڵ�
				ifExist = true;//�����д��ڸýڵ�
				toolMan = toolMan.getNext();
				break;
			}
			toolMan = toolMan.getNext();
		}

		//�ж��Ƿ�����޸�
		if (ifExist)
		{
			//����������и�Ԫ��
			toolMan.setNickName(nickName);
			return;
		}
		//������������޷��޸�
		else {
			System.out.printf("�����в��������Ϊ %d �Ľڵ㣬�޷��޸���Ϣ����\n", no);
			return;
		}
	}
	//�������ݵ��޸Ĺ���
	public void edit(int no, String name, String nickName)
	{
		//�������Ϊ��
		if (head.getNext() == null)
		{
			System.out.println("����Ϊ��~~~~~");
			return;
		}
		//����һ�������ڵ�
		var toolMan = head;
		//��ʶ�����Ƿ��иýڵ�
		boolean ifExist = false;
		//��������
		while (true)
		{
			if (toolMan.getNext() == null) //���˶�β
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//�����ǰ�ڵ����Ҫ�޸ĵĽڵ�
				ifExist = true;//�����д��ڸýڵ�
				toolMan = toolMan.getNext();
				break;
			}
			toolMan = toolMan.getNext();
		}

		//�ж��Ƿ�����޸�
		if (ifExist)
		{
			//����������и�Ԫ��
			toolMan.setName(name);
			toolMan.setNickName(nickName);
			return;
		}
		//������������޷��޸�
		else {
			System.out.printf("�����в��������Ϊ %d �Ľڵ㣬�޷��޸���Ϣ����\n", no);
			return;
		}
	}

	//-----------------------------------------������ҹ���--------------------------------------------
	//�������ݵĲ��ҹ���
	public void search(int no)
	{
		//�������Ϊ��
		if (head.getNext() == null)
		{
			System.out.println("����Ϊ��~~~~~");
			return;
		}
		//����һ�������ڵ�
		var toolMan = head;
		//��ʶ�����Ƿ��иýڵ�
		boolean ifExist = false;
		//��������
		while (true)
		{
			if (toolMan.getNext() == null) //���˶�β
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//�����ǰ�ڵ����Ҫ���ҵĽڵ�
				ifExist = true;//�����д��ڸýڵ�
				toolMan = toolMan.getNext();
				break;
			}
			toolMan = toolMan.getNext();
		}

		//�ж��Ƿ�����޸�
		if (ifExist)
		{
			//����������и�Ԫ��
			System.out.printf("���Ϊ%d�Ľڵ����ϢΪ" + toolMan + "\n", no);
			return;
		}
		//������������޷��޸�
		else {
			System.out.printf("�����в��������Ϊ %d �Ľڵ㣡��\n", no);
			return;
		}
	}

	//-----------------------------------------����չʾ����--------------------------------------------
	//չʾ����
	public void showLinkedList()
	{
		if (head.getNext() == null)
		{
			System.out.println("����Ϊ��~~~~~");
			return;
		}
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
	public HeroNode()
	{
	}

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

	//����name
	public void setName(String name)
	{
		this.name = name;
	}

	//��ѯname
	public String getName()
	{
		return name;
	}

	//����nickName
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	//��ѯnickName
	public String getNickName()
	{
		return nickName;
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