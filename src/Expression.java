import java.util.HashMap;
import java.util.Stack;

class Expression implements Evaluable {
    private String exp;
	private HashMap<String, Double> symtab;
    public Expression(String exp, HashMap<String, Double> tbl) {
		this.exp = exp;
		this.symtab = tbl;
	}
    
    public int checkParenthese() {
    	int i,total=0;
    	char[] exp1 = this.exp.toCharArray();
    	for(i=0;i<this.exp.length();i++) {
    		if(exp1[i] == '(') {
    			total ++;
    		}
    		else if(exp1[i] == ')') {
    			total --;
    		}
    	}
    	return total;
    }
	
    public boolean Precedent(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        if(op1 == '^')
            return false;
        else
            return true;
    }

    public double Operation(char op, double b, double a) throws UnsupportedOperationException {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '^':
                return Math.pow(a, b);
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException(" > Erreur : La division par zéro n'est pas possible.");
                return a / b;
        }
        return 0;
    }
    public double Evaluer() throws FunctionError, OpenParentheses, ClosingParentheses,VariableExistence, UnsupportedOperationException, ExpressionEronne{
		if(this.checkParenthese()>0) {
			throw new ClosingParentheses();
		}
		else if(this.checkParenthese()<0) {
			throw new OpenParentheses();
		}
		else {
	        char[] exp1 = this.exp.toCharArray();
	        Stack<Double> valeurs = new Stack<Double>();
	        Stack<Character> operateurs = new Stack<Character>();
	        for (int i = 0; i < exp1.length; i++) {
	            if (exp1[i] == ' ')
	                continue;
	            if (exp1[i] >= '0' && exp1[i] <= '9') { 
	                StringBuffer sbuf = new StringBuffer();
	                while (i < exp1.length && exp1[i] >= '0' && exp1[i] <= '9')
	                    sbuf.append(exp1[i++]);
	                valeurs.push(Double.parseDouble(sbuf.toString()));
	                i--;
	            }
				else if ((exp1[i] >= 'a' && exp1[i] <= 'z') || (exp1[i] >= 'A' && exp1[i] <= 'Z'))
				{
					StringBuffer sbuf = new StringBuffer();
	                while (i < exp1.length && ((exp1[i] >= '0' && exp1[i] <= '9') || (exp1[i] >= 'a' && exp1[i] <= 'z') || (exp1[i] >= 'A' && exp1[i] <= 'Z')))
	                    sbuf.append(exp1[i++]);
					if (i < exp1.length && exp1[i] == '(')
					{
						i++;
						int open_parenthesis = 0;
						StringBuffer sbuf2 = new StringBuffer();
						while (i < exp1.length)
						{
							if (exp1[i] == '(') open_parenthesis++;
							else if (exp1[i] == ')') open_parenthesis--;
							if (open_parenthesis >= 0 ) sbuf2.append(exp1[i++]);
							else break;
						} 
						double val = new Expression(sbuf2.toString(), this.symtab).Evaluer();
						valeurs.push(Function.Evaluer(sbuf.toString(), val));
					}
					else
					{
						Double val = this.symtab.get(sbuf.toString());
						if (val == null) {
							throw new VariableExistence(" > Erreur : La variable " + sbuf.toString() + " n'existe pas.");
						}
						valeurs.push((double) val);
						i--;
					}
				}
	            else if (exp1[i] == '(')
	                operateurs.push(exp1[i]);
	            else if (exp1[i] == ')')
	            {
	                while (operateurs.peek() != '(')
		                    valeurs.push(Operation(operateurs.pop(),
		                            valeurs.pop(),
		                            valeurs.pop()));
	                operateurs.pop();
	            }
	            else if (exp1[i] == '+' || exp1[i] == '-' || exp1[i] == '*' ||
	                    exp1[i] == '/' || exp1[i] == '^')
	            {
	                while (!operateurs.empty() &&
	                        Precedent(exp1[i],
	                                operateurs.peek()))
	                    valeurs.push(Operation(operateurs.pop(),
	                            valeurs.pop(),
	                            valeurs.pop()));
	                operateurs.push(exp1[i]);
	            }
	        }
	        if(operateurs.size() > valeurs.size()) {
	        	throw new ExpressionEronne(" > Erreur : L'expression est eronnée.");
	        }
	        else {
		        while (!operateurs.empty())
		        	if(operateurs.size() == 1 && valeurs.size() == 1)
		        		valeurs.push(Operation(operateurs.pop(),valeurs.pop(),0));
		        	else
		        		valeurs.push(Operation(operateurs.pop(),valeurs.pop(),valeurs.pop()));
	        }
	        return valeurs.pop();
		}
    }
}