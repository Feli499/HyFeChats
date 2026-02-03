package de.feli490.hytale.hyfechats.data.properties;

public class IntegerHyFeProperty extends AbstractHyFeProperty<Integer> {

    public IntegerHyFeProperty(String key, Integer value) {
        super(key, value);
    }

    public IntegerHyFeProperty(String key) {
        this(key, null);
    }
}
