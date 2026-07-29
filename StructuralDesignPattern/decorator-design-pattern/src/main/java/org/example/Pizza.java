package org.example;

/**
 * Component — the base interface that both concrete components and decorators implement.
 * Defines the contract ({@code getDescription} + {@code getCost}) that decorators use
 * to transparently wrap any Pizza object, whether it's a plain base pizza or another decorator.
 */
public interface Pizza {
    String getDescription();
    double getCost();
}
