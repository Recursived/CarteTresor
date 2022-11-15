package data;

import java.util.Objects;

public abstract class MapElement implements ILoggable, IInteractableElement{
	private MapElementType type;
	private final int posI, posJ;
	

	public int getPosI() {
		return posI;
	}

	public int getPosJ() {
		return posJ;
	}

	public MapElementType getType() {
		return this.type;
	}
	
	public MapElement(int posI, int posJ, MapElementType type) {
		super();
		this.posI = posI;
		this.posJ = posJ;
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(posI, posJ);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapElement other = (MapElement) obj;
		return posI == other.posI && posJ == other.posJ;
	}
	
	@Override
	public String toString() {
		return this.type.getLabel() + " - " + this.posJ + " - " + this.posI;
	}
	
	@Override
	public void interact(Adventurer a) {}
	
}
