public class LinkedListNum 
{
	public static void main(String[] args) 
	{
		//新建几个英雄节点 添加到链表
		HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
		HeroNode heroNode4 = new HeroNode(4, "公孙胜", "入云龙");
		HeroNode heroNode5 = new HeroNode(5, "李逵", "黑旋风");

		//定义一个链表
		SingleLinkedList sll = new SingleLinkedList();
		sll.addByOrder(heroNode1);
		sll.addByOrder(heroNode4);
		sll.addByOrder(heroNode2);
		sll.addByOrder(heroNode3);
		sll.addByOrder(heroNode5);

		//展示链表
		sll.showLinkedList();

		System.out.println("当前链表的数据有效个数为：" + getNumber(sll.getHead()));

		sll.delete(2);
		sll.showLinkedList();
		System.out.println("当前链表的数据有效个数为：" + getNumber(sll.getHead()));
		sll.showLinkedList();
		sll.delete(4);
		System.out.println("当前链表的数据有效个数为：" + getNumber(sll.getHead()));
	}

	/**
	 *@param head 链表的头节点
	 *@return 链表的有效节点个数
	 */
	public static int getNumber(HeroNode head)
	{
		//如果链表为空
		if (head.getNext() == null)
		{
			return 0;
		}
		//如果链表不为空，遍历链表
		HeroNode toolMan = head;
		//统计有效节点个数
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
}


//创建一个管理链表的类
class SingleLinkedList
{
	//包含一个不存放数据的头节点
	private HeroNode head = new HeroNode(0, null, null);

	//获取头节点
	public HeroNode getHead()
	{
		return this.head;
	}
	
	//-----------------------------------------链表增加数据功能--------------------------------------------
	//给链表添加数据的方法
	public void addElement(HeroNode data)
	{
		//如果当前链表为空，则直接添加
		if (head.getNext() == null)
		{
			head.setNext(data);
			return;
		}
		//如果当前链表不为空，
		//1、则遍历链表找到尾部，
		//2、往链表尾部添加数据

		//定义一个辅助节点 来遍历链表
		HeroNode toolMan = head;
		while (true)
		{
			toolMan = toolMan.getNext();
			if (toolMan.getNext() == null)
			{
				//如果到了链表尾部
				toolMan.setNext(data);
				return;
			}
		}
	}

	// 按照顺序添加数据
	public void addByOrder(HeroNode data)
	{
		//遍历辅助节点，来实现链表遍历，找到要添加的位置
		HeroNode toolMan = head;
		//判断是否重复
		boolean ifExist = false;
		
		//遍历链表，找到要插入的位置
		while (true)
		{
			if (toolMan.getNext() == null)//说明已经到达了链表尾部
			{
				break;
			}
			else if (toolMan.getNo() == data.getNo())
			{
				//链表已经存在该数据 
				ifExist = true;
				break;
			}
			else if (toolMan.getNext().getNo() > data.getNo())
			{
				//toolMan和next中间就是data需要插入的位置
				break;
			}
			//对遍历节点赋值，否则会死循环
			toolMan = toolMan.getNext();
		}
		//如果已经存在数据
		if (ifExist)
		{
			System.out.printf("序号为 %d 的英雄已经存在，无法添加！！\n", data.getNo());
			return;
		}
		//可以添加的话，将数据添加到遍历节点和next中间
		data.setNext(toolMan.getNext());//将要插入的节点的next设置成遍历节点的next
		toolMan.setNext(data);//将遍历节点的next设置成要插入的节点
	}
	
	//-----------------------------------------链表删除功能--------------------------------------------
	//对链表的删除功能
	public void delete(int no)
	{
		//如果链表为空
		if (head.getNext() == null)
		{
			System.out.println("链表为空~~~~~");
		}
		//定义一个遍历节点
		var toolMan = head;
		//标识链表是否有该节点
		boolean ifExist = false;
		//遍历链表
		while (true)
		{
			if (toolMan.getNext() == null) //到了队尾
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//如果next节点就是要删除的节点
				ifExist = true;//链表中存在该节点
				break;
			}
			toolMan = toolMan.getNext();
		}

		//判断是否可以删除
		if (ifExist)
		{
			//如果链表中有该元素
			toolMan.setNext(toolMan.getNext().getNext());
			return;
		}
		//如果不存在则无法删除
		else {
			System.out.printf("链表中不存在序号为 %d 的节点，无法删除！！\n", no);
			return;
		}
	}
	//-----------------------------------------链表修改功能--------------------------------------------
	//链表数据的修改功能--------------只修改名字
	public void editName(int no, String name)
	{
		//如果链表为空
		if (head.getNext() == null)
		{
			System.out.println("链表为空~~~~~");
			return;
		}
		//定义一个遍历节点
		var toolMan = head;
		//标识链表是否有该节点
		boolean ifExist = false;
		//遍历链表
		while (true)
		{
			if (toolMan.getNext() == null) //到了队尾
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//如果当前节点就是要修改的节点
				ifExist = true;//链表中存在该节点
				toolMan = toolMan.getNext();
				break;
			}
			toolMan = toolMan.getNext();
		}

