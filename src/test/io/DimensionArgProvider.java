package test.io;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import utils.Pair;

public class DimensionArgProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return Stream.of(
					Arguments.of(new Pair<String, Pair<Integer, Integer>>("C - 3 - 4", new Pair<>(Integer.valueOf("3"), Integer.valueOf("4")))),
					Arguments.of(new Pair<String, Pair<Integer, Integer>>("C - 31 - 4", new Pair<>(Integer.valueOf("31"), Integer.valueOf("4")))),
					Arguments.of(new Pair<String, Pair<Integer, Integer>>("C - 1 - 10", new Pair<>(Integer.valueOf("1"), Integer.valueOf("10")))),
					Arguments.of(new Pair<String, Pair<Integer, Integer>>("C - 5 - 5", new Pair<>(Integer.valueOf("5"), Integer.valueOf("5"))))
				);
	}

}
