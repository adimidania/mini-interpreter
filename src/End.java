import java.util.regex.Matcher;
import java.util.regex.Pattern;

class End extends Commande {
	private String pattern = "^end$";
    public End(String line) {
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (!m.find())
			System.out.println(" > Erreur :  La commande End est mal saise.");
		else {
	        this.AfficherResultat();
	        this.TerminerLeProgramme();	
		}
    }
    public void TerminerLeProgramme() {
        System.exit(0);
    }
    public void AfficherResultat() {
        System.out.println(" > Le programme est terminé!");
    }
}