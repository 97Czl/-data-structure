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

//KMP�㷨����Ҫ��;���ַ���ƥ�䣬������ڱ���ƥ����ŵ������next���������
/*
	next�����ж�����ʽ�������о������֣�next������Ҫ������Ƕ�Ӧ���ַ�����ǰ׺�ͺ�׺�Ĺ�����󳤶�
	���� aba ��ǰ׺����Ϊ a ab ��׺����Ϊa ba�����Թ�������Ϊa������Ϊ1��������next�����Ӧ�±�ҲΪ1��
	����һ���ַ�����˵��next���鱣����ǵ�ǰ�±�ǰ�������ַ����������������������ǰ��׺���󹫹�����
	����������ַ���ABCDABD   �±�Ϊ0ʱ��ֻ��A����ôû��ǰ��׺������Ϊ0 �±�Ϊ1ʱ���ַ���ΪAB��ǰ��׺�ֱ�ֻ��A  B��û�й����ģ����Գ��Ȼ���0
	�±�Ϊ4��ABCDA�� ����ǰ׺��������� A, AB, ABC, ABCD  ��׺��������� A, DA, CDA, BCDA,��������A�����Գ���Ϊ1�����Կ��Եĵ�next����Ϊ
	�ַ���	ABCDABD
	next	0000120
	//����Ҳ��һ�ַ�ʽ���ǽ������������������һλ����ʾ�����±�Ϊi��ȥ����ǰiԪ�ص������ַ���������Ҫ�����ƶ�һλ������û������𣬼���
	next	-1000012
*/
class KMPMatch
{
	public static int kmpMatch(String str1, String str2)
	{
		//���Ȼ�ȡ��Ӧ��next����
		int[] next1 = getNext(str2);
		int[] next2 = getNextRightShift(str2);

		for (int i = 0, j = 0; i < str1.length(); i++)
		{
			//����ͬnextһ�������л��ݣ������ȴ���ƥ������
			while (j > 0 &&  str1.charAt(i) != str2.charAt(j))
			{
				//���ûƥ�䵽������j,i�ǲ����ģ���Ϊǰ׺��֮ǰ�ĺ�׺��ͬ���Ѿ�ƥ�����
				j = next1[j - 1];
				//j = next2[j];
			}
			//������ƥ�䵽�����
			if (str1.charAt(i) == str2.charAt(j))
			{
				j++;
			}
			//ÿ�ν����󣬿��Ƿ�ƥ�����ˣ�ǰ����ͬ��ʱ����j++�����ĳɹ��ˣ�j�϶����ڳ���
			if (j == str2.length())
			{
				return i - (j - 1);
			}
		}
		return -1;
	}

	//����չʾ��һ��next�Ļ�ȡ����
	public static int[] getNext(String dest)
	{
		int[] next = new int[dest.length()];
		next[0] = 0;
		//����i������Ǻ�׺ĩβ��j�������ǰ׺ĩβ
		for (int i = 1, j = 0; i < dest.length(); i++)
		{
			//����Ӧ�ÿ��ǲ��ǲ�ƥ�䣬��Ϊ�ȿ�ƥ��Ľ���Ļ�����ƥ���ʱ�����ػ��ݿ���ֱ��i++��һ���ˣ�������
			while (j > 0 && dest.charAt(i) != dest.charAt(j))
			{
				//�����ƥ�䣬�ͽ�j��Ϊ��һ�����ȵ�ʱ�������ȣ����ܷ�ƥ�䵽��ƥ�䲻���Ļ���һֱ���أ�ֱ���ʼ0��λ��
				j = next[j - 1];
			}
			//����ƥ�䵽�����
			if (dest.charAt(i) == dest.charAt(j))
			{
				//�����ƥ�䵽��ô����j��++����Σ��Ե�ǰ���鸳ֵ j���±��0��ʼ��������++��ֵ
				j++;
				next[i] = j;
			}
		}

		return next;
	}
	//�ڶ�����ʽ��next����
	public static int[] getNextRightShift(String dest)
	{
		int[] next = new int[dest.length()];
		//������������0Ϊ-1
		next[0] = -1;
		//��������������k����ǰ׺��β��j�����׺β��
		int k = -1;
		int j = 0;

		//ֻҪû��������һֱ����
		while (j < dest.length() - 1)
		{
			if (k == -1 || dest.charAt(k) == dest.charAt(j))
			{
				//��Ϊ��������洢��next ��ȥ����ǰλ�õ��ַ���õ��Ľ�����������������ƶ�һλ�ٸ�ֵ
				j++;
				k++;
				next[j] = k;
			}
			else
			{
				//���û��ƥ�䵽������л���
				k = next[k];
			}
		}
		
		return next;
	}
}