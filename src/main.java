import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		System.out.println("** Début du Programme **");
		boolean p = true;
		Compilateur x = new Compilateur();
		while(p) {
			Scanner myObj = new Scanner(System.in);
			String inp = myObj.nextLine();
			p = x.Executer(inp);		
		}
	}

}
