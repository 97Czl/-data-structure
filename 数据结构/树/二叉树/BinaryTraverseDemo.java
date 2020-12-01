public class BinaryTraverseDemo 
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


		//����ǰ����������Ӧ����  1��2��3��5��4
		System.out.println("ǰ�������");
		tree.preOrder();

		//Ȼ��������������Ӧ����  2��1��5��3��4
		System.out.println("���������");
		tree.infixOrder();

		//��������������Ӧ����  2��5��4��3��1
		System.out.println("ǰ�������");
		tree.postOrder();
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
}