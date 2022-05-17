interface Evaluable {
    public double Evaluer() throws FunctionError, ClosingParentheses, OpenParentheses,VariableExistence,UnsupportedOperationException, ExpressionEronne;
}