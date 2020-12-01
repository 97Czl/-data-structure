import java.util.*;

public class AllCalculateDemo 
{
	public static void main(String[] args) 
	{
		//����Ҫ������沨�����ʽ
		//(3+4)��5-6 ��Ӧ�ĺ�׺���ʽ���� 3 4 + 5 �� 6 -
		String origin = "(3+4)*5-6";
		Poland poland = new Poland(origin);
		

		//System.out.println(origin + "  ����׺���ʽ��  " + poland.getSuffix());
		
		int result = new PolandCalculate().calculate(poland.getSuffix());
		System.out.println(origin + " = " + result);
	}
}

class Poland
{
	String origin;
	List<String> middle;
	List<String> suffixList;

	public Poland(String origin)
	{
		this.origin = origin;
		this.middle = stringToList(origin);
		this.suffixList = middleToSuffix(middle);
	}

	//��ѯ�������ʽ�ķ�����
	public String getOrigin() {return this.origin;} 
	public List<String> getMiddle() {return this.middle;} 
	public List<String> getSuffix() {return this.suffixList;} 

	//����׺stringת��Ϊlist����׺���ʽ
	private List<String> stringToList(String equation)
	{
		//������ǰ����λ�õ�����
		int index = 0;
		//���������Ҫ�õı���
		char ch = ' ';	//��ǰ������
		boolean continueNum = false;
		//���������
		List<String> list = new ArrayList<String>();

		while (true)
		{
			//��ȡÿһ����������ֵ
			ch = equation.charAt(index);
			
			//�жϸ÷����Ƿ�Ϊ����
			if (ifNumber(ch))
			{
				//�����ж�
				//1.������ǰ���Ƿ�Ϊ���֣�����ǣ�����ǰ������֣�������ǣ�ֱ�Ӽӽ�ȥ
				//2.������ж��Ƿ�Ϊ���һ����Ȼ���жϺ����Ƿ�������
				if (continueNum)
					{
						list.add("" + (Integer.parseInt(list.remove(index)) * 10 + (ch - 48)));

						//����������һ��λ��
						if (index != equation.length() - 1)
						{
							//�ж���һ���ǲ�������
							if (ifNumber(equation.charAt(index + 1)))
							{
								continueNum = true;
							}
							else continueNum = false;
						}
					}
				else
				{
					list.add(Character.toString(ch));
					//����������һ��λ��
					if (index != equation.length() - 1)
					{
						//�ж���һ���ǲ�������
						if (ifNumber(equation.charAt(index + 1)))
						{
							continueNum = true;
						}
						else continueNum = false;
					}
				}
			}
			//����������
			else
			{
				list.add(Character.toString(ch));
			}

			//ÿ�ν�����������һ�����жϽ���û
			index++;
			if (index >= equation.length())
			{
				break;
			}
		}
		return list;
	}
	
	//����׺���ʽת��Ϊ��׺���ʽ
	//1.��ʼ������ջ�������ջs1�ʹ����м�����ջs2��
	//2.��������ɨ����׺���ʽ��
	//3����������ʱ������ѹs2��
	//4���������ʱ���Ƚ�����s1ջ������������ȼ���
	// 4.1���s1Ϊ�գ���ջ�������Ϊ�����š�(������ֱ�ӽ����������ջ��
	// 4.2���������ȼ���ջ��������ĸߣ�Ҳ�������ѹ��s1��
	// 4.3���򣬽�s1ջ���������������ѹ�뵽s2�У��ٴ�ת��(4-1)��s1���µ�ջ���������Ƚϣ�
	//5.��������ʱ��(1) ����������š�(������ֱ��ѹ��s1(2) ����������š�)���������ε���s1ջ�������������ѹ��s2��ֱ������������Ϊֹ����ʱ����һ�����Ŷ���
	//6.�ظ�����2��5��ֱ�����ʽ�����ұ�
	//7.��s1��ʣ�����������ε�����ѹ��s2
	//8.���ε���s2�е�Ԫ�ز���������������Ϊ��׺���ʽ��Ӧ�ĺ�׺���ʽ
	private List<String> middleToSuffix(List<String> middle)
	{
		//�����һ��ջs1���������һֱ�䶯������
		Stack<String> s1 = new Stack<>();
		//����s2����Ҫ��ջ���Һ�����Ҫ���������ֱ�Ӳ�������洢����Ϊ����
		List<String> s2 = new ArrayList<String>();

		//����
		int index = 0;
		//���� ��׺���ʽ
		do
		{
			String st = middle.get(index);
			//���������������
			if (ifNumber( middle.get(index) ))
			{
				//ֱ����ջs2
				s2.add(st);
			}
			else if (st.equals("(") || st.equals(")"))
			{
				if (st.equals("("))	//(1) ����������š�(������ֱ��ѹ��s1
				{
					s1.push(st);
				}
				else				//(2) ����������š�)���������ε���s1ջ�������������ѹ��s2��ֱ������������Ϊֹ����ʱ����һ�����Ŷ���
				{
					String tool;
					while (true)
					{
						tool = s1.pop();
						if (tool.equals("("))
						{
							break;
						}
						s2.add(tool);
					}
				}
			}
			else if (st.equals("+") || st.equals("-") || st.equals("*") || st.equals("/"))
			{
				while (true)
				{
					if (s1.isEmpty() || s1.peek().equals("("))		//4.1���s1Ϊ�գ���ջ�������Ϊ�����š�(������ֱ�ӽ����������ջ��
					{
						s1.push(st);
						break;
					}
					else if (getPri(st) > getPri(s1.peek()))		//4.2���������ȼ���ջ��������ĸߣ�Ҳ�������ѹ��s1��
					{
						s1.push(st);
						break;
					}
					else											//���򣬽�s1ջ���������������ѹ�뵽s2�У��ٴ�ת��(4-1)��s1���µ�ջ���������Ƚϣ�
					{
						s2.add(s1.pop());
					}
				}				
			}
			else 
				throw new RuntimeException("����ʶ�����");

			index++;
		}
		while (index < middle.size());
		

		//7.��s1��ʣ�����������ε�����ѹ��s2
		while (!s1.isEmpty())
		{
			s2.add(s1.pop());
		}

		return s2;
	}

	//�жϷ������ȼ�
	private int getPri(String opre)
	{
		if (opre.equals("+") || opre.equals("-"))
		{
			return -1;
		}
		if (opre.equals("*") || opre.equals("/"))
		{
			return 1;
		}
			return 0;
	}

	//�жϷ������� ����Ϊ1��������Ϊ2
	private boolean ifNumber(char ch)
	{
		if (ch < 58 && ch > 47)
		{
			return true;
		}
		else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')')
		{
			return false;
		}
		return false;
	}
	
	//�жϷ������� ����Ϊ1��������Ϊ2
	private boolean ifNumber(String st)
	{
		if (st.equals("+") || st.equals("-") || st.equals("*") || st.equals("/") || st.equals("(") || st.equals(")"))
			return false;
		else return true;
	}
}

class PolandCalculate
{
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
					case "*":
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