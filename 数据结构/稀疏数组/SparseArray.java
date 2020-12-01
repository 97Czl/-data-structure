import java.io.*;

public class SparseArray 
{
	private int[][] sourceArray;
	private int[][] sparseArray;

	//�����������ʼ��
	public void init(int[][] data)
	{
		//�������鸳��ԭʼ��ά����
		sourceArray = new int[data.length][data[0].length];
		for (var i = 0; i < data.length; i++)
		{
			for (var j = 0; j < data[0].length; j++)
			{
				sourceArray[i][j] = data[i][j];
			}
		}

		//��ϡ��������г�ʼ��
		//1.ͳ��ԭʼ��ά����ķ���Ԫ���ж���
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
		//2.��ϡ�������ʼ��
		sparseArray = new int[sum + 1][3];
		sparseArray[0][0] = sourceArray.length;
		sparseArray[0][1] = sourceArray[0].length;
		sparseArray[0][2] = sum;
	}
	
	//��ԭʼ����ת��Ϊϡ������
	public void ordinaryToSparse()
	{
		var index = 1;
		//��ԭʼ������б������ҳ�����Ԫ�ض�ϡ�����鸳ֵ
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

	//��ϡ������ת��Ϊԭʼ����
	public void sparseToOrdinary()
	{
		//��ϡ��������б������ҳ�����Ԫ�ض�ԭʼ���鸳ֵ
		for (var i = 1; i < sparseArray.length; i++)
		{
			sourceArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
		}
	}

	//��ϡ�����ݴ���
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

	//�����ݶ�ȡ����
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
	
	//�����������
	public void printArray()
	{
		System.out.println("--------------------------------ԭʼ����---------------------------------------");
		for (var i = 0; i < sourceArray.length; i++)
		{
			for (var j = 0; j < sourceArray[i].length; j++)
			{
				System.out.printf("%d\t", sourceArray[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------------ϡ������---------------------------------------");
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
		System.out.println("--------------------------------��ʼ��---------------------------------------");
		sa.init(data);
		sa.printArray();
		System.out.println("--------------------------------ת��Ϊϡ������---------------------------------------");
		sa.ordinaryToSparse();
		sa.printArray();
		System.out.println("--------------------------------���ԭʼ����---------------------------------------");
		sa.clearO();
		sa.printArray();
		System.out.println("--------------------------------���---------------------------------------");
		sa.saveData("memory.data");
		System.out.println("--------------------------------ȡ�������ݻָ�---------------------------------------");
		sa.restore("memory.data");
		sa.printArray();
	}
}
