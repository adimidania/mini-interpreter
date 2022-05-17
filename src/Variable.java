class Variable {
    public String nom;
    public double valeur;
    public Variable(String nom, double valeur) throws VariableNameError {
        if(this.checkNom(nom)) {
            this.nom = nom;
            this.valeur = valeur;
        }
    }
    public boolean checkNom(String nom) throws VariableNameError {
		boolean found = false;
		for (Functions f : Functions.values())
		{
			if (f.name().equals(nom))
			{
				found = true;
				break;
			}
		}
		for (codeOperation c : codeOperation.values())
		{
			if (found || c.name().equals(nom))
			{
				found = true;
				break;
			}
		}
        if(found || nom.matches("\\d.+")) {
			throw new VariableNameError("");
			
		}
		return true;
	}
}