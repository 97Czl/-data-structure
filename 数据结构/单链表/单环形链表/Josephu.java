public class Josephu 
{
	public static void main(String[] args) 
	{
		CircleSingleLinkedList csll = new CircleSingleLinkedList();
		csll.countBoy(3, 4, 6);
		csll.showList();

	}
}


//创建一个管理链表的类
class CircleSingleLinkedList
{
	private Boy first = null;

	//给链表添加数据
	public void add(int num)
	{
		if (num < 1)
		{
			System.out.println("链表元素的个数不符合规范~~");
			return;
		}
		//给链表添加元素
		//创建一个辅助变量来实现遍历
		Boy current = first;
		for (var i = 1; i <= num; i++)
		{
			Boy boy = new Boy(i);
			if (i == 1)//如果是第一个元素，让first指向它 形成一个闭环
			{
				first = boy;
				first.setNext(first);
				current = first;
				continue;
			}
			//如果是正常加入，让current表示需要加入的前一个节点
			current.setNext(boy);
			boy.setNext(first);
			current = current.getNext();
		}
	}

	/**
	 *@param startNo 最开始计数的节点
	 *@param countNum 每次计数的个数
	 *@param nums 孩子总数
	 */
	public void countBoy(int startNo, int countNum, int nums)
	{
		//先创建包含nums个孩子的环形链表
		if (nums < startNo || startNo < 1 || countNum < 1)
		{
			System.out.println("参数不符合规范");
			return;
		}
		add(nums);
		//定义辅助节点，记录first后的节点，来实现出队
		Boy helper = first;
		//遍历到最后一个,指向helper
		while (true)
		{
			if (helper.getNext() == first)
			{
				break;
			}
			helper = helper.getNext();
		}
		//从第startNo个孩子开始计数，helper和first需要往后移动startNo个位置
		for (var i = 0; i < startNo - 1; i++)
		{
			helper = helper.getNext();
			first = first.getNext();
		}

		//开始出队
		while (true)
		{
			if (first == helper)//如果链表内就剩一个元素，即first == helper则直接退出循环。
			{
				break;
			}
			//first和helper都往后移动countNum个位置，然后first指向下一个。删除掉first和helper中间的元素
			for (var j = 0; j < countNum - 1; j++)
			{
				helper = helper.getNext();
				first = first.getNext();
			}
			System.out.println(first + "已出局");
			first = first.getNext();
			helper.setNext(first);
		}
		//最后仅剩的一个元素出局
		System.out.println(first + "已出局");
		first = null;
		return;
	}

	//展示所有元素
	public void showList()
	{
		//如果链表为空
		if (first == null)
		{
			System.out.println("链表为空~");
			return;
		}

		//定义辅助节点
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

//创建一个链表的元素类
class Boy
{
	//数据包含了排名，下一个小孩的地址
	private int no;
	private Boy next;

	//构造器
	public Boy(int no)
	{
		this.no = no;
	}

	public int getNo()
	{
		return this.no;
	}

	//设置next
	public void setNext(Boy next)
	{
		this.next = next;
	}

	//查询next
	public Boy getNext()
	{
		return next;
	}

	//重写toString方法来输出结果
	public String toString()
	{
		return "Boy[no = " + no + "]";
	}
} 