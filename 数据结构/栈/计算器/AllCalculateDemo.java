import java.util.*;

public class AllCalculateDemo 
{
	public static void main(String[] args) 
	{
		//定义要计算的逆波兰表达式
		//(3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 -
		String origin = "(3+4)*5-6";
		Poland poland = new Poland(origin);
		

		//System.out.println(origin + "  的中缀表达式是  " + poland.getSuffix());
		
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

	//查询三个表达式的方法；
	public String getOrigin() {return this.origin;} 
	public List<String> getMiddle() {return this.middle;} 
	public List<String> getSuffix() {return this.suffixList;} 

	//将中缀string转化为list的中缀表达式
	private List<String> stringToList(String equation)
	{
		//表明当前数据位置的索引
		int index = 0;
		//计算过程需要用的变量
		char ch = ' ';	//当前的数据
		boolean continueNum = false;
		//储存的链表
		List<String> list = new ArrayList<String>();

		while (true)
		{
			//提取每一次索引的数值
			ch = equation.charAt(index);
			
			//判断该符号是否为数字
			if (ifNumber(ch))
			{
				//数字判断
				//1.该数字前面是否为数字，如果是，加上前面的数字，如果不是，直接加进去
				//2.加完后判断是否为最后一个，然后判断后面是否还有数字
				if (continueNum)
					{
						list.add("" + (Integer.parseInt(list.remove(index)) * 10 + (ch - 48)));

						//如果不是最后一个位置
						if (index != equation.length() - 1)
						{
							//判断下一个是不是数字
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
					//如果不是最后一个位置
					if (index != equation.length() - 1)
					{
						//判断下一个是不是数字
						if (ifNumber(equation.charAt(index + 1)))
						{
							continueNum = true;
						}
						else continueNum = false;
					}
				}
			}
			//如果是运算符
			else
			{
				list.add(Character.toString(ch));
			}

			//每次结束，索引加一，并判断结束没
			index++;
			if (index >= equation.length())
			{
				break;
			}
		}
		return list;
	}
	
	//将中缀表达式转化为后缀表达式
	//1.初始化两个栈：运算符栈s1和储存中间结果的栈s2；
	//2.从左至右扫描中缀表达式；
	//3遇到操作数时，将其压s2；
	//4遇到运算符时，比较其与s1栈顶运算符的优先级：
	// 4.1如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
	// 4.2否则，若优先级比栈顶运算符的高，也将运算符压入s1；
	// 4.3否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
	//5.遇到括号时：(1) 如果是左括号“(”，则直接压入s1(2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
	//6.重复步骤2至5，直到表达式的最右边
	//7.将s1中剩余的运算符依次弹出并压入s2
	//8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
	private List<String> middleToSuffix(List<String> middle)
	{
		//定义第一个栈s1，用来存放一直变动的数据
		Stack<String> s1 = new Stack<>();
		//由于s2不需要出栈，且后续需要逆序输出，直接采用链表存储，更为方便
		List<String> s2 = new ArrayList<String>();

		//索引
		int index = 0;
		//遍历 中缀表达式
		do
		{
			String st = middle.get(index);
			//如果遍历到了数字
			if (ifNumber( middle.get(index) ))
			{
				//直接入栈s2
				s2.add(st);
			}
			else if (st.equals("(") || st.equals(")"))
			{
				if (st.equals("("))	//(1) 如果是左括号“(”，则直接压入s1
				{
					s1.push(st);
				}
				else				//(2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
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
					if (s1.isEmpty() || s1.peek().equals("("))		//4.1如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
					{
						s1.push(st);
						break;
					}
					else if (getPri(st) > getPri(s1.peek()))		//4.2否则，若优先级比栈顶运算符的高，也将运算符压入s1；
					{
						s1.push(st);
						break;
					}
					else											//否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
					{
						s2.add(s1.pop());
					}
				}				
			}
			else 
				throw new RuntimeException("符号识别错误");

			index++;
		}
		while (index < middle.size());
		

		//7.将s1中剩余的运算符依次弹出并压入s2
		while (!s1.isEmpty())
		{
			s2.add(s1.pop());
		}

		return s2;
	}

	//判断符号优先级
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

	//判断符号类型 数字为1，操作符为2
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
	
	//判断符号类型 数字为1，操作符为2
	private boolean ifNumber(String st)
	{
		if (st.equals("+") || st.equals("-") || st.equals("*") || st.equals("/") || st.equals("(") || st.equals(")"))
			return false;
		else return true;
	}
}

class PolandCalculate
{
	//对逆波兰表达式进行计算
	//1.从左往右遍历
	//2.1 如果遍历到数字，直接压入栈中
	//2.2 如果遍历到符号，直接pop出两个数字进行计算，然后进行计算，再将数字压入栈中
	//3. 最后的数字就是计算结果
	public int calculate(List<String> poland)
	{
		//定义一个保存数据的栈
		Stack<String> stack = new Stack<>();
		//对链表进行遍历
		for (String ele : poland)
		{
			//判断遍历到的元素是什么
			if (ele.matches("\\d"))
			{
				//如果匹配到的是数字
				stack.push(ele);
			}
			else
			{
				//如果匹配到的是符号
				//首先定义需要用的变量
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
						throw new RuntimeException("运算符错误");
				}
				//计算结束后，将结果入栈
				stack.push("" + result);
			}
		}
		//返回结果
		return Integer.parseInt(stack.pop());
	}
}