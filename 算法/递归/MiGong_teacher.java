public class MiGong_teacher
{
	//�ȴ���һ����ά���飬ģ���Թ���ͼ
	int[][] map = new int[8][7];

	public MiGong_teacher()
	{
		init();
	}
	
	public static void main(String[] args) 
	{
		MiGong_teacher mg = new MiGong_teacher();
		mg.setWay(1, 1);
		mg.show();
	}

	//���Թ����г�ʼ��
	private void init()
	{
		//����Χǽ
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				map[0][j] = 1;
				map[6][j] = 1;
				map[i][0] = 1;
				map[i][5] = 1;
			}
		}

		//���õ���
		map[4][1] = 1;
		map[4][2] = 1;
		map[3][2] = 1;
		map[2][2] = 1;

	}

	//�������
	public void show()
	{
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	//ʹ�õݹ��������С����·
	//1.i��j��ʾ�ӵ�ͼ���ĸ�λ�ÿ�ʼ����
	//2.���С���ܵ�map[6][5]λ�ã���˵��ͨ·�ҵ�
	//3.Լ������map[i][j]Ϊ0��ʾ�õ�û���߹�����Ϊ1ʱ��ʾǽ����Ϊ2ʱ��ʾͬ·������
	//	3��ʾ�õ��Ѿ��߹��������߲�ͨ
	//4.�����Թ�ʱ����Ҫȷ��һ������ ��->��->��->������õ��߲�ͨ���ٻ���

	public boolean setWay(int i, int j)
	{
		show();
		System.out.println();
		if (map[5][4] == 2)
		{
			//ͨ·�Ѿ��ҵ�
			return true;
		}
		else
		{
			if (map[i][j] == 0)
			{
				//�����ǰ�㻹û�߹�
				map[i][j] = 2;				//����õ������ͨ
				if (setWay(i + 1, j))		//������
				{
					return true;
				}
				else if (setWay(i, j + 1))	//������
				{
					return true;
				}
				else if (setWay(i - 1, j))	//������
				{
					return true;
				}
				else if (setWay(i, j - 1))	//������
				{
					return true;
				}
				else
				{
					//˵���õ��߲�ͨ
					map[i][j] = 3;
					return false;
				}
			}
			else
			{
				//���map[i][j] != 0,������1��2��3
				return false;
			}
		}
	}
}