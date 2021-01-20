public class ViolenceMatchDemo 
{
	public static void main(String[] args) 
	{
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";

		System.out.println("index = " + ViolenceMatch.violenceMatch(str1, str2));
	}
}

class ViolenceMatch
{
	public static int violenceMatch(String str1, String str2)
	{
		int i = 0;
		int j = 0;
		//对第一个字符串挨个遍历检查
		while (i < str1.length() && j < str2.length())
		{
			if (str1.charAt(i) == str2.charAt(j))
			{
				//如果能匹配到，那么j向后移动一位
				i++;
				j++;
			}
			else
			{
				//如果没有匹配到，说明当前字符不对，退回去继续
				//i - j 是能匹配到的第一位，所以要加1，继续向后执行，否则死循环了
				i = i - j + 1;
				j = 0;
			}
		}

		//while遍历结束后，判断j所处的位置，如果j走到了尽头，说明找到了，否则没找到
		if (j == str2.length())
		{
			return i - j;
		}
		else
		{
			return -1;
		}
	}
}