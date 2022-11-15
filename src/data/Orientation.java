package data;

public enum Orientation {
	North("N"),
	East("E"),
	West("O"),
	South("S");
	
	private String label;
	
	Orientation(String string) {
		this.label = string;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public static Orientation getOrientation(String s) {
		for (Orientation o :Orientation.values()) {
			if (o.getLabel().equals(s)) return o;
		}
		return null;
	}

}
