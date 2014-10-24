import java.util.*;
public class H {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Stack<Integer> stack = new Stack<Integer>();
		String err_under = "stack underflow";
		String err_zero = "division by zero exception";
		String err_out = "out of range exception";
		while(in.hasNextLine()) {
			String s = in.nextLine();
			char c = s.charAt(0);
			if(c == '=') {
				if(stack.size() < 1)
					System.out.println(err_under);
				else {
					int num = stack.peek();
					if(num <= 0 || num > 4999)
						System.out.println(err_out);
					else
						System.out.println(ItoR(num));
				}
			}
			else if(c == '+') {
				if(stack.size() < 2)
					System.out.println(err_under);
				else {
					int sum = stack.pop() + stack.pop();
					stack.push(sum);
				}
			}
			else if(c == '-') {
				if(stack.size() < 2)
					System.out.println(err_under);
				else {
					int num1 = stack.pop();
					int num2 = stack.pop();
					int diff = num2 - num1;
					stack.push(diff);
				}
			}
			else if(c == '*') {
				if(stack.size() < 2)
					System.out.println(err_under);
				else {
					stack.push(stack.pop() * stack.pop());
				}
			}
			else if(c == '/') {
				if(stack.size() < 2)
					System.out.println(err_under);
				else {
					int num1 = stack.pop();
					int num2 = stack.pop();
					if(num1 == 0) {
						System.out.println(err_zero);
						stack.push(num2);
					}
					else {
						stack.push(num2 / num1);
					}
				}
			}
			else {
				stack.push(RtoI(s));
			}
		}
	}
	static int[] map = new int[127];
	static {
		map['I'] = 1;
		map['V'] = 5;
		map['X'] = 10;
		map['L'] = 50;
		map['C'] = 100;
		map['D'] = 500;
		map['M'] = 1000;
	}
	public static int RtoI(String r) {
		int num = 0;
		int last = 0;
		for(int i = 0; i < r.length(); ++i) {
			if(last < map[r.charAt(i)]) {
				num -= 2 * last;
			}
			last = map[r.charAt(i)];
			num += last;
		}
		return num;
	}
	public static String ItoR(int num) {
		String s = "";
		while(num > 0) {
			if(num >= 1000) {
				s += "M";
				num -= 1000;
			}
			else if(num >= 900) {
				s += "CM";
				 num -= 900;
			}
			else if(num >= 500) {
				s += "D";
				num -= 500;
			}
			else if(num >= 400) {
				s += "CD";
				num -= 400;
			}
			else if(num >= 100) {
				s += "C";
				num -= 100;
			}
			else if(num >= 90) {
				s += "XC";
				num -= 90;
			}
			else if(num >= 50) {
				s += "L";
				num -= 50;
			}
			else if(num >= 40) {
				s += "XL";
				num -= 40;
			}
			else if(num >= 10) {
				s += "X";
				num -=10;
			}
			else if(num >= 9) {
				s += "IX";
				num -= 9;
			}
			else if(num >= 5) {
				s += "V";
				num -= 5;
			}
			else if(num >= 4) {
				s += "IV";
				num -= 4;
			}
			else {
				s += "I";
				--num;
			}
		}
		return s;
	}
}
