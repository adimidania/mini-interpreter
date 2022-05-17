import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Compilateur {
	public HashMap<String, Double> Table_des_symboles;
	public Compilateur() {
		this.Table_des_symboles = new HashMap<String, Double>();
	}
	public boolean Executer(String input) {
			String print = "^print";
			String end = "^end";
			String let = "^let";
			Pattern r = Pattern.compile(let);
			Matcher m = r.matcher(input);
			if(m.find()) {
				Let x = new Let(input, this.Table_des_symboles);
			}
			else {
				Pattern e = Pattern.compile(print);
				Matcher n = e.matcher(input);
				if(n.find()) {
					Print x = new Print(input, this.Table_des_symboles);
				}
				else {
					Pattern p = Pattern.compile(end);
					Matcher w = p.matcher(input);
					if(w.find()) {
						End x = new End(input);
						return false;
					}
					else {
						System.out.println(" > Erreur : La commande saise n'existe pas.");
					}
				}
				
			}
			return true;
	}
}