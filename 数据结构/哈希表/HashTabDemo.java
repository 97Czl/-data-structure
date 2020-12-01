import java.util.Scanner;

public class HashTabDemo
{
	//—————————————————————————————————————————对哈希表的测试函数———————————————————————————————————————————————
	public static void main(String[] args) 
	{
		//新建一个哈希表
		HashTab hashTab = new HashTab(5);
		//命令行菜单
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入命令进行测试哈希表：");
		String str ; //接收命令行参数
		int num = 0; //接收命令行参数

		while (true)
		{
			System.out.println("add  : 添加元素");
			System.out.println("find : 查找元素");
			System.out.println("list : 展示元素");
			System.out.println("del  : 删除元素");
			
			str = sc.next();

			switch (str)
			{
				case "add":
					System.out.println("请输入id：");
					num = sc.nextInt();
					System.out.println("请输入name：");
					str = sc.next();
					hashTab.addEmp(new Emp(num, str));
					break;
				case "find":
					System.out.println("请输入要查找的元素的id：");
					num = sc.nextInt();
					hashTab.findEmp(num);
					break;
				case "list":
					hashTab.listEmp();
					break;
				case "del":
					System.out.println("请输入要删除的元素的id：");
					num = sc.nextInt();
					hashTab.deleteEmp(num);
					break;
				case "exit":
					sc.close();
					System.exit(1);
				default : 
					System.out.println("输入命令有误");
					break;
			}
		}
	}
}

class HashTab 
{
	//哈希表只保存一个数组，
	private EmpLinkedList[] empLinkedList;

	//构造器指定数组大小
	public HashTab(int size)
	{
		empLinkedList = new EmpLinkedList[size];
		//*****************************************
		//对每一个元素进行初始化，否则未添加元素之前访问会空指针异常
		for (int i = 0; i < size; i++)
		{
			empLinkedList[i] = new EmpLinkedList();
		}
	}

	//—————————————————————————————————————————对哈希表的增删查列操作———————————————————————————————————————————————
	//添加节点
	public void addEmp(Emp emp)
	{
		int no = getNo(emp.getId());
		empLinkedList[no].add(emp);
	}

	//列出所有元素
	public void listEmp()
	{
		//遍历所有链表
		for (int i = 0; i < empLinkedList.length; i++)
		{
			empLinkedList[i].list(i);
		}
	}

	//查找元素
	public int findEmp(int id)
	{
		//遍历所有链表挨个查找
		for (int i = 0; i < empLinkedList.length; i++)
		{
			if (empLinkedList[i].find(id) != null)
			{
				System.out.println("在第" + i + "个链表中找到该元素~");
				return i;
			}
		}
		System.out.println("在所有链表中没有找到该元素~");
		return -1;
	}

	//删除元素
	public boolean deleteEmp(int id)
	{
		int no = findEmp(id);
		//遍历所有链表挨个查找
		if (no == -1)
		{
			System.out.println("哈希表中不存在该元素，删除失败！！");
			return false;
		}
		else 
		{
			//删除操作
			empLinkedList[no].delete(id);
			System.out.println("已删除该元素~");
			return true;
		}
	}

	//—————————————————————————————————————————对哈希表的元素的序号确定———————————————————————————————————————————————
	private int getNo(int no)
	{
		return (no - 1) % this.empLinkedList.length;
	}
}

//哈希表每个数组元素里面存放的链表
class EmpLinkedList
{
	//头节点
	private Emp head = new Emp();
	
	//添加节点
	public boolean add(Emp emp)
	{
		if (find(emp.getId()) == null)
		{
			//辅助遍历的节点
			Emp temp = head;
			//遍历找到队尾
			while(true)
			{
				//如果已经到了队尾
				if (temp.getNext() == null)
				{
					break;
				}
				temp = temp.getNext();
			}
			//在尾部添加元素
			temp.setNext(emp);
			return true;
		}
		else
		{
			System.out.println("序号为" + emp.getId() + "的元素已经存在，添加失败");
			return false;
		}
	}

	//列出所有元素
	public void list(int no)
	{
		System.out.print("第" + no + "个链表的元素 => ");
		//辅助遍历的节点
		Emp temp = head.getNext();
		if (temp == null)
		{
			System.out.println("空");
			return;
		}
		//遍历找到队尾
		while(true)
		{
			System.out.print(temp + "\t");
			//如果已经到了队尾
			if (temp.getNext() == null)
			{
				System.out.println();
				break;
			}
			temp = temp.getNext();
		}
	}

	//查找元素
	public Emp find(int id)
	{
		//辅助遍历的节点
		Emp temp = head;
		
		//遍历一直搜索
		while(true)
		{
			//如果已经找到
			if (temp.getId() == id)
			{
				break;
			}
			//如果已经到了队尾
			if (temp.getNext() == null)
			{
				//没有找到，将临时变量置为null
				temp = null;
				break;
			}
			temp = temp.getNext();
		}
		return temp;
	}

	//删除元素
	public boolean delete(int id)
	{
		//辅助遍历的节点
		Emp temp = head;
		
		//遍历一直搜索
		while(true)
		{
			//如果已经找到
			if (temp.getNext().getId() == id)
			{
				break;
			}
			//如果已经到了队尾
			if (temp.getNext() == null)
			{
				//没有找到，返回false
				return false;
			}
			temp = temp.getNext();
		}

		//删除节点
		temp.setNext(temp.getNext().getNext());
		return true; 
	}
}

//哈希表最底部存放的元素
class Emp
{
	private int id;
	private String name;
	private Emp next;

	//头节点专用构造器
	public Emp()
	{
	}
	//普通构造器
	public Emp(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	//各个元素的set和get方法
	public void setId(int id) 
	{
		this.id = id;
	}
	public int getId()
	{
		return this.id;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	public void setNext(Emp next) 
	{
		this.next = next;
	}
	public Emp getNext()
	{
		return this.next;
	}

	//重写toString方法
	public String toString()
	{
		return "id = " + this.getId() + ",name = " + this.getName();
	}
}