public class PackageProblem 
{
	/*
	��̬�滮����Ľ��˼·�����ڷ����㷨�������������ڣ�
		�����㷨�ֽ��õ���С����һ����˵���໥�����ģ������ʺ��ö�̬�滮�㷨���������ֽ���С����һ�㲻���໥������
	*/
	//���ö�̬�滮�㷨�����������
	/*
	�������⼴����ڸ����ı�������£�����������Ʒ���װ��������ʵ����ߵļ�ֵ��������01��������Ӧ��ȫ��������������Ʒ���ֻ��װһ��
	�������Ʒ����������weight[i]����Ʒ�ļ�ֵ����value[i]�� maxV[i][j]��ʾǰi����Ʒװ������Ϊj�ı���ʱ�ܻ�õ�����ֵ
	��ô�ͻ������µ��ƹ�ʽ��
		1��maxV[i][0] = maxV[0][j] = 0
			���û����Ʒ���߱�������Ϊ0�����ֵΪ0
		2���� weight[i] > j ʱ��maxV[i][j] = maxV[i - 1][j];
			������������Ʒ�����Ǹ���Ʒ�ĵ��������Ѿ����ڱ�������������ô��߼�ֵ������һ�ε����
		3���� j > weight[i] ʱ��maxV[i][j] = max{maxV[i - 1][j], maxV[i - 1][j - weight[i]] + value[i]};
			�������ӵ���Ʒ�ǿ��ԷŽ������ģ���ô��ǰ������ֵ��Ϊ���������Ҫô��װi������֮ǰ����Ϊǰ�ߣ�Ҫô��װ��i��ʣ�¿ռ�װi��ǰ�Ķ���Ϊ���Ž�
	*/
	/**
	 *@param weight ��Ʒ����������
	 *@param value ��Ʒ�ļ�ֵ����
	 *@param capacity ��������
	 *@return ����װ��������������ڵ�֪�������װ��ʲô����
	 */
	public static void solvePackage(int[] weight, int[] value, int capacity)
	{
		//���ȶ��屣�����ż�ֵ�������
		int[][] maxV = new int[weight.length + 1][capacity + 1];
		//������������Щ�����������ص����飬����1������ˣ�0����û��
		int[][] path = new int[weight.length + 1][capacity + 1];

		//��ʼ����
		for (int i = 0; i < maxV.length; i++)
		{
			for (int j = 0; j < maxV[0].length; j++)
			{
				//���ȵ�һ����ʽ���Ե�һ�к͵�һ�и���
				if (i == 0 || j == 0)
				{
					maxV[i][j] = 0;
					continue;
				}

				//�ڶ�������ʽ �� if else �Ĺ�ϵ
				if (weight[i - 1] > j)
				{
					//������ΪmaxV���к��ж��Ƕ���һ��0�����Է���weight��valueʱ���±궼��-1
					maxV[i][j] = maxV[i - 1][j];
					//������ʵ��û���¼���Ʒ�����Բ���Ҫ�޸�path
				}
				else
				{
					//�����������maxV[i][j] = Math.max{maxV[i - 1][j], maxV[i - 1][j - weight[i - 1]] + value[i - 1]};
					//�᲻֪���ǲ�������һ�����ֵ���޷����·�������Լ���ʹ��if else�ṹ
					if (maxV[i - 1][j] >= (maxV[i - 1][j - weight[i - 1]] + value[i - 1]))
					{
						//���������������Ʒi������û��ӣ�����Ҫi��������û�仯
						maxV[i][j] = maxV[i - 1][j];
					}
					else
					{
						//ֻ����һ���Ż������Ʒi
						maxV[i][j] = maxV[i - 1][j - weight[i - 1]] + value[i - 1];
						path[i][j] = 1;
					}
				}
			}
		}
		System.out.println("�������ܼ�ֵΪ��" + maxV[path.length - 1][path[0].length - 1]);
		//����������ӡ����
		//��Ϊǰ�����Ʒһ�����кܶ�����˵ģ�������Ҫ�������
		for (int i = path.length - 1; i > 0;)
		{
			for (int j = path[0].length - 1; j > 0;)
			{
				//ֻ��ӡpath[i] == 1�����
				if (path[i][j] == 1)
				{
					System.out.printf("��%d����Ʒ��װ���˱��� \n", i);
					//�����ӡ��i��Ʒ�󣬱����������͵ü���ȥ
					j = j - weight[i - 1];
					//jֻ����ô������Ϊ�ܹ��ı����������ܶ��������ж����Ѿ���ӡ�ˣ���ȥ�����������������Ž�����
				}
				//�����ǰ����û�ҵ���˵����ǰiδ��ӣ�����һ����Ʒ
				i--;
			}
		}
	}
	public static void main(String[] args) 
	{
		//������Ʒ ���� 1 1500������ 4 3000������ 3 2000
		int[] weight = new int[]{1, 4, 3};
		int[] value = new int[]{1500, 3000, 2000};

		//��������
		int capacity = 4;

		PackageProblem.solvePackage(weight, value, capacity);
	}
}