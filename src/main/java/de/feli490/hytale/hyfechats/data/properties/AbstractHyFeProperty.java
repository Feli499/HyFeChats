package de.feli490.hytale.hyfechats.data.properties;

public class AbstractHyFeProperty<T> implements HyFeProperty<T> {

    private final String key;
    private T value;

    protected AbstractHyFeProperty(String key, T value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean hasValue() {
        return value != null;
    }
}