		//判断是否可以修改
		if (ifExist)
		{
			//如果链表中有该元素
			toolMan.setName(name);
			return;
		}
		//如果不存在则无法修改
		else {
			System.out.printf("链表中不存在序号为 %d 的节点，无法修改信息！！\n", no);
			return;
		}
	}

	//链表数据的修改功能--------------只修改绰号
	public void editNickName(int no, String nickName)
	{
		//如果链表为空
		if (head.getNext() == null)
		{
			System.out.println("链表为空~~~~~");
			return;
		}
		//定义一个遍历节点
		var toolMan = head;
		//标识链表是否有该节点
		boolean ifExist = false;
		//遍历链表
		while (true)
		{
			if (toolMan.getNext() == null) //到了队尾
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//如果当前节点就是要修改的节点
				ifExist = true;//链表中存在该节点
				toolMan = toolMan.getNext();
				break;
			}
			toolMan = toolMan.getNext();
		}

		//判断是否可以修改
		if (ifExist)
		{
			//如果链表中有该元素
			toolMan.setNickName(nickName);
			return;
		}
		//如果不存在则无法修改
		else {
			System.out.printf("链表中不存在序号为 %d 的节点，无法修改信息！！\n", no);
			return;
		}
	}
	//链表数据的修改功能
	public void edit(int no, String name, String nickName)
	{
		//如果链表为空
		if (head.getNext() == null)
		{
			System.out.println("链表为空~~~~~");
			return;
		}
		//定义一个遍历节点
		var toolMan = head;
		//标识链表是否有该节点
		boolean ifExist = false;
		//遍历链表
		while (true)
		{
			if (toolMan.getNext() == null) //到了队尾
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//如果当前节点就是要修改的节点
				ifExist = true;//链表中存在该节点
				toolMan = toolMan.getNext();
				break;
			}
			toolMan = toolMan.getNext();
		}

		//判断是否可以修改
		if (ifExist)
		{
			//如果链表中有该元素
			toolMan.setName(name);
			toolMan.setNickName(nickName);
			return;
		}
		//如果不存在则无法修改
		else {
			System.out.printf("链表中不存在序号为 %d 的节点，无法修改信息！！\n", no);
			return;
		}
	}

	//-----------------------------------------链表查找功能--------------------------------------------
	//链表数据的查找功能
	public void search(int no)
	{
		//如果链表为空
		if (head.getNext() == null)
		{
			System.out.println("链表为空~~~~~");
			return;
		}
		//定义一个遍历节点
		var toolMan = head;
		//标识链表是否有该节点
		boolean ifExist = false;
		//遍历链表
		while (true)
		{
			if (toolMan.getNext() == null) //到了队尾
			{
				break;
			}
			else if (toolMan.getNext().getNo() == no)
			{
				//如果当前节点就是要查找的节点
				ifExist = true;//链表中存在该节点
				toolMan = toolMan.getNext();
				break;
			}
			toolMan = toolMan.getNext();
		}

		//判断是否可以修改
		if (ifExist)
		{
			//如果链表中有该元素
			System.out.printf("序号为%d的节点的信息为" + toolMan + "\n", no);
			return;
		}
		//如果不存在则无法修改
		else {
			System.out.printf("链表中不存在序号为 %d 的节点！！\n", no);
			return;
		}
	}

	//-----------------------------------------链表展示功能--------------------------------------------
	//展示链表
	public void showLinkedList()
	{
		if (head.getNext() == null)
		{
			System.out.println("链表为空~~~~~");
			return;
		}
		//继续用辅助节点遍历
		HeroNode toolMan = head;
		while (true)
		{
			toolMan = toolMan.getNext();
			System.out.println(toolMan);
			if (toolMan.getNext() == null)
			{
				//如果到了链表尾部
				return;
			}
		}
	}
}

//创建一个链表的元素类
class HeroNode
{
	//数据包含了排名，名字，绰号，下一个英雄的地址
	private int no;
	private String name;
	private String nickName;
	private HeroNode next;

	//构造器
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

	//设置name
	public void setName(String name)
	{
		this.name = name;
	}

	//查询name
	public String getName()
	{
		return name;
	}

	//设置nickName
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	//查询nickName
	public String getNickName()
	{
		return nickName;
	}

	//设置next
	public void setNext(HeroNode next)
	{
		this.next = next;
	}

	//查询next
	public HeroNode getNext()
	{
		return next;
	}

	//重写toString方法来输出结果
	public String toString()
	{
		return "HeroNode[no = " + no + ",name = " + name + ",nickName = " + nickName + "]";
	}
} 