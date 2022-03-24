package com.company;
import java.util.Stack;
import java.util.Scanner;

/** source code from GeekforGeeks https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
 * accessed 2022-03-24
 */

public class StackTest {
    private static int Prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // The main method that converts
    // given infix expression
    // to postfix expression.
    private static String infixToPostfix(String exp) {
        // initializing empty String for result
        String result = new String("");

        // initializing empty stack
        Stack<Character> stackTest = new Stack<>();

        for (int i = 0; i<exp.length(); ++i) {
            char c = exp.charAt(i);

            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result += c;

                // If the scanned character is an '(',
                // push it to the stack.
            else if (c == '(')
                stackTest.push(c);

                //  If the scanned character is an ')',
                // pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')')
            {
                while (!stackTest.isEmpty() &&
                        stackTest.peek() != '(')
                    result += stackTest.pop();

                stackTest.pop();
            }
            else // an operator is encountered
            {
                while (!stackTest.isEmpty() && Prec(c)
                        <= Prec(stackTest.peek())){

                    result += stackTest.pop();
                }
                stackTest.push(c);
            }

        }

        // pop all the operators from the stack
        while (!stackTest.isEmpty()){
            if(stackTest.peek() == '(')
                return "Invalid Expression";
            result += stackTest.pop();
        }
        return result;
    }

    // Driver method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String exp = input.nextLine();
        System.out.println(infixToPostfix(exp));
    }
}
