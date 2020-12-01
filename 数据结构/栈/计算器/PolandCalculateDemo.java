import java.util.*;

public class PolandCalculateDemo 
{
	public static void main(String[] args) 
	{
		//定义要计算的逆波兰表达式
		//(3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 -
		String origin = "(3+4)×5-6";
		String poland = "3 4 + 5 × 6 -";
		List<String> polandList = PolandCalculate.stringToPoland(poland);
		
		int result = new PolandCalculate().calculate(polandList);
		System.out.println(origin + " = " + result);
	}
}

class PolandCalculate
{
	//将字符串转化为逆波兰表达式
	public static List<String> stringToPoland(String equation)
	{
		//对字符串进行按照空格分割
		String[] splitResult = equation.split(" ");
		//定义链表
		ArrayList<String> poland = new ArrayList<>();

		//遍历数组，将数组存入到链表中
		for (String ele : splitResult)
		{
			poland.add(ele);
		}
		
		return poland;
	}

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
					case "×":
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