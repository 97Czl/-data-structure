import java.util.Scanner;

public class HashTabDemo
{
	//�����������������������������������������������������������������������������������Թ�ϣ��Ĳ��Ժ�������������������������������������������������������������������������������������������������
	public static void main(String[] args) 
	{
		//�½�һ����ϣ��
		HashTab hashTab = new HashTab(5);
		//�����в˵�
		Scanner sc = new Scanner(System.in);
		System.out.println("������������в��Թ�ϣ��");
		String str ; //���������в���
		int num = 0; //���������в���

		while (true)
		{
			System.out.println("add  : ���Ԫ��");
			System.out.println("find : ����Ԫ��");
			System.out.println("list : չʾԪ��");
			System.out.println("del  : ɾ��Ԫ��");
			
			str = sc.next();

			switch (str)
			{
				case "add":
					System.out.println("������id��");
					num = sc.nextInt();
					System.out.println("������name��");
					str = sc.next();
					hashTab.addEmp(new Emp(num, str));
					break;
				case "find":
					System.out.println("������Ҫ���ҵ�Ԫ�ص�id��");
					num = sc.nextInt();
					hashTab.findEmp(num);
					break;
				case "list":
					hashTab.listEmp();
					break;
				case "del":
					System.out.println("������Ҫɾ����Ԫ�ص�id��");
					num = sc.nextInt();
					hashTab.deleteEmp(num);
					break;
				case "exit":
					sc.close();
					System.exit(1);
				default : 
					System.out.println("������������");
					break;
			}
		}
	}
}

class HashTab 
{
	//��ϣ��ֻ����һ�����飬
	private EmpLinkedList[] empLinkedList;

	//������ָ�������С
	public HashTab(int size)
	{
		empLinkedList = new EmpLinkedList[size];
		//*****************************************
		//��ÿһ��Ԫ�ؽ��г�ʼ��������δ���Ԫ��֮ǰ���ʻ��ָ���쳣
		for (int i = 0; i < size; i++)
		{
			empLinkedList[i] = new EmpLinkedList();
		}
	}

	//�����������������������������������������������������������������������������������Թ�ϣ�����ɾ���в�������������������������������������������������������������������������������������������������
	//��ӽڵ�
	public void addEmp(Emp emp)
	{
		int no = getNo(emp.getId());
		empLinkedList[no].add(emp);
	}

	//�г�����Ԫ��
	public void listEmp()
	{
		//������������
		for (int i = 0; i < empLinkedList.length; i++)
		{
			empLinkedList[i].list(i);
		}
	}

	//����Ԫ��
	public int findEmp(int id)
	{
		//������������������
		for (int i = 0; i < empLinkedList.length; i++)
		{
			if (empLinkedList[i].find(id) != null)
			{
				System.out.println("�ڵ�" + i + "���������ҵ���Ԫ��~");
				return i;
			}
		}
		System.out.println("������������û���ҵ���Ԫ��~");
		return -1;
	}

	//ɾ��Ԫ��
	public boolean deleteEmp(int id)
	{
		int no = findEmp(id);
		//������������������
		if (no == -1)
		{
			System.out.println("��ϣ���в����ڸ�Ԫ�أ�ɾ��ʧ�ܣ���");
			return false;
		}
		else 
		{
			//ɾ������
			empLinkedList[no].delete(id);
			System.out.println("��ɾ����Ԫ��~");
			return true;
		}
	}

	//�����������������������������������������������������������������������������������Թ�ϣ���Ԫ�ص����ȷ������������������������������������������������������������������������������������������������
	private int getNo(int no)
	{
		return (no - 1) % this.empLinkedList.length;
	}
}

//��ϣ��ÿ������Ԫ�������ŵ�����
class EmpLinkedList
{
	//ͷ�ڵ�
	private Emp head = new Emp();
	
	//��ӽڵ�
	public boolean add(Emp emp)
	{
		if (find(emp.getId()) == null)
		{
			//���������Ľڵ�
			Emp temp = head;
			//�����ҵ���β
			while(true)
			{
				//����Ѿ����˶�β
				if (temp.getNext() == null)
				{
					break;
				}
				temp = temp.getNext();
			}
			//��β�����Ԫ��
			temp.setNext(emp);
			return true;
		}
		else
		{
			System.out.println("���Ϊ" + emp.getId() + "��Ԫ���Ѿ����ڣ����ʧ��");
			return false;
		}
	}

	//�г�����Ԫ��
	public void list(int no)
	{
		System.out.print("��" + no + "�������Ԫ�� => ");
		//���������Ľڵ�
		Emp temp = head.getNext();
		if (temp == null)
		{
			System.out.println("��");
			return;
		}
		//�����ҵ���β
		while(true)
		{
			System.out.print(temp + "\t");
			//����Ѿ����˶�β
			if (temp.getNext() == null)
			{
				System.out.println();
				break;
			}
			temp = temp.getNext();
		}
	}

	//����Ԫ��
	public Emp find(int id)
	{
		//���������Ľڵ�
		Emp temp = head;
		
		//����һֱ����
		while(true)
		{
			//����Ѿ��ҵ�
			if (temp.getId() == id)
			{
				break;
			}
			//����Ѿ����˶�β
			if (temp.getNext() == null)
			{
				//û���ҵ�������ʱ������Ϊnull
				temp = null;
				break;
			}
			temp = temp.getNext();
		}
		return temp;
	}

	//ɾ��Ԫ��
	public boolean delete(int id)
	{
		//���������Ľڵ�
		Emp temp = head;
		
		//����һֱ����
		while(true)
		{
			//����Ѿ��ҵ�
			if (temp.getNext().getId() == id)
			{
				break;
			}
			//����Ѿ����˶�β
			if (temp.getNext() == null)
			{
				//û���ҵ�������false
				return false;
			}
			temp = temp.getNext();
		}

		//ɾ���ڵ�
		temp.setNext(temp.getNext().getNext());
		return true; 
	}
}

//��ϣ����ײ���ŵ�Ԫ��
class Emp
{
	private int id;
	private String name;
	private Emp next;

	//ͷ�ڵ�ר�ù�����
	public Emp()
	{
	}
	//��ͨ������
	public Emp(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	//����Ԫ�ص�set��get����
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

	//��дtoString����
	public String toString()
	{
		return "id = " + this.getId() + ",name = " + this.getName();
	}
}