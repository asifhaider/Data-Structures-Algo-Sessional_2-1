class ExpressionEvaluation {
    // two private attributes for tracking the total number of parenthesis of two types
    // unaryMinusCandidate keeps track of potential unary minus operation cases
    private int openingParentheses;
    private int closingParentheses;
    private int unaryMinusCandidate;

    // checking the current validity of the expression
    // if not return to main function and move forward with the next input
    private boolean isValid;
    public boolean isValid() {
        return isValid;
    }

    // default constructor that initializes the private attributes
    public ExpressionEvaluation() {
        this.openingParentheses = 0;
        this.closingParentheses = 0;
        this.isValid = true;
        this.unaryMinusCandidate = 0;
    }

    // calculating the postfix notated expression and returning the result
    public double performOperation(String[] expression){
        // creating a stack of integers
        LinkedStack<Double> numberStack = new LinkedStack<>();

        // handling single digit input
        if (expression.length == 1){
            return Double.valueOf(expression[0]);
        }

        int i = 0;
        while (expression[i]!=null) {
            // working with the allowed operators
            if (expression[i].equals("+") || expression[i].equals("-") || expression[i].equals("*") ||
                    expression[i].equals("/")){
                // at this point a valid number stack cannot be empty
                if (numberStack.isEmpty()){
                    isValid = false;
                    return -1;
                }
                double operand1 = numberStack.stackPop();
                // same as before, while popping out a number when needed, a valid stack must be non-empty
                if (numberStack.isEmpty()){
                    isValid = false;
                    return -1;
                }
                double operand2 = numberStack.stackPop();

                switch (expression[i]){
                    case "+":
                        numberStack.stackPush(operand1 + operand2);
                        //System.out.println(numberStack.stackTop());
                        break;
                    case "*":
                        numberStack.stackPush(operand1 * operand2);
                        break;
                    case "-":
                        numberStack.stackPush(operand2 - operand1);
                        break;
                    case "/":
                        if(operand1 == 0){
                            System.out.println("Can't Evaluate"); // Divisor Zero Case
                        }else
                            numberStack.stackPush(operand2 / operand1);
                        break;
                }
            } else {
                // dealing with the number part
                numberStack.stackPush(Double.valueOf(expression[i]));
            }
            i++;
            if (i==expression.length)
                break;
        }
    return numberStack.stackTop();
    }

    public int precedenceChecker(char operand){
        // putting the correct precedence and assigning default lowest (-1) value to parenthesis
        switch(operand){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1; // custom precedence set for parentheses
    }

    public String[] convertToPostFixNotation (String oldString){
        String newString[] = new String[oldString.length()];
        int index = 0;
        int valueCount = 0;
        int operatorCount = 0;

        LinkedStack<Character> operatorStack = new LinkedStack<>();
        if(oldString.charAt(0)=='-')
            return null; // minus at the very end is invalid

        for (int i=0; i<oldString.length(); i++){
            char element = oldString.charAt(i);

            if (element == '('){
                operatorStack.stackPush(element);
                openingParentheses++;
            }
            // parsing multi-digit numbers from single characters
            else if(Character.isDigit(element)){
                int value = Character.getNumericValue(element);
                if (i < oldString.length()-1){
                    if(Character.isDigit(oldString.charAt(i+1))) {
                        int j = i;
                        while (Character.isDigit(oldString.charAt(j + 1))) {
                            value = value * 10
                                    + Character.getNumericValue(oldString.charAt(j + 1));
                            i++;
                            j++;
                            if (j==oldString.length()-1)
                                break;
                        }
                    }
                }
                newString[index++] = Integer.toString(value);
                valueCount++;
            } else if(element == ')'){
                closingParentheses++;
                while (!operatorStack.isEmpty() && operatorStack.stackTop()!='('){
                    // newString += operatorStack.stackPop();
                    if(operatorStack.stackTop() == '-' && unaryMinusCandidate>0){
                        if(operatorStack.stackBelowTop()=='(') {
                            String temp = newString[index - 1];
                            newString[index - 1] = "0";
                            newString[index++] = temp;
                            valueCount++;
                            unaryMinusCandidate--;
                        }
                    }
                    newString[index++] = operatorStack.stackPop().toString();
                }
                if (operatorStack.isEmpty()){
                    System.out.println("Stack is empty!");
                    return null;

                } else
                    operatorStack.stackPop();
            }else{
                while(!operatorStack.isEmpty() && precedenceChecker(operatorStack.stackTop())
                                                    >=precedenceChecker(element)){

                    newString[index++] = operatorStack.stackPop().toString();
                }

                if (element == '-' && oldString.charAt(i-1) == '('){
                    this.unaryMinusCandidate++;
                }

                operatorStack.stackPush(element);
                operatorCount++;
            }
        }

        if(openingParentheses != closingParentheses){
            // Brackets don't match
            return null;
        }

        while(!operatorStack.isEmpty()){
            if(operatorStack.stackTop() == '('){
                operatorStack.stackPop();
            }

            if(operatorStack.stackTop() == '-'){
                if(valueCount == 1)
                    return null;
                if(operatorStack.stackSize()>1){
                    if(operatorStack.stackBelowTop() == '(') {

                        String temp = newString[index - 1];
                        newString[index - 1] = "0";
                        newString[index] = temp;
                    }
                }
            }
            newString[index++] = operatorStack.stackPop().toString();
        }
        // further checking
        if (valueCount >=2 && operatorCount == 0){
            return null;
        }

        return newString;
    }
}
