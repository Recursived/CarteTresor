package data;

public class Mountain extends MapElement {

	public Mountain(int posI, int posJ) {
		super(posI, posJ, MapElementType.Mountain);
	}

	@Override
	public void interact(Adventurer a) {
		if (this.getPosI() == a.getPosI() && this.getPosJ() == a.getPosJ()) {
			a.revertMove();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String formatLog() {
		return this.getType().getLabel();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
