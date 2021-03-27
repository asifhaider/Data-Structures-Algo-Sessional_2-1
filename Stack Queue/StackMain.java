import java.util.Scanner;

public class StackMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Arithmetic Expression Evaluator!");
        System.out.println("Please provide your expression with integer operands!");
        System.out.println("You can include parenthesis as well as +, -, *, / operators");
        System.out.println("=====================================================");

        Scanner scn = new Scanner(System.in);
        while (scn.hasNextLine()){
            String expression = scn.nextLine();
            if(expression.equalsIgnoreCase("Exit")){
                System.out.println("The Program is shut down");
                break;
            }

            ExpressionEvaluation e = new ExpressionEvaluation();
            String[] postFixExpression = e.convertToPostFixNotation(expression);
            if (postFixExpression == null){
                System.out.println("Not Valid");
                continue;
            } else{
                double expressionValue = e.performOperation(postFixExpression);
                if (!e.isValid()){
                    System.out.println("Not Valid");
                    continue;
                }
                System.out.print("Valid Expression, Computed Value: ");
                System.out.println(expressionValue);
            }
        }
    }
}
