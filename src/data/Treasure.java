package data;

public class Treasure extends MapElement 
	implements ICollectibleElement {
	
	private int treasureCount;
	
	public Treasure(int posI, int posJ, int treasureCount) {
		super(posI, posJ, MapElementType.Treasure);
		this.treasureCount = treasureCount;
	}
	
	@Override
	public void interact(Adventurer a) {
		if (!a.didNotMove() &&
				(this.getPosI() == a.getPosI() && this.getPosJ() == a.getPosJ()) 
				&& this.collect()) {
			a.collectTreasure();
		}
	}

	@Override
	public boolean collect() {
		if (treasureCount == 0) {
			return false;
		}
		treasureCount--;
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String formatLog() {
		return this.getType().getLabel() + "(" + this.treasureCount + ")";
	}
	
	@Override
	public String toString() {
		if (this.treasureCount == 0) return "";
		return super.toString() + " - " + this.treasureCount;
	}

	

}
