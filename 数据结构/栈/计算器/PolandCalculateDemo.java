import java.util.*;

public class PolandCalculateDemo 
{
	public static void main(String[] args) 
	{
		//����Ҫ������沨�����ʽ
		//(3+4)��5-6 ��Ӧ�ĺ�׺���ʽ���� 3 4 + 5 �� 6 -
		String origin = "(3+4)��5-6";
		String poland = "3 4 + 5 �� 6 -";
		List<String> polandList = PolandCalculate.stringToPoland(poland);
		
		int result = new PolandCalculate().calculate(polandList);
		System.out.println(origin + " = " + result);
	}
}

class PolandCalculate
{
	//���ַ���ת��Ϊ�沨�����ʽ
	public static List<String> stringToPoland(String equation)
	{
		//���ַ������а��տո�ָ�
		String[] splitResult = equation.split(" ");
		//��������
		ArrayList<String> poland = new ArrayList<>();

		//�������飬��������뵽������
		for (String ele : splitResult)
		{
			poland.add(ele);
		}
		
		return poland;
	}

	//���沨�����ʽ���м���
	//1.�������ұ���
	//2.1 ������������֣�ֱ��ѹ��ջ��
	//2.2 ������������ţ�ֱ��pop���������ֽ��м��㣬Ȼ����м��㣬�ٽ�����ѹ��ջ��
	//3. �������־��Ǽ�����
	public int calculate(List<String> poland)
	{
		//����һ���������ݵ�ջ
		Stack<String> stack = new Stack<>();
		//��������б���
		for (String ele : poland)
		{
			//�жϱ�������Ԫ����ʲô
			if (ele.matches("\\d"))
			{
				//���ƥ�䵽��������
				stack.push(ele);
			}
			else
			{
				//���ƥ�䵽���Ƿ���
				//���ȶ�����Ҫ�õı���
				int num1 = 0;
				int num2 = 0;
				int result = 0;
				switch (ele)
				{
					case "+":
						num2 = Integer.parseInt(stack.pop());
						num1 = Integer.parseInt(stack.pop());
						result = num1 + num2;
						break;
					case "��":
						num2 = Integer.parseInt(stack.pop());
						num1 = Integer.parseInt(stack.pop());
						result = num1 * num2;
						break;
					case "-":
						num2 = Integer.parseInt(stack.pop());
						num1 = Integer.parseInt(stack.pop());
						result = num1 - num2;
						break;
					case "/":
						num2 = Integer.parseInt(stack.pop());
						num1 = Integer.parseInt(stack.pop());
						result = num1 / num2;
						break;
					default:
						throw new RuntimeException("���������");
				}
				//��������󣬽������ջ
				stack.push("" + result);
			}
		}
		//���ؽ��
		return Integer.parseInt(stack.pop());
	}
}