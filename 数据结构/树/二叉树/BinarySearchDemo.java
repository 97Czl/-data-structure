public class BinarySearchDemo 
{
	public static void main(String[] args) 
	{
		//�������д洢�ļ����ڵ�
		HeroNode root = new HeroNode(1, "�ν�");
		HeroNode hero2 = new HeroNode(2, "����");
		HeroNode hero3 = new HeroNode(3, "¬����");
		HeroNode hero4 = new HeroNode(4, "�ֳ�");
		HeroNode hero5 = new HeroNode(5, "��ʤ");

		//����һ��������
		BinaryTree tree = new BinaryTree(root);

		//����һ�� �ν��Ǹ��ڵ㣬������ã��ұ�¬���壬  ¬�������ҷֱ�Ϊ ��ʤ���ֳ�Ķ�����
		root.setLeft(hero2);
		root.setRight(hero3);
		hero3.setLeft(hero5);
		hero3.setRight(hero4);
		
		int searchNo = 5;
		HeroNode result = null;
		//����ǰ����ң���Ҫ�Ƚ�4��
		System.out.println("ǰ�������");
		result = tree.preOrderSearch(searchNo);
		if (result != null)
		{
			System.out.println("���ҽ����" + result);
		}
		else 
		{
			System.out.println("δ�ҵ�no = " + searchNo + "��Ӣ��");
		}

		//Ȼ�������������Ҫ�Ƚ�3��
		System.out.println("���������");
		result = tree.infixOrderSearch(searchNo);
		if (result != null)
		{
			System.out.println("���ҽ����" + result);
		}
		else 
		{
			System.out.println("δ�ҵ�no = " + searchNo + "��Ӣ��");
		}

		//�������������Ҫ�Ƚ�2��
		System.out.println("���������");
		result = tree.postOrderSearch(searchNo);
		if (result != null)
		{
			System.out.println("���ҽ����" + result);
		}
		else 
		{
			System.out.println("δ�ҵ�no = " + searchNo + "��Ӣ��");
		}
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

	public void count()
	{
		System.out.println("����Ƚ�1��~~~");
	}
}