package io;


import data.Game;


public interface ICarteReader {
	public Game readGameFile(String filename) throws Exception;
}
