/*������������   ����ԣ�
Ҫ��	1)�������Ķ����� n ���ڵ�Ķ������к��� n + 1(��ʽ 2n - (n - 1) = n + 1)����ָ����
		2)Ϊ�˽�����������������ָ��ָ��ǰ���ͺ�̽ڵ�
		3)ͬʱ��Ҫ��ʶ�������ҽڵ��ʶ��Ϊ 0 �������������� 1 ����ǰ�����ߺ�̽ڵ�
		4)��Ҫһ��pre����¼ǰһ���ڵ㣬�����޷�ʵ��ǰ���ڵ������

˼·��
		1)������������˳�������ǰ�ڵ������ָ��գ�������ǰ���ͺ�̽ڵ�
		2)�ݹ�ʵ��
		3)����ǰ������������ԣ����ڵ�Ϊ��(����Ϊ��)�ӽڵ��pre��
		������Ҫ����ע���Ѿ�������ǰ�����ߺ�̽ڵ�Ķ˿ھͲ��ܼ��������ˣ������������ѭ��

������
		1)һֱ����ֱ��ĳ���ڵ��rightΪ��̽ڵ㣬�������ʽ���

*/

public class ThreadedBinaryTreeDemo 
{
	public static void main(String[] args) 
	{
		//�������д洢�ļ����ڵ�
		HeroNode root = new HeroNode(1, "�ν�");
		HeroNode hero2 = new HeroNode(3, "����");
		HeroNode hero3 = new HeroNode(6, "¬����");
		HeroNode hero4 = new HeroNode(8, "�ֳ�");
		HeroNode hero5 = new HeroNode(10, "��ʤ");
		HeroNode hero6 = new HeroNode(14, "ʱǨ");

		//����һ��������
		ThreadedBinaryTree tree = new ThreadedBinaryTree(root);

		//����һ�� �ν��Ǹ��ڵ㣬������ã��ұ�¬���壬  ¬�������ҷֱ�Ϊ ��ʤ���ֳ�Ķ�����
		root.setLeft(hero2);
		root.setRight(hero3);
		hero2.setLeft(hero4);
		hero2.setRight(hero5);
		//hero3.setLeft(hero6);
		hero3.setRight(hero6);
		
		/*
		//ǰ����������
		tree.threadPre();
		System.out.println("�ڵ�8����ڵ�Ϊ��" + hero4.getLeft());
		System.out.println("�ڵ�8���ҽڵ�Ϊ��" + hero4.getRight());
		System.out.println("�ڵ�10����ڵ�Ϊ��" + hero5.getLeft());
		System.out.println("�ڵ�10���ҽڵ�Ϊ��" + hero5.getRight());
		System.out.println("�ڵ�14����ڵ�Ϊ��" + hero6.getLeft());
		System.out.println("�ڵ�14���ҽڵ�Ϊ��" + hero6.getRight());
		System.out.println("�ڵ�6����ڵ�Ϊ��" + hero3.getLeft());
		System.out.println("�ڵ�6���ҽڵ�Ϊ��" + hero3.getRight());
		*/

		/*
		//������������
		tree.threadInfix();
		System.out.println("�ڵ�8����ڵ�Ϊ��" + hero4.getLeft());
		System.out.println("�ڵ�8���ҽڵ�Ϊ��" + hero4.getRight());
		System.out.println("�ڵ�10����ڵ�Ϊ��" + hero5.getLeft());
		System.out.println("�ڵ�10���ҽڵ�Ϊ��" + hero5.getRight());
		System.out.println("�ڵ�14����ڵ�Ϊ��" + hero6.getLeft());
		System.out.println("�ڵ�14���ҽڵ�Ϊ��" + hero6.getRight());
		System.out.println("�ڵ�6����ڵ�Ϊ��" + hero3.getLeft());
		System.out.println("�ڵ�6���ҽڵ�Ϊ��" + hero3.getRight());
		*/

		/*
		//������������
		tree.threadPost();
		System.out.println("�ڵ�8����ڵ�Ϊ��" + hero4.getLeft());
		System.out.println("�ڵ�8���ҽڵ�Ϊ��" + hero4.getRight());
		System.out.println("�ڵ�10����ڵ�Ϊ��" + hero5.getLeft());
		System.out.println("�ڵ�10���ҽڵ�Ϊ��" + hero5.getRight());
		System.out.println("�ڵ�14����ڵ�Ϊ��" + hero6.getLeft());
		System.out.println("�ڵ�14���ҽڵ�Ϊ��" + hero6.getRight());
		System.out.println("�ڵ�6����ڵ�Ϊ��" + hero3.getLeft());
		System.out.println("�ڵ�6���ҽڵ�Ϊ��" + hero3.getRight());
		*/
		//tree.postOrder();

		//ǰ����������
		//tree.threadPre();
		//tree.threadPreTraverse();
		//������������
		tree.threadInfix();
		tree.threadInfixTraverse();
		//������������
		//tree.threadPost();
	}
}

