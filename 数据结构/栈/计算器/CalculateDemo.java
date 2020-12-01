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
	//两个栈，分别用来存放数字和运算符
	ArrayStack numStack = new ArrayStack(50);
	ArrayStack opreStack = new ArrayStack(30);
	
	//计算的方法
	public int run(String string)
	{
		//表明当前数据位置的索引
		int index = 0;
		//计算过程需要用的变量
		char ch = ' ';	//当前的数据
		boolean continueNum = false;

		while (true)
		{
			//提取每一次索引的数值
			ch = string.substring(index, index+1).charAt(0);
			
			//判断该符号是否为数字
			if (ifNumber(ch) == 1)
			{
				//数字判断
				//1.该数字前面是否为数字，如果是，加上前面的数字，如果不是，直接加进去
				//2.加完后判断是否为最后一个，然后判断后面是否还有数字
				if (continueNum)
					{
						numStack.push(numStack.pop() * 10 + (ch - 48));
						//如果不是最后一个位置
						if (index != string.length() - 1)
						{
							//判断下一个是不是数字
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
					//如果不是最后一个位置
					if (index != string.length() - 1)
					{
						//判断下一个是不是数字
						if (ifNumber(string.substring(index + 1, index + 2).charAt(0)) == 1)
						{
							continueNum = true;
						}
						else continueNum = false;
					}
				}
			}
			//如果是运算符
			else if (ifNumber(ch) == 2)
			{
				//运算符较复杂：
				//1.如果栈空，直接入栈
				//2.如果栈不空  2.1 如果运算符比前面优先级高，直接入栈 2.2如果运算符等级比前面低或者相等，取出两个数字和一个运算符，运算，再将结果压入数字栈，再将当前运算符压入符号栈
				if (opreStack.ifEmpty())
				{
					opreStack.push(ch);
				}
				else 
				{
					while (true)
					{
						//判断该等级是否高
						if (getPri(ch) <= getPri((char)opreStack.peek()))
						{
							int num1 = numStack.pop();	//第一个数字
							int num2 = numStack.pop();;	//第二个数字
							char opre = (char)opreStack.pop();	//操作符
							int res = singleCal(num1, num2, opre);
							//取出两个数字，运算,将结果入栈
							numStack.push(res);
							if (opreStack.ifEmpty())
							{
								opreStack.push(ch);
								break;
							}							
						}
						else 
						{
							//直接入栈
							opreStack.push(ch);
							break;
						}
					}
				}
			}

			//每次结束，索引加一，并判断结束没
			index++;
			if (index >= string.length())
			{
				break;
			}
		}
		
		//遍历完后，再将栈中所有数字计算一遍
		while (true)
		{
			int num1 = numStack.pop();	//第一个数字
			int num2 = numStack.pop();	//第二个数字
			char opre = (char)opreStack.pop();	//操作符
			int res = singleCal(num1, num2, opre);
			//取出两个数字，运算,将结果入栈
			numStack.push(res);

			//如果操作符栈空了，则结束了
			if (opreStack.ifEmpty())
			{
				break;
			}
		}

		//将结果出栈
		int result = numStack.pop();

		return result;
	}

	//判断符号类型 数字为1，操作符为2
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

	//判断符号优先级
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

	//单步计算
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
	//用数组实现 包括 容量、栈顶、存放数据的数组
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
			System.out.println("栈的大小不符合规定！！");
		}
	}

	//判断栈是否非空
	public boolean ifEmpty()
	{
		return top == -1;
	}

	//判断栈是否是满的
	public boolean ifFull ()
	{
		return top == maxSize - 1;
	}

	//入栈操作(push)
	public void push (int data)
	{
		if (ifFull())
		{
			System.out.println("栈已满，无法入栈~~");
			return;
		}

		//如果栈没满，将top上移，然后放置数据
		top++;
		stack[top] = data;
	}

	//出栈操作（pop）
	public int pop ()
	{
		if (ifEmpty())
		{
			throw new RuntimeException("栈为空");
		}

		//如果有数据，先将数据存储，然后将栈顶下移一位
		int result = stack[top];
		top--;
		return result;
	}

	//返回栈顶元素但不出站
	public int peek()
	{
		return stack[top];
	}

	public void list ()
	{
		if (ifEmpty())
		{
			System.out.println("目前栈为空");
		}
		//倒叙展示数组
		for (var i = top; i >= 0; i--)
		{
			System.out.printf("stack[%d] = %d\n", i, stack[i]);
		}
	}
}

