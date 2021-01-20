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
		//�Ե�һ���ַ��������������
		while (i < str1.length() && j < str2.length())
		{
			if (str1.charAt(i) == str2.charAt(j))
			{
				//�����ƥ�䵽����ôj����ƶ�һλ
				i++;
				j++;
			}
			else
			{
				//���û��ƥ�䵽��˵����ǰ�ַ����ԣ��˻�ȥ����
				//i - j ����ƥ�䵽�ĵ�һλ������Ҫ��1���������ִ�У�������ѭ����
				i = i - j + 1;
				j = 0;
			}
		}

		//while�����������ж�j������λ�ã����j�ߵ��˾�ͷ��˵���ҵ��ˣ�����û�ҵ�
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