package org.example;

// This is the common interface that all concrete product types will implement.
// The factory will return objects of this interface type, keeping the client decoupled.
public interface Logistics {
    void send();
}
