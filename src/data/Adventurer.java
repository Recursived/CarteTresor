package data;

import java.util.Objects;

public class Adventurer 
	implements IInteractableElement, ILoggable {

	private final String name;
	private final String moveSequence;
	private Orientation orientation;
	private int posI, posJ, previousI, previousJ;
	private int treasureCount;
	private int moveIndex;
	private boolean doneMoving;
	
	/*
	 * Private methods section
	 */
	private void turnLeft() {
		switch (orientation) {
		case North:
			orientation = Orientation.West;
			break;
		case East:
			orientation = Orientation.North;
			break;
		case South:
			orientation = Orientation.East;
			break;
		case West:
			orientation = Orientation.South;
			break;
		}
	}
	
	private void turnRight() {
		switch (orientation) {
		case North:
			orientation = Orientation.East;
			break;
		case East:
			orientation = Orientation.South;
			break;
		case South:
			orientation = Orientation.West;
			break;
		case West:
			orientation = Orientation.North;
			break;
		}
	}
	
	private void moveForward() {
		switch (orientation) {
		case North:
			posI--;
			break;
		case East:
			posJ++;
			break;
		case South:
			posI++;
			break;
		case West:
			posJ--;
			break;
		}
	}
	

	/*
	 * Private methods section
	 */
	public Adventurer(String name, Orientation orientation, int pos_i, int pos_j, String seq) {
		this(name, orientation, pos_i, pos_j, seq, 0, false, 0);
	}

	public Adventurer(String name, Orientation orientation, int pos_i, int pos_j, String seq, int treasureCount, boolean doneMoving, int moveIndex) {
		super();
		this.name = name;
		this.orientation = orientation;
		this.posI = pos_i;
		this.posJ = pos_j;
		this.previousI = pos_i;
		this.previousJ = pos_j;
		this.moveSequence = seq;
		this.treasureCount = treasureCount;
		this.doneMoving = doneMoving;
		this.moveIndex = 0;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(moveSequence, name, orientation, posI, posJ, treasureCount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adventurer other = (Adventurer) obj;
		return Objects.equals(moveSequence, other.moveSequence) && Objects.equals(name, other.name)
				&& orientation == other.orientation && posI == other.posI && posJ == other.posJ
				&& treasureCount == other.treasureCount;
	}
	
	@Override
	public String toString() {
		// # {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}
		return "A - "+ name + " - " + posJ+ " - " + posI + " - "
				+ orientation.getLabel() + " - " + treasureCount;
	}

	
	
	public int getPosI() {
		return posI;
	}

	public int getPosJ() {
		return posJ;
	}
	
	public String getMoveSequence() {
		return moveSequence;
	}
	
	public boolean isDoneMoving() {
		return doneMoving;
	}

	public void setDoneMoving(boolean doneMoving) {
		this.doneMoving = doneMoving;
	}
	
	public boolean didNotMove() {
		return previousI == posI && previousJ == posJ;
	}
	
	public void move() {
		if (!doneMoving && moveIndex == moveSequence.length()) {
			setDoneMoving(true);
		} else {
			previousI = posI;
			previousJ = posJ;
			switch(moveSequence.charAt(moveIndex++)) {
				case 'A':
					moveForward();
					break;
				case 'G':
					turnLeft();
					break;
				case 'D':
					turnRight();
					break;
			}
		}
	}

	public void collectTreasure() {
		treasureCount++;
	}

	public void revertMove() {
		switch (orientation) {
		case North:
			posI++;
			break;
		case East:
			posJ--;
			break;
		case South:
			posI--;
			break;
		case West:
			posJ++;
			break;
		}
	}

	@Override
	public void interact(Adventurer a) {
		if (posI == a.posI && posJ == a.posJ) {
			a.revertMove();
		}
	}

	@Override
	public String formatLog() {
		return "A(" + this.name + ")";
	}
}
