package de.feli490.hytale.hyfechats.data.properties;

public class DoubleHyFeProperty extends AbstractHyFeProperty<Double> {

    public DoubleHyFeProperty(String key, Double defaultValue) {
        super(key, defaultValue);
    }

    @Override
    public void setValueFromString(String value) {
        setValue(Double.parseDouble(value));
    }
}
