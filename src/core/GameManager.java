package core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import client.IUserCommand;
import data.Adventurer;
import data.Game;
import data.MapElement;
import exception.NoGameException;
import io.CarteInOutImp;
import io.ICarteIO;
import utils.Pair;

public class GameManager implements IUserCommand {
	private List<Pair<String, Game>> games;
	private ICarteIO mapIO;
	private boolean logDebug;
	
	
	/*
	 * Private functions section
	 */
	
	private boolean isGameFinished(List<Adventurer> l) {
		return l.stream().allMatch(a -> a.isDoneMoving());
	}
	
	private void doSimulation(Pair<String, Game> p) throws Exception {
		Game g = p.snd;
		List<Adventurer> adventurers = g.getAdventurers();
		Map<Pair<Integer, Integer>, MapElement> m = g.getElements();
		
		while (!isGameFinished(adventurers)) {
			if (this.logDebug) logGameTurn(g);
			for (int i = 0; i < adventurers.size(); i++) {
				Adventurer current_a = adventurers.get(i);
				current_a.move();
				
				// Check for collision on the wall of the map
				g.interact(current_a);
				
				// Check for collision with other
				for (int j = 0; j < adventurers.size(); j++) {
					// A player cannot interact with himself
					if (j == i) continue;
					adventurers.get(j).interact(current_a);
				}
				
				//
				Pair<Integer, Integer> coord = new Pair<>(
						Integer.valueOf(current_a.getPosI()),
						Integer.valueOf(current_a.getPosJ())
					);

				if (m.containsKey(coord)) {
					m.get(coord).interact(current_a);
				}
			}
		}
		 if (this.logDebug) System.out.println("""
				
				
				/**********************************/
				/        Simulation DONE !         /
				/**********************************/
				""");
		
		// We write the result of the simulation
		mapIO.writeGameFile(g, p.fst);
		
	}
	
	private void logGameTurn(Game g) {
		
		Map<Pair<Integer, Integer>, Adventurer> hm = new HashMap<>(); // HashMap of player and there position
		g.getAdventurers().stream().forEach(a -> hm.put(
				new Pair<>(
							Integer.valueOf(a.getPosI()),
							Integer.valueOf(a.getPosJ())
						), 
				a));

		Map<Pair<Integer,Integer>, MapElement> elements = g.getElements();
		StringBuilder sb = new StringBuilder();
		
		
		for (int i = 0; i < g.getHauteur() ; i++) {
			for (int j = 0; j < g.getLargeur(); j++) {
				Pair<Integer, Integer> p = new Pair<>(Integer.valueOf(i), Integer.valueOf(j));
				// On affiche le personnage sinon l'elem sinon "." pour un espace vide
				if (hm.containsKey(p)) {
					sb.append(String.format("%6s", hm.get(p).formatLog()));
				} else if (elements.containsKey(p)){
					sb.append(String.format("%6s", elements.get(p).formatLog()));
				} else {
					sb.append(String.format("%6s", "."));
				}
			}
			sb.append(String.format("%6s", "\n"));
		}
		sb.append(String.format("%6s", """
				
				+++++++++++++++\n
				"""));
		System.out.println(sb.toString()); ;
		
	}
	

	/*
	 * Public functions section
	 */
	
	public GameManager() {
		super();
		this.games = new Vector<Pair<String,Game>>();
		this.mapIO = new CarteInOutImp();
	}
	
	@Override
	public void loadGame(String filename) throws Exception{
		games.add(new Pair<>(filename, mapIO.readGameFile(filename)));
	}
	
	@Override
	public void simulateGame() throws Exception {
		if (this.games.size() == 0) throw new NoGameException("No game loaded. Impossible to simulate one");
		for (Pair<String, Game> p : this.games) {
				doSimulation(p);
		}
	}

	@Override
	public void toggleDebug() {
		this.logDebug = !logDebug;
	}

	@Override
	public void simulateGameParralel() throws RuntimeException {
		if (this.logDebug) toggleDebug(); // No debug when parallel processing because output is not in order
		this.games.parallelStream().forEach(p -> {
			try {
				doSimulation(p);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		});
		
	}
}
