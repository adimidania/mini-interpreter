import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Print extends Commande {
    private Expression exp;
    private double resultat;
	private String pattern = "^print (.+)$";
	
    public Print(String line, HashMap<String, Double> symtab) {
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (m.find())
		{
			this.exp = new Expression(m.group(1), symtab); 
			try
			{
				this.resultat = this.exp.Evaluer();
				this.AfficherResultat();
			}
			catch (OpenParentheses e) {
				System.out.println(" > Erreur : Parenthèse ouvrante manquante");
			}
			catch (ClosingParentheses e) {
				System.out.println(" > Erreur : Parenthèse fermante manquante ");
			}
			catch (UnsupportedOperationException e) {
				System.out.println(e.getMessage());
			}
			catch (ExpressionEronne e) {
				System.out.println(e.getMessage());
			}
			catch (VariableExistence e) {
				System.out.println(e.getMessage());
			}
			catch (FunctionError e)
			{
				System.out.println(" > Erreur :  Fonction eronnée.");
			}
		}
		else {
			System.out.println(" > Erreur :  La commande Print est mal saise.");
		}
		
    }
    
    public void AfficherResultat() {
        System.out.println(" > La valeur est : " + this.resultat);
    }
}