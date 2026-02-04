package de.feli490.hytale.hyfechats.data.properties;

public class IntegerHyFeProperty extends AbstractHyFeProperty<Integer> {

    public IntegerHyFeProperty(String key, Integer defaultValue) {
        super(key, defaultValue);
    }

    @Override
    public void setValueFromString(String value) {
        setValue(Integer.parseInt(value));
    }
}
