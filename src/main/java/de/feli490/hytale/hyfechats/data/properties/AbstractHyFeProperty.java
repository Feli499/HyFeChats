package de.feli490.hytale.hyfechats.data.properties;

public abstract class AbstractHyFeProperty<T> implements HyFeProperty<T> {

    private final String key;
    private T value;

    protected AbstractHyFeProperty(String key, T defaultValue) {
        this.key = key;
        this.value = defaultValue;
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

    @Override
    public String getValueAsString() {
        return value.toString();
    }

    @Override
    public String toString() {
        return "AbstractHyFeProperty{" + "key='" + key + '\'' + ", value=" + value + '}';
    }
}