//������������
class ThreadedBinaryTree
{
	private HeroNode root;
	//��¼ǰһ���ڵ�
	private HeroNode pre;

	public ThreadedBinaryTree(HeroNode root)
	{
		this.root = root;
	}

	//***********************************************************************************************
	//�����Ƕ������������������ķ���
	//***********************************************************************************************
	//������ǰ������������
	public boolean threadPre()
	{
		if (this.root != null)
		{
			threadPre(root);
			System.out.println("ǰ���������ɹ���");
			return true;
		}
		else 
		{
			System.out.println("ǰ��������ʧ�ܣ�");
			return false;
		}
	}
	private void threadPre(HeroNode node)
	{
		//��ȫ���ж�
		if (node == null)
		{
			return;
		}

		//ǰ��˳�� ��ǰ�ڵ� -> ��ڵ���� -> �ҽڵ����
		
		/*
		����ǰ�ڵ�
		*/
		//��ǰ�ڵ����ߣ�������ǰ���ڵ�
		if (node.getLeft() == null)
		{
			//���ӽڵ�Ϊ�գ�����Ϊǰ���ڵ�
			node.setLeft(pre);
			//˵����ڵ㲻��������������״̬
			node.setLeftStatus(false);
		}
		//��һ���ڵ���ұߣ����������һ���ĺ�̽ڵ㣬��Ϊ�޷�֪����ǰ�ڵ�ı���˳�����һ��
		if (pre != null &&  pre.getRight() == null)  //preΪ�� �����һ�ν���root����ڵ���ҽڵ㶼Ϊ��
		{
			//�ڴ���ǰ�ڵ�ʱ�������һ���ڵ���ҽڵ㻹�ǿյģ���Ҫ���ú�̽ڵ�
			pre.setRight(node);
			//˵���ҽڵ㲻��������������״̬
			pre.setRightStatus(false);
		}
		
		//����pre
		pre = node;
		
		/*
		������ڵ�
		*/
		//������ ��ڵ� �Ѿ�����ǰ���ڵ㻹�ǲ������
		if (node.getLeftStatus())
		{
			//��ڵ����
			threadPre(node.getLeft());
		}
		
		/*
		�����ҽڵ�
		*/
		//������ �ҽڵ� �Ѿ����Ӻ�̽ڵ㻹�ǲ������
		if (node.getRightStatus())
		{
			//�ҽڵ����
			threadPre(node.getRight());
		}
	}
	//����������
	public boolean threadInfix()
	{
		if (this.root != null)
		{
			threadInfix(root);
			System.out.println("�����������ɹ���");
			return true;
		}
		else 
		{
			System.out.println("����������ʧ�ܣ�");
			return false;
		}
	}
	private void threadInfix(HeroNode node)
	{
		//��ȫ���ж�
		if (node == null)
		{
			return;
		}

		//����˳�� ��ڵ���� -> ��ǰ�ڵ� -> �ҽڵ����
		
		/*
		��ڵ����
		*/
		threadInfix(node.getLeft());
		
		/*
		����ǰ�ڵ�
		*/
		//��ǰ�ڵ����ߣ�������ǰ���ڵ�
		if (node.getLeft() == null)
		{
			//���ӽڵ�Ϊ�գ�����Ϊǰ���ڵ�
			node.setLeft(pre);
			//˵����ڵ㲻��������������״̬
			node.setLeftStatus(false);
		}
		//��һ���ڵ���ұߣ����������һ���ĺ�̽ڵ㣬��Ϊ�޷�֪����ǰ�ڵ�ı���˳�����һ��
		if (pre != null &&  pre.getRight() == null)  //preΪ�� �����һ�ν���root����ڵ���ҽڵ㶼Ϊ��
		{
			//�ڴ���ǰ�ڵ�ʱ�������һ���ڵ���ҽڵ㻹�ǿյģ���Ҫ���ú�̽ڵ�
			pre.setRight(node);
			//˵���ҽڵ㲻��������������״̬
			pre.setRightStatus(false);
		}
		
		//����pre
		pre = node;

		/*
		�ҽڵ����
		*/
		threadInfix(node.getRight());
	}
	//����������
	public boolean threadPost()
	{
		if (this.root != null)
		{
			threadPost(root);
			System.out.println("�����������ɹ���");
			return true;
		}
		else 
		{
			System.out.println("����������ʧ�ܣ�");
			return false;
		}
	}
	private void threadPost(HeroNode node)
	{
		//��ȫ���ж�
		if (node == null)
		{
			return;
		}

		//����˳�� ��ڵ���� -> �ҽڵ���� -> ��ǰ�ڵ�
		
		/*
		��ڵ����
		*/
		threadPost(node.getLeft());
		/*
		�ҽڵ����
		*/
		threadPost(node.getRight());

		/*
		����ǰ�ڵ�
		*/
		//��ǰ�ڵ����ߣ�������ǰ���ڵ�
		if (node.getLeft() == null)
		{
			//���ӽڵ�Ϊ�գ�����Ϊǰ���ڵ�
			node.setLeft(pre);
			//˵����ڵ㲻��������������״̬
			node.setLeftStatus(false);
		}
		//��һ���ڵ���ұߣ����������һ���ĺ�̽ڵ㣬��Ϊ�޷�֪����ǰ�ڵ�ı���˳�����һ��
		if (pre != null &&  pre.getRight() == null)  //preΪ�� �����һ�ν���root����ڵ���ҽڵ㶼Ϊ��
		{
			//�ڴ���ǰ�ڵ�ʱ�������һ���ڵ���ҽڵ㻹�ǿյģ���Ҫ���ú�̽ڵ�
			pre.setRight(node);
			//˵���ҽڵ㲻��������������״̬
			pre.setRightStatus(false);
		}
		
		//����pre
		pre = node;
	}

