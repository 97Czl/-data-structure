/*
Ҫ��	1)���ɾ���Ľڵ���Ҷ�ӽڵ㣬��ɾ���ýڵ�
		2)���ɾ���Ľڵ��Ƿ�Ҷ�ӽڵ�
		 2.1)����÷�Ҷ�ӽڵ�Aֻ��һ���ӽڵ�B�����ӽڵ�B����ڵ�A
		 2.2)����÷�Ҷ�ӽڵ�A�����ӽڵ�B�����ӽڵ�C���������ӽڵ�B����ڵ�A



˼·��
	1�����ȣ��жϸ����Ƿ�Ϊ�գ���Ϊ�գ�ֱ�ӷ���false�������жϸ��ڵ��Ƿ���Ҫɾ���Ľڵ�
	2��������ڵ���Ҫɾ���Ľڵ㣬�����ڵ���Ϊnull������true�������Ը��ڵ���Ϊ��ǰ�ڵ㣬��ʼ�ݹ�3-6��
	3�������ǰ�ڵ����ڵ㲻Ϊ����ΪҪɾ���Ľڵ㣬ɾ��������true��
	4�������ǰ�ڵ���ҽڵ㲻Ϊ����ΪҪɾ���Ľڵ㣬ɾ��������true��
	5�������ǰ�ڵ����ڵ㲻Ϊ�գ�������Ϊ��ǰ�ڵ�ݹ�ִ��3
	6�������ǰ�ڵ���ҽڵ㲻Ϊ�գ�������Ϊ��ǰ�ڵ�ݹ�ִ��3
*/

public class BinaryDeleteReserve 
{
	public static void main(String[] args) 
	{
		//�������д洢�ļ����ڵ�
		HeroNode root = new HeroNode(1, "�ν�");
		HeroNode hero2 = new HeroNode(2, "����");
		HeroNode hero3 = new HeroNode(3, "¬����");
		HeroNode hero4 = new HeroNode(4, "�ֳ�");
		HeroNode hero5 = new HeroNode(5, "��ʤ");
		HeroNode hero6 = new HeroNode(6, "����");
		HeroNode hero7 = new HeroNode(7, "ʷ��");

		//����һ��������
		BinaryTree tree = new BinaryTree(root);

		//����һ�� �ν��Ǹ��ڵ㣬������ã��ұ�¬���壬  ¬�������ҷֱ�Ϊ ��ʤ���ֳ�Ķ�����
		root.setLeft(hero2);
		root.setRight(hero3);
		hero3.setLeft(hero5);
		hero3.setRight(hero4);
		hero2.setLeft(hero6);
		hero5.setRight(hero7);
		
		int deleteNo = 5;
		

		//ǰ���ӡ������
		System.out.println("ɾ��ǰ��ǰ�������������");
		tree.preOrder();

		tree.deleteReserve(deleteNo);

		//ǰ���ӡ������
		System.out.println("ɾ����ǰ�������������");
		tree.preOrder();
	}
}

//������
class BinaryTree
{
	private HeroNode root;

	public BinaryTree(HeroNode root)
	{
		this.root = root;
	}
	
	//***********************************************************************************************
	//�����Ƕ������ı�������
	//***********************************************************************************************
	//����ͷ�ڵ��ǰ�����ʵ�ֶԶ�������ǰ�����
	public void preOrder()
	{
		//�����жϵ�ǰ�����Ƿ�Ϊ�գ���ͷ�ڵ��Ƿ�Ϊ��
		if (this.root == null)
		{
			System.out.println("��Ϊ�գ��޷�����");
		}
		else 
		{
			this.root.preOrder();
		}
	}
	//����ͷ�ڵ���������ʵ�ֶԶ��������������
	public void infixOrder()
	{
		//�����жϵ�ǰ�����Ƿ�Ϊ�գ���ͷ�ڵ��Ƿ�Ϊ��
		if (this.root == null)
		{
			System.out.println("��Ϊ�գ��޷�����");
		}
		else 
		{
			this.root.infixOrder();
		}
	}
	//����ͷ�ڵ�ĺ������ʵ�ֶԶ������ĺ������
	public void postOrder()
	{
		//�����жϵ�ǰ�����Ƿ�Ϊ�գ���ͷ�ڵ��Ƿ�Ϊ��
		if (this.root == null)
		{
			System.out.println("��Ϊ�գ��޷�����");
		}
		else 
		{
			this.root.postOrder();
		}
	}
	
