import java.util.*;

public class TecentReverse 
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

		System.out.println("\n逆序打印:");
		
		//逆序打印数据
		SingleLinkedList.inversePrint(sll.getHead());
	}
}


//创建一个管理链表的类
class SingleLinkedList
{
	//包含一个不存放数据的头节点
	private HeroNode head = new HeroNode();

	//----------------构造器---------------------
	public SingleLinkedList(){}

	public SingleLinkedList(HeroNode head)
	{
		this.head = head;
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

	/**
	 *腾讯：对链表进行反转
	 *@param head 链表的头节点
	 *@return 倒序后的头节点
	 */
	public static SingleLinkedList inverse(HeroNode head)
	{
		//如果链表为空
		if (head.getNext() == null)
		{
			return new SingleLinkedList();
		}
		//如果链表不为空
		//1、定义辅助节点来表示当前节点，
		//2、定义另一个节点来表示下一个节点的下一个节点
		//3、让下一个节点的next指向当前节点
		//4、更新状态，直到队尾
		//定义一个新的头节点
		HeroNode newHead = new HeroNode();
		//辅助节点，对链表进行遍历
		//1.对遍历变量赋值
		HeroNode toolMan = head.getNext();
		HeroNode toolMan_next = toolMan.getNext();
		HeroNode toolMan_next2 = toolMan_next.getNext();
		//保存后，将第一个元素的next设为null;
		toolMan.setNext(null);
		while (true)
		{
			if (toolMan_next == null)
			{
				//如果到了队尾，将新的头节点添加进去，退出
				newHead.setNext(toolMan);
				break;
			}
			//2.实现反转
			toolMan_next.setNext(toolMan);
			//3.更新状态
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
		//如果链表为空
		if (head.getNext() == null)
		{
			return new SingleLinkedList();
		}
		//如果链表不为空
		//1、定义另一个头节点来保留逆序后的链表
		//2、使用当前头节点来保留以前的链表
		//3、从原来的链表依次取出节点来插入到1的链表中
		//4、更新状态，直到队尾
		//定义另一个头节点来保留逆序后的链表
		HeroNode newHead = new HeroNode();
		//辅助节点，对链表进行遍历
		HeroNode toolMan = head.getNext();
		while (true)
		{
			if (toolMan == null)
			{
				//如果到了队尾，将新的头节点添加进去，退出
				break;
			}
			//另一个头节点来保留逆序后的链表
			head.setNext(toolMan.getNext());
			//从原来的链表依次取出节点来插入到1的链表中
			toolMan.setNext(newHead.getNext());
			newHead.setNext(toolMan);
			
			//3.更新状态
			toolMan = head.getNext();
		}
		head.setNext(newHead.getNext());//这样会将原来的链表逆序，更改源数据
		return new SingleLinkedList(head);
	}

	/**
	 *腾讯的面试题，将链表的数据逆序打印出来(利用栈)
	 *@param head 链表的头节点
	 */
	public static void inversePrint(HeroNode head)
	{
		//如果链表为空
		if (head.getNext() == null)
		{
			System.out.println("该链表为空~~");
			return;
		}
		//定义辅助节点遍历链表，将数据全部压入栈中
		HeroNode toolMan = head.getNext();
		//定义一个泛型为HoreNode的栈
		Stack<HeroNode> stack = new Stack<>();
		while (toolMan != null)
		{
			//将数据压入栈中
			stack.push(toolMan);
			toolMan = toolMan.getNext();
		}

		//如果栈中有数据，遍历打印数据
		while (!stack.empty())
		{
			System.out.println(stack.pop());
		}
	}

	//-----------------------------------------链表编辑头节点功能--------------------------------------------
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
	public HeroNode()
	{
	}

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