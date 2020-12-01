public class SinaReverseK 
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
		
		//�õ�����ĵ�����k��Ԫ��
		int k = 4;
		System.out.printf("��������%d��Ԫ���ǣ�\n", k);
		System.out.println(SingleLinkedList.getReverseK(sll.getHead(), k));
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
	 *���ˣ�������������k��ͷ�ڵ�
	 *@param head �����ͷ�ڵ�
	 *@return ������������k��ͷ�ڵ�
	 */
	public static HeroNode getReverseK(HeroNode head, int k)
	{
		if (head.getNext() == null)
		{
			System.out.println("����Ϊ��");
			return null;
		}
		//��������
		HeroNode toolMan = head;

		int sum = SingleLinkedList.getNumber(head);
		/*int sum = 0;//ͳ��������ЧԪ�ظ���
		while (true)
		{
			if (toolMan.getNext() == null)
			{
				break;//��������
			}
			toolMan = toolMan.getNext();
			sum++;
		}*/
		///----------------------�ж�k�Ƿ���Ϲ���
		if (k <= 0 || k > sum)
		{
			return null;
		}
		toolMan = head.getNext();
		for (var i = 0; i < sum - k; i++)
		{
			toolMan = toolMan.getNext();
		}
		/*
		toolMan = head;
		for (var i = 0; i < sum - k + 1; i++)
		{
			toolMan = toolMan.getNext();
		}*/
		return toolMan;
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