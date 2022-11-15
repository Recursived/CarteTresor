package io;

import data.Game;

public interface ICarteWriter {
	public void writeGameFile(Game g, String filename) throws Exception;
}
