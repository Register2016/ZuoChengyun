package chapter_1_stackandqueue;

import java.util.Stack;

/**
 * 栈排序 时间复杂度O(N),空间复杂度O(N)
 */
public class Problem_05_StackSortStack {

	public static void sortStackByStack(Stack<Integer> stack) {
		// 1.申请一个辅助栈help
		Stack<Integer> help = new Stack<Integer>();
		// 2.只要栈不为空，则弹出栈顶元素top，比较栈顶元素与辅助栈help栈顶元素top'大小，如果top' < top, 则将top'弹出压入原栈
		// 3.将栈顶元素top压入help
		// 4.待原栈都压入辅助栈后，将辅助栈元素依次弹出入原栈
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			while (!help.isEmpty() && help.peek() < cur) {
				stack.push(help.pop());
			}
			help.push(cur);
		}
		while (!help.isEmpty()) {
			stack.push(help.pop());
		}
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(3);
		stack.push(1);
		stack.push(6);
		stack.push(2);
		stack.push(5);
		stack.push(4);
		sortStackByStack(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}

}
