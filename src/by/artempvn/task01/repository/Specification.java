package by.artempvn.task01.repository;

import java.util.function.Predicate;
import by.artempvn.task01.entity.Tetrahedron;

public interface Specification extends Predicate<Tetrahedron> {
}
