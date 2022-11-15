package test.io;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import data.Mountain;
import data.Treasure;
import utils.Pair;

public class MapElemArgProvider implements ArgumentsProvider {


	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return Stream.of(
					Arguments.of(new Pair<>("M - 1 - 2", new Mountain(2, 1))),
					Arguments.of(new Pair<>("T - 199 - 2 - 2", new Treasure(2, 199, 2))),
					Arguments.of(new Pair<>("T - 0 - 0 - 1", new Treasure(0, 0, 1))),
					Arguments.of(new Pair<>("M - 1 - 3", new Mountain(3, 1)))
				);
	}

}
