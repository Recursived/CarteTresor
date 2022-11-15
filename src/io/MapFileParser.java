package io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Adventurer;
import data.MapElement;
import data.Mountain;
import data.Orientation;
import data.Treasure;
import utils.Pair;



public final class MapFileParser {
	private final static String grammar = "^(C - \\d+ - \\d+)$|^(M - \\d+ - \\d+)$|^(T - \\d+ - \\d+ - \\d+)$|^(A - [a-zA-Z]+ - \\d+ - \\d+ - [NSEO] - [AGD]+)$|^(A - [a-zA-Z]+ - \\d+ - \\d+ - [NSEO] - \\d+)$|^(#.*)$";
	
	
	private static Mountain parseMountain(String s) {
		String[] e = s.split(" - ");
		int pos_j = Integer.parseInt(e[1]), pos_i = Integer.parseInt(e[2]);
		return new Mountain(pos_i, pos_j);
	}
	
	private static Treasure parseTreasure(String s) {
		String[] e = s.split(" - ");
		int pos_j = Integer.parseInt(e[1]), 
				pos_i = Integer.parseInt(e[2]),
				treasureCount = Integer.parseInt(e[3]);
		return new Treasure(pos_i, pos_j, treasureCount);
	}
	
	// Public functions
	
	public static boolean isValidGrammar(String s) {
		Pattern pattern = Pattern.compile(grammar, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(s);
		return matcher.find();
	}
	
	public static Adventurer parseAdventurer(String s) {
		String[] e = s.split(" - ");
		int pos_j = Integer.parseInt(e[2]), pos_i = Integer.parseInt(e[3]);
		return new Adventurer(e[1], Orientation.getOrientation(e[4]), pos_i, pos_j, e[5]);
	}
	
	
	public static MapElement parseMapElement(String line) {
		MapElement me = null;
		switch(line.charAt(0)) {
			case 'T':
				me = MapFileParser.parseTreasure(line);
				break;
			case 'M':
				me = MapFileParser.parseMountain(line);
				break;
		}
		return me;
	}
	
	
	public static Pair<Integer, Integer> parseGameDimensions(String s){
		String[] e = s.split(" - ");
		return new Pair<>(Integer.valueOf(e[1]), Integer.valueOf(e[2]));
		
	}
}
