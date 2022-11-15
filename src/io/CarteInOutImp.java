package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import data.Game;
import data.MapElement;
import data.Adventurer;

import exception.InvalidFormatException;
import exception.InvalidGameException;
import utils.Pair;

public class CarteInOutImp implements ICarteIO{
	
	/*
	 * Reusable method that closes a scanner and throws an exception
	 * when predicate is true
	 */
	private void closeOnException(boolean predicate, Scanner myReader, Exception e) throws Exception {
		if (predicate) {
			myReader.close();
			throw e;
		}
	}
	

	@Override
	public Game readGameFile(String filename) throws Exception {
		File f = new File(filename);
		Scanner myReader = new Scanner(f);
		int lineCount = 1;
		Vector<Adventurer> adventurers = new Vector<>();
		Set<MapElement> elems = new HashSet<>();
		Game g = new Game();
		while (myReader.hasNextLine()) {
		  String line = myReader.nextLine();
		  // If line is invalid grammar we throw an exception
		  closeOnException(
				  !MapFileParser.isValidGrammar(line), 
				  myReader, 
				  new InvalidFormatException("Line " + lineCount + " is not valid grammar for the Map file format")
				  );

		  
		  switch(line.charAt(0)) {
		  	case 'C':
			  Pair<Integer, Integer> p = MapFileParser.parseGameDimensions(line);
			  g.setLargeur(p.fst.intValue());
			  g.setHauteur(p.snd.intValue());
			  break;
		  	case 'A':
		  		adventurers.add(MapFileParser.parseAdventurer(line));
		  		break;
		  	case 'M':
		  	case 'T':
		  		closeOnException(
		  				!elems.add(MapFileParser.parseMapElement(line)), 
		  				myReader, 
		  				new InvalidFormatException("Elem at line " + lineCount + " is a duplicate. Check for duplicate coordinates in the file")
		  				);
		  		break;
		  	
		  	default:
		  		break;
		  }
		  lineCount++;
		}
		
		// If game valid nothing happens else an exception is thrown and should be dealt with
		int i = g.getHauteur(), j = g.getLargeur();
		
		boolean predicate = (i == 0 || j == 0) || // Board should have valid dimensions
				(!adventurers.isEmpty() 
						&& adventurers.stream().anyMatch(e -> e.getPosI() >= i || e.getPosJ() >= j )) || // adventurer should be placed on the board
				(!elems.isEmpty() 
						&& elems.stream().anyMatch(e -> e.getPosI() >= i || e.getPosJ() >= j )) || // MapElement should be placed on the board
				(!adventurers.stream()
						.map(a -> new Pair<Integer, Integer>(a.getPosI(), a.getPosJ()))
						.allMatch(new HashSet<>()::add)); // Adventurer are all on a unique cell
		
		closeOnException(predicate, myReader, new InvalidGameException("State of the game is invalid. Check coordinates in the file"));
		g.setAdventurers(adventurers);
		Map<Pair<Integer, Integer>, MapElement> m = new HashMap<>();
		elems.forEach(e -> 
			m.put(new Pair<>(Integer.valueOf(e.getPosI()), Integer.valueOf(e.getPosJ())), e));
		g.setElements(m);
		return g;
	}


	@Override
	public void writeGameFile(Game g, String filename) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".simulated_result"));
        writer.write(g.toString());
        writer.newLine();
        
        // We write the map elements into to the file
        for (MapElement me : g.getElements().values()) {
        	String output = me.toString();
        	if (output == "") continue;
        	
        	writer.write(output);
        	writer.newLine();
        }
        
        // We write the adventurers into the file
        List<Adventurer> lst = g.getAdventurers();
        int size = lst.size();
        for(ListIterator<Adventurer> i = lst.listIterator(); i.hasNext(); ) {
        	if (i.nextIndex() + 1 < size) {
        		writer.write(i.next().toString());
        		writer.newLine();
        	} else {
        		writer.write(i.next().toString());
        	}
        }
        

        // Closing the file writing connection
        writer.close();
		
	}
}
