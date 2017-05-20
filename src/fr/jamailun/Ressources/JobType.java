package fr.jamailun.Ressources;

public enum JobType {

	MINEUR("mineur"),
	BUCHERON("bucheron"),
	PAYSAN("paysan"),
	FORGERON("forgeron"),
	ALCHIMISTE("alchimiste");
	
	public String name;
	
	private JobType(String name) {
		this.name = name;
	}
}
