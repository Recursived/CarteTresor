package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import utils.Pair;

/**
 * 
 * @author alexm
 * Main data Object representing a game of "Chasse aux trésors"
 */
public class Game implements IInteractableElement {
	private int largeur, hauteur;
	private List<Adventurer> adventurers;
	private Map<Pair<Integer,Integer>, MapElement> elements;
	
	public Game() {
		this(0, 0);
	}
	
	public Game(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.adventurers = new Vector<Adventurer>();
		this.elements = new HashMap<Pair<Integer,Integer>, MapElement>();
	}


	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public List<Adventurer> getAdventurers() {
		return adventurers;
	}

	public Map<Pair<Integer,Integer>, MapElement> getElements() {
		return elements;
	}
	
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	
	public void setAdventurers(List<Adventurer> adventurers) {
		this.adventurers = adventurers;
	}

	public void setElements(Map<Pair<Integer, Integer>, MapElement> elements) {
		this.elements = elements;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "C - " + this.largeur + " - " + this.hauteur;
	}

	@Override
	public void interact(Adventurer a) {
		if ((this.hauteur == a.getPosI() || a.getPosI() == -1) || 
				(this.largeur == a.getPosJ() || a.getPosJ() == -1)) {
			a.revertMove();
		}
		
	}
}
