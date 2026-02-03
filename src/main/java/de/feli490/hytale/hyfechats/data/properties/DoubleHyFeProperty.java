package de.feli490.hytale.hyfechats.data.properties;

public class DoubleHyFeProperty extends AbstractHyFeProperty<Double> {

    public DoubleHyFeProperty(String key, Double value) {
        super(key, value);
    }

    public DoubleHyFeProperty(String key) {
        this(key, null);
    }
}
