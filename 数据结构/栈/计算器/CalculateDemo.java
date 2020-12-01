public class CalculateDemo 
{
	public static void main(String[] args) 
	{
		Calculate cal1 = new Calculate();
		String expression = "5*4+45+558-34*3-4*4/2+1-2-3+4+345+3*4-6+2";
		int result = cal1.run(expression);
		System.out.println(expression + " = " + result);
	}
}

class Calculate
{
	//����ջ���ֱ�����������ֺ������
	ArrayStack numStack = new ArrayStack(50);
	ArrayStack opreStack = new ArrayStack(30);
	
	//����ķ���
	public int run(String string)
	{
		//������ǰ����λ�õ�����
		int index = 0;
		//���������Ҫ�õı���
		char ch = ' ';	//��ǰ������
		boolean continueNum = false;

		while (true)
		{
			//��ȡÿһ����������ֵ
			ch = string.substring(index, index+1).charAt(0);
			
			//�жϸ÷����Ƿ�Ϊ����
			if (ifNumber(ch) == 1)
			{
				//�����ж�
				//1.������ǰ���Ƿ�Ϊ���֣�����ǣ�����ǰ������֣�������ǣ�ֱ�Ӽӽ�ȥ
				//2.������ж��Ƿ�Ϊ���һ����Ȼ���жϺ����Ƿ�������
				if (continueNum)
					{
						numStack.push(numStack.pop() * 10 + (ch - 48));
						//����������һ��λ��
						if (index != string.length() - 1)
						{
							//�ж���һ���ǲ�������
							if (ifNumber(string.substring(index + 1, index + 2).charAt(0)) == 1)
							{
								continueNum = true;
							}
							else continueNum = false;
						}
					}
				else
				{
					numStack.push(ch - 48);
					//����������һ��λ��
					if (index != string.length() - 1)
					{
						//�ж���һ���ǲ�������
						if (ifNumber(string.substring(index + 1, index + 2).charAt(0)) == 1)
						{
							continueNum = true;
						}
						else continueNum = false;
					}
				}
			}
			//����������
			else if (ifNumber(ch) == 2)
			{
				//������ϸ��ӣ�
				//1.���ջ�գ�ֱ����ջ
				//2.���ջ����  2.1 ����������ǰ�����ȼ��ߣ�ֱ����ջ 2.2���������ȼ���ǰ��ͻ�����ȣ�ȡ���������ֺ�һ������������㣬�ٽ����ѹ������ջ���ٽ���ǰ�����ѹ�����ջ
				if (opreStack.ifEmpty())
				{
					opreStack.push(ch);
				}
				else 
				{
					while (true)
					{
						//�жϸõȼ��Ƿ��
						if (getPri(ch) <= getPri((char)opreStack.peek()))
						{
							int num1 = numStack.pop();	//��һ������
							int num2 = numStack.pop();;	//�ڶ�������
							char opre = (char)opreStack.pop();	//������
							int res = singleCal(num1, num2, opre);
							//ȡ���������֣�����,�������ջ
							numStack.push(res);
							if (opreStack.ifEmpty())
							{
								opreStack.push(ch);
								break;
							}							
						}
						else 
						{
							//ֱ����ջ
							opreStack.push(ch);
							break;
						}
					}
				}
			}

			//ÿ�ν�����������һ�����жϽ���û
			index++;
			if (index >= string.length())
			{
				break;
			}
		}
		
		//��������ٽ�ջ���������ּ���һ��
		while (true)
		{
			int num1 = numStack.pop();	//��һ������
			int num2 = numStack.pop();	//�ڶ�������
			char opre = (char)opreStack.pop();	//������
			int res = singleCal(num1, num2, opre);
			//ȡ���������֣�����,�������ջ
			numStack.push(res);

			//���������ջ���ˣ��������
			if (opreStack.ifEmpty())
			{
				break;
			}
		}

		//�������ջ
		int result = numStack.pop();

		return result;
	}

	//�жϷ������� ����Ϊ1��������Ϊ2
	public int ifNumber(char ch)
	{
		if (ch < 58 && ch > 47)
		{
			return 1;
		}
		else if (ch == '+' || ch == '-' || ch == '*' || ch == '/')
		{
			return 2;
		}
		return 0;
	}

	//�жϷ������ȼ�
	public int getPri(char opre)
	{
		if (opre == '+' || opre == '-')
		{
			return -1;
		}
		if (opre == '*' || opre == '/')
		{
			return 1;
		}
			return 0;
	}

	//��������
	public int singleCal(int num1, int num2, char opre)
	{
		int res = 0;
		switch (opre)
		{
			case '+':
				res = num1 + num2;
				break;
			case '-':
				res = num2 - num1;
				break;
			case '*':
				res = num1 * num2;
				break;
			case '/':
				res = num2 / num1;
				break;
			default:
				break;
		}
		return res;
	}
}

class ArrayStack
{
	//������ʵ�� ���� ������ջ����������ݵ�����
	private int maxSize;
	private int[] stack;
	private int top;

	public ArrayStack(int maxSize)
	{
		try
		{
			this.maxSize = maxSize;
			stack = new int[this.maxSize];
			this.top = -1;
		}
		catch (Exception e)
		{
			System.out.println("ջ�Ĵ�С�����Ϲ涨����");
		}
	}

	//�ж�ջ�Ƿ�ǿ�
	public boolean ifEmpty()
	{
		return top == -1;
	}

	//�ж�ջ�Ƿ�������
	public boolean ifFull ()
	{
		return top == maxSize - 1;
	}

	//��ջ����(push)
	public void push (int data)
	{
		if (ifFull())
		{
			System.out.println("ջ�������޷���ջ~~");
			return;
		}

		//���ջû������top���ƣ�Ȼ���������
		top++;
		stack[top] = data;
	}

	//��ջ������pop��
	public int pop ()
	{
		if (ifEmpty())
		{
			throw new RuntimeException("ջΪ��");
		}

		//��������ݣ��Ƚ����ݴ洢��Ȼ��ջ������һλ
		int result = stack[top];
		top--;
		return result;
	}

	//����ջ��Ԫ�ص�����վ
	public int peek()
	{
		return stack[top];
	}

	public void list ()
	{
		if (ifEmpty())
		{
			System.out.println("ĿǰջΪ��");
		}
		//����չʾ����
		for (var i = top; i >= 0; i--)
		{
			System.out.printf("stack[%d] = %d\n", i, stack[i]);
		}
	}
}

