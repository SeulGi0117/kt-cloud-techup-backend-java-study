package study.seulgi.java.ch01.basics.optional;

import java.util.Map;
import java.util.Optional;

public class MemoryRepository {
	private final Map<String, Person> personmap = Map.of(
		"철호", new Person("철호"),
		"영희", new Person("영희")
	);

	public Optional<Person> findByName(String name) {
		return Optional.ofNullable(personmap.get(name));
	}
}