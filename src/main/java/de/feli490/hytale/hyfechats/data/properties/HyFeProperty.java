package de.feli490.hytale.hyfechats.data.properties;

interface HyFeProperty<T> {

    String getKey();

    T getValue();

    void setValue(T value);

    boolean hasValue();
}
