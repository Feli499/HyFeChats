package de.feli490.hytale.hyfechats.data.properties;

public interface HyFeProperty<T> {

    String getKey();

    T getValue();

    void setValue(T value);

    boolean hasValue();
}
