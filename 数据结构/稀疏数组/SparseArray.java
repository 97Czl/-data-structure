import java.io.*;

public class SparseArray 
{
	private int[][] sourceArray;
	private int[][] sparseArray;

	//对两个数组初始化
	public void init(int[][] data)
	{
		//将该数组赋给原始二维数组
		sourceArray = new int[data.length][data[0].length];
		for (var i = 0; i < data.length; i++)
		{
			for (var j = 0; j < data[0].length; j++)
			{
				sourceArray[i][j] = data[i][j];
			}
		}

		//对稀疏数组进行初始化
		//1.统计原始二维数组的非零元素有多少
		int sum = 0;
		for (var i = 0; i < sourceArray.length; i++)
		{
			for (var j = 0; j < sourceArray[i].length; j++)
			{
				if (sourceArray[i][j] != 0)
				{
					sum++;
				}
			}
		}
		//2.对稀疏数组初始化
		sparseArray = new int[sum + 1][3];
		sparseArray[0][0] = sourceArray.length;
		sparseArray[0][1] = sourceArray[0].length;
		sparseArray[0][2] = sum;
	}
	
	//将原始数组转化为稀疏数组
	public void ordinaryToSparse()
	{
		var index = 1;
		//对原始数组进行遍历，找出非零元素对稀疏数组赋值
		for (var i = 0; i < sourceArray.length; i++)
		{
			for (var j = 0; j < sourceArray[i].length; j++)
			{
				if (sourceArray[i][j] != 0)
				{
					sparseArray[index][0] = i;
					sparseArray[index][1] = j;
					sparseArray[index][2] = sourceArray[i][j];
					index++;
				}
			}
		}
	}

	//将稀疏数组转化为原始数组
	public void sparseToOrdinary()
	{
		//对稀疏数组进行遍历，找出非零元素对原始数组赋值
		for (var i = 1; i < sparseArray.length; i++)
		{
			sourceArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
		}
	}

	//将稀疏数据存盘
	public boolean saveData(String name)
	{
		try
			(
				var fw = new FileWriter(name))
		{
			for (var i = 0; i < sparseArray.length; i++)
			{
				for (var j = 0; j < sparseArray[i].length; j++)
				{
					fw.write(sparseArray[i][j]);
				}
			}
			fw.close();
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	//将数据读取出来
	public boolean restore(String name)
	{
		try(
			var fr = new FileReader(name))
		{
			for (var i = 0; i < sparseArray.length; i++)
			{
				for (var j = 0; j < sparseArray[i].length; j++)
				{
					sparseArray[i][j] = fr.read();
				}
			}
			fr.close();
			sparseToOrdinary();
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void clearO()
	{
		for (var i = 0; i < sourceArray.length; i++)
		{
			for (var j = 0; j < sourceArray[i].length; j++)
			{
				sourceArray[i][j] = 0;
			}
		}
	}
	
	//输出两个数组
	public void printArray()
	{
		System.out.println("--------------------------------原始数组---------------------------------------");
		for (var i = 0; i < sourceArray.length; i++)
		{
			for (var j = 0; j < sourceArray[i].length; j++)
			{
				System.out.printf("%d\t", sourceArray[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------------稀疏数组---------------------------------------");
		for (var i = 0; i < sparseArray.length; i++)
		{
			for (var j = 0; j < sparseArray[i].length; j++)
			{
				System.out.printf("%d\t", sparseArray[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) 
	{
		int data[][] = new int[11][11];
		data[7][2] = 1;
		data[3][5] = 2;
		data[5][7] = 4;
		
		var sa = new SparseArray();
		System.out.println("--------------------------------初始化---------------------------------------");
		sa.init(data);
		sa.printArray();
		System.out.println("--------------------------------转化为稀疏数组---------------------------------------");
		sa.ordinaryToSparse();
		sa.printArray();
		System.out.println("--------------------------------清空原始数组---------------------------------------");
		sa.clearO();
		sa.printArray();
		System.out.println("--------------------------------存库---------------------------------------");
		sa.saveData("memory.data");
		System.out.println("--------------------------------取出来数据恢复---------------------------------------");
		sa.restore("memory.data");
		sa.printArray();
	}
}