	//***********************************************************************************************
	//���������������������ֱ����ķ���
	//***********************************************************************************************
	//ǰ���������������ı�������
	public boolean threadPreTraverse()
	{
		if (this.root != null)
		{
			HeroNode node = root;
			//��whileѭ����ʵ�ֱ���
			while (node != null)
			{
				//�����ǰ��������ȴӵ�ǰ�ڵ㿪ʼ�����ǰ�ڵ�
				/*
				��ӡ��ǰ�ڵ�
				*/
				System.out.println(node);
				
				/*
				������ߵĽڵ�,������߽ڵ�ֻ��Ҫ���������ӽڵ㣬�����ǰ���ڵ㲻ȥ������
				*/
				//��߽ڵ��������ǰ���ڵ�
				if (node.getLeftStatus())
				{
					node = node.getLeft();
					continue;
				}

				//�ұ߽ڵ�����Ǻ�̽ڵ㣬�����һֱ�����������Ϊ��̽ڵ�ض���ָ����һ�������ڵ��
				while (!node.getRightStatus())
				{
					node = node.getRight();
					System.out.println(node);
				}
				
				//���½ڵ�
				//�������ų� ��ڵ�Ϊ�գ�ָ����ǰ���ڵ㣩��ֻ���ҽڵ����������Կ����������� û�п��ǵ����ӽڵ��������ڵ���ô�죬�൱���߼���©��һ�飩
				//���ִ�е���һ����˵����ǰ�ڵ�����ӽڵ�ָ����ǰ���ڵ㣬���ӽڵ�Ҳ���Ǻ�̽ڵ㣬��˵�����ӽڵ��������ڵ㣬�����ӽڵ�û����
				//������һ���ڵ���ǵ�ǰ�ڵ�����ӽڵ���
				node = node.getRight();
			}
			System.out.println("ǰ�������������������ɹ���");
			return true;
		}
		else 
		{
			System.out.println("ǰ������������������ʧ�ܣ�");
			return false;
		}
	}
	//�����������������ı�������
	public boolean threadInfixTraverse()
	{
		if (this.root != null)
		{
			HeroNode node = root;
			while (node != null)
			{
				//�������������߿�ʼ�����������ҵ�����ߵ�Ԫ�أ�

				/*
				������ߵĽڵ�,�������ÿһ���ڵ� ���ȵ��ҵ�����ߵ��ӽڵ㿪ʼ����
				*/
				//һֱ�ҵ� �ڵ������ǰ���ڵ�Ľڵ� Ϊֹ
				while (node.getLeftStatus())
				{
					node = node.getLeft();
					//�����˳�ʱ����Ӧ��������ߵĽڵ㣬���ʼ�ı����ڵ�
					continue;
				}

				/*
				��ӡ��ǰ�ڵ�
				*/
				System.out.println(node);

				/*
				�����ұ߽ڵ㣬�����ұ߱Ƚ����⣬����Ǻ�̽ڵ㣬����һֱ��������򻻳����ӽڵ����ѭ��
				*/
				//Ȼ���԰�����������ڵ�
				while (! node.getRightStatus())
				{
					//��ӡ��̽ڵ�
					System.out.println(node.getRight());
					//���½ڵ�
					node = node.getRight();
				}

				//��ִ�е���һ�� ����ǰ����жϽ�� �Ҳ�ڵ���������������������
				node = node.getRight();	
			}
			System.out.println("���������������������ɹ���");
			return true;
		}
		else 
		{
			System.out.println("��������������������ʧ�ܣ�");
			return false;
		}
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
}

//�������д洢�Ľڵ���
class HeroNode
{
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;

	//��¼�ڵ������״̬��Ĭ��Ϊtrue,��Ĭ�����߶��� ������ֻ����������ʱ����޸�״̬
	private boolean leftIsTree = true;
	private boolean rightIsTree = true;

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
	public void setLeftStatus(boolean status)
	{
		this.leftIsTree = status;
	}
	public boolean getLeftStatus()
	{
		return this.leftIsTree;
	}
	public void setRightStatus(boolean status)
	{
		this.rightIsTree = status;
	}
	public boolean getRightStatus()
	{
		return this.rightIsTree;
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

	public void count()
	{
		System.out.println("����Ƚ�1��~~~");
	}
}
