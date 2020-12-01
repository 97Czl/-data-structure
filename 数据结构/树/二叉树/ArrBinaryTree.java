/*
���������ʽ��ʵ�ֶ�����������ʱ����ǰ���У��������ַ�ʽ����

ԭ��(��ȫ������)
	������������±�����ң��±��0��ʼ
	1.�� n ���ڵ����ڵ�Ϊ 2 * n + 1
	2.�� n ���ڵ���ҽڵ�Ϊ 2 * n + 2
	3.�� n ���ڵ�ĸ��ڵ�Ϊ (n - 1) / 2
*/

public class ArrBinaryTree 
{
	private int[] arr;

	//������
	public ArrBinaryTree(int[] arr)
	{
		this.arr = arr;
	}

	//**************************************************************************
	//�������ֱ�������������index
	//**************************************************************************
	public void preOrder()
	{
		System.out.println("ǰ����������");
		this.preOrder(0);
		System.out.println();
	}
	public void infixOrder()
	{
		System.out.println("������������");
		this.infixOrder(0);
		System.out.println();
	}
	public void postOrder()
	{
		System.out.println("������������");
		this.postOrder(0);
		System.out.println();
	}
	
	//**************************************************************************
	//���ֱ�������
	//**************************************************************************
	//ǰ���������
	public void preOrder(int index)
	{
		//���Ȱ�ȫ���жϣ��������Ϊ�ջ������鳤��Ϊ��
		if (arr == null || arr.length == 0)
		{
			System.out.println("����Ϊ�գ��޷�����");
			return;
		}

		//��ʼǰ����������������ǰԪ��
		System.out.printf("arr[%d] = %d  ", index, arr[index]);

		//�ݹ����
		if (index * 2 + 1 < arr.length)
		{
			preOrder(index * 2 + 1);
		}

		//�ݹ��ұ�
		if (index * 2 + 2 < arr.length)
		{
			preOrder(index * 2 + 2);
		}
	}
	//�����������
	public void infixOrder(int index)
	{
		//���Ȱ�ȫ���жϣ��������Ϊ�ջ������鳤��Ϊ��
		if (arr == null || arr.length == 0)
		{
			System.out.println("����Ϊ�գ��޷�����");
			return;
		}

		//��ʼ������������ȵݹ����
		if (index * 2 + 1 < arr.length)
		{
			infixOrder(index * 2 + 1);
		}

		//�����ǰԪ��
		System.out.printf("arr[%d] = %d  ", index, arr[index]);

		//�ݹ��ұ�
		if (index * 2 + 2 < arr.length)
		{
			infixOrder(index * 2 + 2);
		}
	}
	//�����������
	public void postOrder(int index)
	{
		//���Ȱ�ȫ���жϣ��������Ϊ�ջ������鳤��Ϊ��
		if (arr == null || arr.length == 0)
		{
			System.out.println("����Ϊ�գ��޷�����");
			return;
		}

		//��ʼǰ����������ȵݹ����
		if (index * 2 + 1 < arr.length)
		{
			postOrder(index * 2 + 1);
		}

		//�ݹ��ұ�
		if (index * 2 + 2 < arr.length)
		{
			postOrder(index * 2 + 2);
		}

		//�����ǰԪ��
		System.out.printf("arr[%d] = %d  ", index, arr[index]);
	}

	public static void main(String[] args) 
	{
		/*
		 �������ṹ ��               1
								  2      3
								 4 5    6 7
								8 9
		*/
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		ArrBinaryTree arrTree = new ArrBinaryTree(arr);

		//ǰ�������� 1 2 4 8 9 5 3 6 7
		arrTree.preOrder();

		//���������� 8 4 9 2 5 1 6 3 7
		arrTree.infixOrder();

		//���������� 8 9 4 5 2 6 7 3 1
		arrTree.postOrder();
	}
}