import java.io.*;

public class LabSolution {
    private static final int FILENAME_POS = 0;
    private static final int ARGUMENT_AMOUNT = 1;
    private static final String OUT_FILENAME = "result.txt";

    public static void main(String[] args) throws IOException{
        FileGetter getter = new FileGetter(FILENAME_POS);
        File inFile = getter.getFile(args, ARGUMENT_AMOUNT);
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        File outFile = new File(OUT_FILENAME);
        PrintWriter writer = new PrintWriter(outFile);

        System.out.println("Calculate the result of expression(Y/N)?");
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        boolean calculate = (reader1.readLine().toUpperCase().equals("Y")? true: false);

        InfixNotationExpression infixNotationExpression = new InfixNotationExpression();
        PostfixNotationExpression postfixNotationExpression = new PostfixNotationExpression();
        PostfixNotationCalc calculator = (calculate == false? null: new PostfixNotationCalc());

        String line;
        while ((line = reader.readLine()) != null){
            writer.write("For expression: "+ line + '\n');
            writer.write("Postfix notation: " + postfixNotationExpression.toPostfixNotation(line) + '\n');
            String temp = postfixNotationExpression.toPostfixNotation(line);
            writer.write("Infix notation: " + infixNotationExpression.toInfixNotation(line) + '\n');
            if (calculator != null)
                writer.write("Result: " + calculator.evaluate(postfixNotationExpression.toPostfixNotation(line)) + "\n");
        }
        writer.flush();
    }
}
