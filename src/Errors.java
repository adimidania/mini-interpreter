class ExpressionEronne extends Exception {
    public ExpressionEronne(String errorMessage) {
        super(errorMessage);
    }
}
class FunctionError extends Exception {
    public FunctionError(String errorMessage) {
        super(errorMessage);
    }
}
class VariableNameError extends Exception {
    public VariableNameError(String errorMessage) {
        super(errorMessage);
    }
}
class VariableTypeError extends Exception {
    public VariableTypeError(String errorMessage) {
        super(errorMessage);
    }
}
class InvalidFormatError extends Exception {
	public InvalidFormatError(String errorMessage) {
		super(errorMessage);
	}
}
class VariableExistence extends Exception {
	public VariableExistence(String errorMessage) {
		super(errorMessage);
	}	
}
class OpenParentheses extends Exception {

}
class ClosingParentheses extends Exception {

}