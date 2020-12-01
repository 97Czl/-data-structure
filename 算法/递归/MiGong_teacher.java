public class MiGong_teacher
{
	//先创建一个二维数组，模拟迷宫地图
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

	//对迷宫进行初始化
	private void init()
	{
		//设置围墙
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

		//设置挡板
		map[4][1] = 1;
		map[4][2] = 1;
		map[3][2] = 1;
		map[2][2] = 1;

	}

	//输出数组
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

	//使用递归回溯来给小球找路
	//1.i，j表示从地图的哪个位置开始出发
	//2.如果小球能到map[6][5]位置，则说明通路找到
	//3.约定：当map[i][j]为0表示该店没有走过，当为1时表示墙，当为2时表示同路可以走
	//	3表示该点已经走过，但是走不通
	//4.在走迷宫时，需要确定一个策略 下->右->上->左，如果该点走不通，再回溯

	public boolean setWay(int i, int j)
	{
		show();
		System.out.println();
		if (map[5][4] == 2)
		{
			//通路已经找到
			return true;
		}
		else
		{
			if (map[i][j] == 0)
			{
				//如果当前点还没走过
				map[i][j] = 2;				//假设该点可以走通
				if (setWay(i + 1, j))		//向下走
				{
					return true;
				}
				else if (setWay(i, j + 1))	//向右走
				{
					return true;
				}
				else if (setWay(i - 1, j))	//向上走
				{
					return true;
				}
				else if (setWay(i, j - 1))	//向下走
				{
					return true;
				}
				else
				{
					//说明该点走不通
					map[i][j] = 3;
					return false;
				}
			}
			else
			{
				//如果map[i][j] != 0,可能是1，2，3
				return false;
			}
		}
	}
}