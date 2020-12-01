public class AllFunction 
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
		DuLinkedList sll = new DuLinkedList();
		sll.addByOrder(heroNode1);
		sll.addByOrder(heroNode4);
		sll.addByOrder(heroNode2);
		sll.addByOrder(heroNode3);
		sll.addByOrder(heroNode5);

		sll.showLinkedList();
		sll.delete(2);sll.delete(5);


		//展示链表
		sll.showLinkedList();
	}
}

class DuLinkedList
{
	//双向链表包含一个头节点
	private HeroNode head = new HeroNode();

	//--------------------------------------------------获取链表头节点-------------------------------------------------------
	public HeroNode getHead()
	{
		return this.head;
	}

	//--------------------------------------------------给链表添加数据-------------------------------------------------------
	//默认添加到链表最后面
	public void addDefault(HeroNode hero)
	{
		//定义辅助遍历节点
		HeroNode toolMan = this.head;
		//判断该节点是否在链表中已经存在
		boolean ifExist = false;
		//遍历
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
		//即toolMan就是最后的节点
		//添加数据
		if (ifExist)
		{
			//如果要添加的数据已经在链表中
			System.out.println("该节点已经存在，添加失败！！");
			return;
		}
		hero.setPre(toolMan);
		hero.setNext(toolMan.getNext());
		toolMan.setNext(hero);
	}

	//按照顺序添加元素
	public void addByOrder(HeroNode hero)
	{
		//定义辅助节点来遍历
		HeroNode toolMan = this.head;
		//判断当前链表是否已经存在该节点
		boolean ifExist = false;
		//遍历
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
		//即toolMan就是插入位置前一个的节点
		//添加数据
		if (ifExist)
		{
			//如果要添加的数据已经在链表中
			System.out.println("该节点已经存在，添加失败！！");
			return;
		}
		hero.setPre(toolMan);
		hero.setNext(toolMan.getNext());
		toolMan.setNext(hero);
	}

	//--------------------------------------------------编辑链表-------------------------------------------------------
	public void edit(int no, String name, String nickName)
	{
		//定义辅助节点来遍历
		HeroNode toolMan = this.head;
		//判断当前链表是否存在该节点
		boolean ifExist = false;
		//遍历
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
		//即toolMan就是要修改的节点
		if (ifExist)
		{
			toolMan.setName(name);
			toolMan.setNickName(nickName);
			System.out.println("修改成功~~");
			return;
		}
		else 
		{
			System.out.printf("链表中没有序号为%d的英雄,修改失败~\n", no);
			return;
		}
	}

	//--------------------------------------------------删除链表-------------------------------------------------------
	public void delete(int no)
	{
		//定义辅助节点来遍历
		HeroNode toolMan = this.head;
		//判断当前链表是否存在该节点
		boolean ifExist = false;
		//遍历
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
		//即toolMan就是要删除的节点
		if (ifExist)
		{
			toolMan.getPre().setNext(toolMan.getNext());
			//如果要删除的是最后一个节点，下面语句会引发空指针异常
			if (toolMan.getNext() != null)
			{
				toolMan.getNext().setPre(toolMan.getPre());
			}
			System.out.println("删除成功~~");
			return;
		}
		else 
		{
			System.out.printf("链表中没有序号为%d的英雄,删除失败~\n", no);
			return;
		}
	}

	//--------------------------------------------------显示链表-------------------------------------------------------
	//显示链表
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

//创建一个链表的元素类
class HeroNode
{
	//数据包含了排名，名字，绰号，下一个英雄的地址，前一个英雄的地址
	private int no;
	private String name;
	private String nickName;
	private HeroNode next;
	private HeroNode pre;
	
	//无参构造器
	public HeroNode(){}

	//有参构造器
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
		return this.name;
	}

	//设置nickName
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	//查询nickName
	public String getNickName()
	{
		return this.nickName;
	}

	//设置next
	public void setNext(HeroNode next)
	{
		this.next = next;
	}

	//查询next
	public HeroNode getNext()
	{
		return this.next;
	}

	//设置pre
	public void setPre(HeroNode pre)
	{
		this.pre = pre;
	}

	//查询pre
	public HeroNode getPre()
	{
		return this.pre;
	}

	//重写toString方法来输出结果
	public String toString()
	{
		return "HeroNode[no = " + no + ",name = " + name + ",nickName = " + nickName + "]";
	}
} 