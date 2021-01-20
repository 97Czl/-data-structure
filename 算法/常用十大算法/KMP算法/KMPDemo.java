import java.util.*;

public class KMPDemo 
{
	public static void main(String[] args) 
	{
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";
		
		//System.out.println(Arrays.toString(KMPMatch.getNextRightShift(str2)));
		System.out.println("index = " + KMPMatch.kmpMatch(str1, str2));
	}
}

//KMP算法的主要用途是字符串匹配，其相对于暴力匹配的优点就在于next数组的引进
/*
	next数组有多种形式，这里列举了两种，next数组主要保存的是对应的字符串的前缀和后缀的公共最大长度
	例如 aba 的前缀可以为 a ab 后缀可以为a ba，所以公共区间为a，长度为1，所以其next数组对应下标也为1，
	对于一个字符串来说，next数组保存的是当前下标前的所有字符串经过上述方法算出来的前后缀过大公共长度
	如对于上述字符串ABCDABD   下标为0时，只有A，那么没有前后缀，长度为0 下标为1时，字符串为AB，前后缀分别只有A  B，没有公共的，所以长度还是0
	下标为4，ABCDA， 这样前缀可能情况有 A, AB, ABC, ABCD  后缀可能情况有 A, DA, CDA, BCDA,公共的有A，所以长度为1，所以可以的到next数组为
	字符串	ABCDABD
	next	0000120
	//但是也有一种方式，是将上述数组整体向后移一位，表示的是下标为i是去掉当前i元素的所有字符串，所以要往后移动一位，本质没大的区别，即：
	next	-1000012
*/
class KMPMatch
{
	public static int kmpMatch(String str1, String str2)
	{
		//首先获取对应的next数组
		int[] next1 = getNext(str2);
		int[] next2 = getNextRightShift(str2);

		for (int i = 0, j = 0; i < str1.length(); i++)
		{
			//这里同next一样，都有回溯，所以先处理不匹配的情况
			while (j > 0 &&  str1.charAt(i) != str2.charAt(j))
			{
				//如果没匹配到，更新j,i是不动的，因为前缀和之前的后缀相同，已经匹配过了
				j = next1[j - 1];
				//j = next2[j];
			}
			//处理能匹配到的情况
			if (str1.charAt(i) == str2.charAt(j))
			{
				j++;
			}
			//每次结束后，看是否匹配完了，前面相同的时候有j++如果真的成功了，j肯定等于长度
			if (j == str2.length())
			{
				return i - (j - 1);
			}
		}
		return -1;
	}

	//这里展示第一种next的获取方法
	public static int[] getNext(String dest)
	{
		int[] next = new int[dest.length()];
		next[0] = 0;
		//这里i代表的是后缀末尾，j代表的是前缀末尾
		for (int i = 1, j = 0; i < dest.length(); i++)
		{
			//首先应该看是不是不匹配，因为先看匹配的结果的话，不匹配的时候往回回溯可能直接i++下一轮了，落掉情况
			while (j > 0 && dest.charAt(i) != dest.charAt(j))
			{
				//如果不匹配，就将j置为上一个长度的时候的最长长度，看能否匹配到，匹配不到的话就一直往回，直到最开始0的位置
				j = next[j - 1];
			}
			//处理匹配到的情况
			if (dest.charAt(i) == dest.charAt(j))
			{
				//如果能匹配到那么首先j得++，其次，对当前数组赋值 j的下标从0开始，所以先++后赋值
				j++;
				next[i] = j;
			}
		}

		return next;
	}
	//第二种形式的next数组
	public static int[] getNextRightShift(String dest)
	{
		int[] next = new int[dest.length()];
		//该种情况下这个0为-1
		next[0] = -1;
		//定义两个变量，k代表前缀结尾，j代表后缀尾部
		int k = -1;
		int j = 0;

		//只要没结束，就一直遍历
		while (j < dest.length() - 1)
		{
			if (k == -1 || dest.charAt(k) == dest.charAt(j))
			{
				//因为这个方法存储的next 是去掉当前位置的字符后得到的结果，所以首先往后移动一位再赋值
				j++;
				k++;
				next[j] = k;
			}
			else
			{
				//如果没有匹配到。则进行回溯
				k = next[k];
			}
		}
		
		return next;
	}
}