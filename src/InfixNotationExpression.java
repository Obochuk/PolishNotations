import java.util.List;

import static java.util.Arrays.asList;

public class InfixNotationExpression {
    public InfixNotationExpression(List<String> operators) {
        this.operators = operators;
    }

    public InfixNotationExpression() {
        operators = standartOperators;
    }

    private List<String> operators;
    private List<String> standartOperators =
            asList("(", ")", "+", "-", "*", "/");

    public String toInfixNotation(String input){
        PostfixNotationExpression expression = new PostfixNotationExpression();
        StringBuilder stringBuilder = new StringBuilder(expression.toPostfixNotation(input));
        return stringBuilder.reverse().toString();
    }
}
