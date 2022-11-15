package test.io;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import data.Adventurer;
import data.Orientation;

import utils.Pair;

public class AdventurerArgProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		
		return Stream.of(
				Arguments.of(new Pair<>("A - Jeanne - 2 - 3 - N - AAAGAADAGGA", new Adventurer("Jeanne", Orientation.North, 3, 2, "AAAGAADAGGA"))),
				Arguments.of(new Pair<>("A - Pierre - 0 - 0 - S - A", new Adventurer("Pierre", Orientation.South, 0, 0, "A"))),
				Arguments.of(new Pair<>("A - Bernard - 10 - 10 - E - GGGGAADDGGAA", new Adventurer("Bernard", Orientation.East, 10, 10, "GGGGAADDGGAA"))),
				Arguments.of(new Pair<>("A - Eva - 0 - 0 - O - AAAAAAAAAAA", new Adventurer("Eva", Orientation.West, 0, 0, "AAAAAAAAAAA")))
			);
	}

}
