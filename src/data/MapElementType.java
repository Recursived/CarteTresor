package data;

public enum MapElementType {
	Mountain("M"),
	Treasure("T");

	private final String label;
	
	MapElementType(String string) {
		this.label = string;
	}

	public String getLabel() {
		return label;
	}
	
}