	//***********************************************************************************************
	//�����Ƕ������Ĳ��ҷ���
	//***********************************************************************************************
	//����ͷ�ڵ��ǰ�����ʵ�ֶԶ�������ǰ�����
	public HeroNode preOrderSearch(int no)
	{
		//�����жϵ�ǰ�����Ƿ�Ϊ�գ���ͷ�ڵ��Ƿ�Ϊ��
		if (this.root == null)
		{
			System.out.println("��Ϊ�գ��޷�����");
			return null;
		}
		else 
		{
			return this.root.preOrderSearch(no);
		}
	}
	//����ͷ�ڵ���������ʵ�ֶԶ��������������
	public HeroNode infixOrderSearch(int no)
	{
		//�����жϵ�ǰ�����Ƿ�Ϊ�գ���ͷ�ڵ��Ƿ�Ϊ��
		if (this.root == null)
		{
			System.out.println("��Ϊ�գ��޷�����");
			return null;
		}
		else 
		{
			return this.root.infixOrderSearch(no);
		}
	}
	//����ͷ�ڵ�ĺ������ʵ�ֶԶ������ĺ������
	public HeroNode postOrderSearch(int no)
	{
		//�����жϵ�ǰ�����Ƿ�Ϊ�գ���ͷ�ڵ��Ƿ�Ϊ��
		if (this.root == null)
		{
			System.out.println("��Ϊ�գ��޷�����");
			return null;
		}
		else 
		{
			return this.root.postOrderSearch(no);
		}
	}

	//***********************************************************************************************
	//�����Ƕ�������ɾ������
	//***********************************************************************************************
	public boolean delete(int no)
	{
		//1�����ȣ��жϸ����Ƿ�Ϊ�գ���Ϊ�գ�ֱ�ӷ���false�������жϸ��ڵ��Ƿ���Ҫɾ���Ľڵ�
		if (root != null)
		{
			//2��������ڵ���Ҫɾ���Ľڵ㣬�����ڵ���Ϊnull������true��
			if (this.root.getNo() == no)
			{
				System.out.println("���Ϊ" + no + "�Ľڵ���ɾ����");
				this.root = null;
				return true;
			}
			else
			{
				//�����Ը��ڵ���Ϊ��ǰ�ڵ㣬��ʼ�ݹ�3-6��
				if (root.delete(no))
				{
					//���ɾ���ɹ�
					System.out.println("���Ϊ" + no + "�Ľڵ���ɾ����");
					return true;
				}
				else
				{
					//���û���ҵ��ڵ㣬��ɾ��ʧ��
					System.out.println("û���ҵ����Ϊ" + no + "�Ľڵ㣬ɾ��ʧ�ܣ�");
					return false;
				}
			}
		}
		else
		{
			//����ǿ���
			System.out.println("�������޷�ɾ��Ԫ�أ�");
			return false;
		}
	}

	//***********************************************************************************************
	//�����Ƕ��������б�����ɾ������
	//***********************************************************************************************
	public boolean deleteReserve(int no)
	{
		//1�����ȣ��жϸ����Ƿ�Ϊ�գ���Ϊ�գ�ֱ�ӷ���false�������жϸ��ڵ��Ƿ���Ҫɾ���Ľڵ�
		if (root != null)
		{
			//2��������ڵ���Ҫɾ���Ľڵ㣬�����ڵ���Ϊnull������true��
			if (this.root.getNo() == no)
			{
				System.out.println("���Ϊ" + no + "�Ľڵ���ɾ����");
				switch (root.countOfSon())
				{
					//���ӽڵ�
					case 0:
						root = null;
						break;
					//ֻ�����ӽڵ�
					case 2:
						root = root.getRight();
						break;
					//ֻ�����ӽڵ�
					default :
						//ֻ�����ӽڵ�������ڵ㶼�Ǳ�����ߣ����Է���һ��
						root = root.getLeft();
						break;
				}
				return true;
			}
			else
			{
				//�����Ը��ڵ���Ϊ��ǰ�ڵ㣬��ʼ�ݹ�3-6��
				if (root.deleteReserve(no))
				{
					//���ɾ���ɹ�
					System.out.println("���Ϊ" + no + "�Ľڵ���ɾ����");
					return true;
				}
				else
				{
					//���û���ҵ��ڵ㣬��ɾ��ʧ��
					System.out.println("û���ҵ����Ϊ" + no + "�Ľڵ㣬ɾ��ʧ�ܣ�");
					return false;
				}
			}
		}
		else
		{
			//����ǿ���
			System.out.println("�������޷�ɾ��Ԫ�أ�");
			return false;
		}
	}
}

//�������д洢�Ľڵ���
class HeroNode
{
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;

	//��������Ĭ�����ҽڵ�Ϊ��
	public HeroNode(int no, String name)
	{
		this.no = no;
		this.name = name;
	}

