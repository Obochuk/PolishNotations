import java.util.Stack;

public class PostfixNotationCalc {
    private final int MaxOperationLen = 1;
    private final int ArgumentsAmount = 2;

    public double evaluate(String expr) {
        if (expr.isEmpty())
            return 0;
        Stack<Double> numbers = new Stack<>();
        for (String op: expr.trim().split(" ")){
            if (isProbablyNumber(op))
                numbers.push(Double.parseDouble(op));
            else
            if (numbers.size() < ArgumentsAmount)
                throw new IllegalArgumentException();
            else
                numbers.push(calculate(op, numbers.pop(), numbers.pop()));
        }
        return numbers.pop();
    }

    private boolean isProbablyNumber(String num){
        if (num.isEmpty())
            throw new IllegalArgumentException();
        if (num.length() > MaxOperationLen)
            return true;
        if (Character.isDigit(num.charAt(MaxOperationLen - 1)))
            return true;
        return false;
    }

    private double calculate(String operation, double rop, double lop){
        switch (operation){
            case "+":
                return (lop + rop);
            case "-":
                return (lop - rop);
            case "*":
                return (lop * rop);
            case "/":
                return (lop / rop);
            default:
                throw new IllegalArgumentException();
        }
    }
}
