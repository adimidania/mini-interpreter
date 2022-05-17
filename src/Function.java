class Function {
    private String nom;
    private double parametre;
    public Function(String nom, double parametre) {
    	this.nom = nom;
    	this.parametre = parametre;
    }
    public static double Evaluer(String nom, double parametre) throws FunctionError, OpenParentheses, ClosingParentheses,VariableExistence,UnsupportedOperationException {
        switch(nom) {
            case "sin":
                return Math.sin(parametre);
            case "cos":
                return Math.cos(parametre);
            case "tan":
                return Math.tan(parametre);
            case "abs":
                return Math.abs(parametre);
            case "sqrt":
                return Math.sqrt(parametre);
            case "log":
                return Math.log(parametre);
            default:
                throw new FunctionError(" > Erreur : La fonction saise n'existe pas.");
        }
    }
}