	//�������������úͻ�ȡ����
	public void setNo(int no)
	{
		this.no = no;
	}
	public int getNo()
	{
		return this.no;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setLeft(HeroNode left)
	{
		this.left = left;
	}
	public HeroNode getLeft()
	{
		return this.left;
	}
	public void setRight(HeroNode right)
	{
		this.right = right;
	}
	public HeroNode getRight()
	{
		return this.right;
	}
	
	@Override
	public String toString()
	{
		return "HeroNode[ no = " + no + ", name = " + name + "]";
	}
	
	//***********************************************************************************************
	//�����Ƕ������ı�������
	//***********************************************************************************************
	//ʵ�ֶ�����ǰ������Ľڵ㷽��
	public void preOrder()
	{
		//���������ǰ�ڵ�
		System.out.println(this);
		//�����ǰ�ڵ����ڵ㲻Ϊ�գ��ݹ�ʵ��ǰ�����
		if (this.left != null)
		{
			this.left.preOrder();
		}
		//�����ǰ�ڵ���ҽڵ㲻Ϊ�գ��ݹ�ʵ��ǰ�����
		if (this.right != null)
		{
			this.right.preOrder();
		}
	}

	//ʵ�ֶ�������������Ľڵ㷽��
	public void infixOrder()
	{
		//�����ǰ�ڵ����ڵ㲻Ϊ�գ��ݹ�ʵ���������
		if (this.left != null)
		{
			this.left.infixOrder();
		}
		//�����ǰ�ڵ�
		System.out.println(this);
		//�����ǰ�ڵ���ҽڵ㲻Ϊ�գ��ݹ�ʵ���������
		if (this.right != null)
		{
			this.right.infixOrder();
		}
	}

	//ʵ�ֶ�������������Ľڵ㷽��
	public void postOrder()
	{
		//�����ǰ�ڵ����ڵ㲻Ϊ�գ��ݹ�ʵ�ֺ������
		if (this.left != null)
		{
			this.left.postOrder();
		}
		//�����ǰ�ڵ���ҽڵ㲻Ϊ�գ��ݹ�ʵ�ֺ������
		if (this.right != null)
		{
			this.right.postOrder();
		}
		//�����ǰ�ڵ�
		System.out.println(this);
	}

	//***********************************************************************************************
	//�����Ƕ������Ĳ��ҷ���
	//***********************************************************************************************
	//ʵ�ֶ�����ǰ����ҵĽڵ㷽��
	public HeroNode preOrderSearch(int no)
	{
		count();
		//�����жϵ�ǰ�ڵ��Ƿ�����
		if (this.no == no)
		{
			return this;
		}
		HeroNode result = null;
		//�����ǰ�ڵ����ڵ㲻Ϊ�գ��ݹ�ʵ��ǰ�����
		if (this.left != null)
		{
			result = this.left.preOrderSearch(no);
		}
		//�ж��Ƿ��ҵ���û���򲻷��أ������ҽڵ�ݹ�
		if (result != null)
		{
			return result;
		}
		//�����ǰ�ڵ���ҽڵ㲻Ϊ�գ��ݹ�ʵ��ǰ�����
		if (this.right != null)
		{
			result = this.right.preOrderSearch(no);
		}
		//�����Ƿ��ҵ�������Ҫ����
		return result;
	}

	//ʵ�ֶ�����������ҵĽڵ㷽��
	public HeroNode infixOrderSearch(int no)
	{
		HeroNode result = null;
		//�����ǰ�ڵ����ڵ㲻Ϊ�գ��ݹ�ʵ���������
		if (this.left != null)
		{
			result = this.left.infixOrderSearch(no);
		}
		//�ж��Ƿ��ҵ���û���򲻷��أ������ж��м�ڵ�
		if (result != null)
		{
			return result;
		}
		count();
		//�жϵ�ǰ�ڵ��Ƿ�����
		if (this.no == no)
		{
			return this;
		}
		//�����ǰ�ڵ���ҽڵ㲻Ϊ�գ��ݹ�ʵ���������
		if (this.right != null)
		{
			result = this.right.infixOrderSearch(no);
		}
		//�����Ƿ��ҵ�������Ҫ����
		return result;
	}

	//ʵ�ֶ�����������ҵĽڵ㷽��
	public HeroNode postOrderSearch(int no)
	{
		HeroNode result = null;
		//�����ǰ�ڵ����ڵ㲻Ϊ�գ��ݹ�ʵ�ֺ������
		if (this.left != null)
		{
			result = this.left.postOrderSearch(no);
		}
		//�ж��Ƿ��ҵ���û���򲻷��أ������ж��ұ߽ڵ�
		if (result != null)
		{
			return result;
		}
		//�����ǰ�ڵ���ҽڵ㲻Ϊ�գ��ݹ�ʵ�ֺ������
		if (this.right != null)
		{
			result = this.right.postOrderSearch(no);
		}
		//�ж��Ƿ��ҵ���û���򲻷��أ������жϵ�ǰ�ڵ�
		if (result != null)
		{
			return result;
		}
		count();
		//�жϵ�ǰ�ڵ��Ƿ�����
		if (this.no == no)
		{
			return this;
		}
		//�����Ƿ��ҵ�������Ҫ����
		return result;
	}

	//***********************************************************************************************
	//�����Ƕ������ڵ��ɾ������
	//***********************************************************************************************
	public boolean delete(int no)
	{
		//3�������ǰ�ڵ����ڵ㲻Ϊ����ΪҪɾ���Ľڵ㣬ɾ��������true��
		if (this.left != null && this.left.getNo() == no)
		{
			this.left = null;
			return true;
		}

		//4�������ǰ�ڵ���ҽڵ㲻Ϊ����ΪҪɾ���Ľڵ㣬ɾ��������true��
		if (this.right != null && this.right.getNo() == no)
		{
			this.right = null;
			return true;
		}

		//5�������ǰ�ڵ����ڵ㲻Ϊ�գ�������Ϊ��ǰ�ڵ�ݹ�ִ��3
		if (this.left != null)
		{
			//���ɾ���ɹ�
			if (this.left.delete(no))
			{
				return true;
			}
			//���û�гɹ���Ҫ�����ұ�
		}

		//6�������ǰ�ڵ���ҽڵ㲻Ϊ�գ�������Ϊ��ǰ�ڵ�ݹ�ִ��3
		if (this.right != null)
		{
			//���ɾ���ɹ�
			if (this.right.delete(no))
			{
				return true;
			}
			//û�гɹ�,��������շ���
		}
		
		//������ն�û���ҵ�Ҫɾ����Ԫ�أ�����false
		return false;
	}

	//***********************************************************************************************
	//�����Ƕ������ڵ���б�����ɾ������
	//***********************************************************************************************
	public boolean deleteReserve(int no)
	{
		//3�������ǰ�ڵ����ڵ㲻Ϊ����ΪҪɾ���Ľڵ㣬ɾ��������true��
		if (this.left != null && this.left.getNo() == no)
		{
			switch (this.left.countOfSon())
			{
				//���ӽڵ�
				case 0:
					this.left = null;
					break;
				//ֻ�����ӽڵ�
				case 2:
					this.left = this.left.getRight();
					break;
				//ֻ�����ӽڵ�
				default :
					//ֻ�����ӽڵ�������ڵ㶼�Ǳ�����ߣ����Է���һ��
					this.left = this.left.getLeft();
					break;
			}
			return true;
		}

		//4�������ǰ�ڵ���ҽڵ㲻Ϊ����ΪҪɾ���Ľڵ㣬ɾ��������true��
		if (this.right != null && this.right.getNo() == no)
		{
			switch (this.right.countOfSon())
			{
				//���ӽڵ�
				case 0:
					this.right = null;
					break;
				//ֻ�����ӽڵ�
				case 2:
					this.right = this.right.getRight();
					break;
				//ֻ�����ӽڵ�
				default :
					//ֻ�����ӽڵ�������ڵ㶼�Ǳ�����ߣ����Է���һ��
					this.right = this.right.getLeft();
					break;
			}
			return true;
		}

		//5�������ǰ�ڵ����ڵ㲻Ϊ�գ�������Ϊ��ǰ�ڵ�ݹ�ִ��3
		if (this.left != null)
		{
			//���ɾ���ɹ�
			if (this.left.deleteReserve(no))
			{
				return true;
			}
			//���û�гɹ���Ҫ�����ұ�
		}

		//6�������ǰ�ڵ���ҽڵ㲻Ϊ�գ�������Ϊ��ǰ�ڵ�ݹ�ִ��3
		if (this.right != null)
		{
			//���ɾ���ɹ�
			if (this.right.deleteReserve(no))
			{
				return true;
			}
			//û�гɹ�,��������շ���
		}
		
		//������ն�û���ҵ�Ҫɾ����Ԫ�أ�����false
		return false;
	}

	//***********************************************************************************************
	//�����Ƕ������ڵ��ж��ӽڵ�����ķ���
	/**
	 *@return �����ӽڵ���������ֻ�����ӽڵ� ����1�����ֻ�����ӽڵ㣬����2������������ڵ㣬����3�����򷵻�0
	 */
	public int countOfSon()
	{
		int result = 0;
		if (this.left != null)
		{
			//��߲�Ϊ�գ��ұ����������
			if (this.right != null)
			{
				//�ұ�Ҳ��Ϊ�գ�
				result = 3;
			}
			else 
			{
				//ֻ����ڵ�
				result = 1;
			}
		}
		else 
		{
			//���Ϊ�գ��ұ������Ϊ��
			if (this.right != null)
			{
				//�ұ߲�Ϊ�գ�
				result = 2;
			}
		}

		return result;
	}

	//***********************************************************************************************
	//�����Ƕ��������ҵļ�������
	public void count()
	{
		System.out.println("����Ƚ�1��~~~");
	}
}