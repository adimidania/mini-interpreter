import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Let extends Commande {
    public Variable var;
	private String pattern = "^let ([^=]+)=(.+)$";
    public Let(String line, HashMap<String, Double> symtab) {
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (m.find())
		{
			String varname = m.group(1).replace(" ", "");
			try {
				double value = new Expression(m.group(2), symtab).Evaluer();
				this.var = new Variable(varname, value);
				symtab.put(this.var.nom, this.var.valeur);
				this.AfficherResultat();
			}
			catch (FunctionError e)
			{
				System.out.println("");
			}
			catch (OpenParentheses e) {
				System.out.println(" > Erreur : Parenthèse ouvrante manquante. ");
			}
			catch (ClosingParentheses e) {
				System.out.println(" > Erreur : Parenthèse fermante manquante. ");
			}
			catch (VariableExistence e) {
				System.out.println(e.getMessage());
			}
			catch (UnsupportedOperationException e) {
				System.out.println(e.getMessage());
			}
			catch (ExpressionEronne e) {
				System.out.println(e.getMessage());
			}
			catch (VariableNameError e)
			{
				System.out.println(" > Erreur : Le nom de la variable n'est pas valide!");
			}
		}
		else {
			System.out.println(" > Erreur :  La commande Let est mal saise.");
		}
    }
    public void AfficherResultat() {
        System.out.println(" > Ok!");
    }
}