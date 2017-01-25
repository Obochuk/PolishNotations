import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.util.Arrays.asList;

public class PostfixNotationExpression {
    private List<String> operators;
    private List<String> standartOperators =
            asList("(", ")", "+", "-", "*", "/");
    public PostfixNotationExpression()
    {
        operators = standartOperators;
    }

    public PostfixNotationExpression(List<String> operators) {
        this.operators = operators;
    }

    private List<String> separate(String input){
        int pos = 0;
        List<String> result = new ArrayList<>(40);
        while (pos < input.length()){
            StringBuilder s = new StringBuilder();
            s.append(input.charAt(pos));
            if(!standartOperators.contains(Character.toString(input.charAt(pos)))) {
                if (Character.isDigit(input.charAt(pos)))
                    for (int i = pos + 1; i < input.length() &&
                            (Character.isDigit(input.charAt(i)) ||
                                    input.charAt(i) == ',' ||
                                    input.charAt(i) == '.'); i++) {
                        s.append(input.charAt(i));
                    }
                else if (Character.isLetter(input.charAt(pos)))
                    for (int i = pos + 1; i < input.length() &&
                            (Character.isLetter(input.charAt(i)) ||
                            Character.isDigit(input.charAt(i))); i++) {
                        s.append(input.charAt(i));
                    }
                }
            pos += s.length();
            result.add(s.toString());
        }
        return result;
    }

    private int getPriority(char c){
        switch (c){
            case '(':
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 4;
        }
    }

    public String toPostfixNotation(String input){
        StringBuilder result = new StringBuilder();
        Stack<String> stack = new Stack<>();
        for(String s: separate(input)){
            if (operators.contains(s)){
                if (stack.size() > 0 && !s.equals("(")){
                    if (s.equals(")")){
                        String elem = stack.pop();
                        while (!elem.equals("(")){
                            result.append(elem);
                            elem = stack.pop();
                        }
                        result.append(' ');
                    }
                    else if (getPriority(s.charAt(0)) >
                            getPriority(stack.peek().charAt(0)))
                        stack.push(s);
                    else {
                        while (stack.size() > 0 && getPriority(s.charAt(0)) <=
                                getPriority(stack.peek().charAt(0)))
                            result.append(stack.pop()).append(' ');
                        stack.push(s);
                    }
                }
                else
                    stack.push(s);
            }
            else
                result.append(s).append(" ");
        }
        while (stack.size() > 0){
            result.append(stack.pop()).append(" ");
        }
        return result.toString();
    }
}