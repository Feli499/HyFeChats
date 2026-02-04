package de.feli490.hytale.hyfechats.data.properties;

public class LongHyFeProperty extends AbstractHyFeProperty<Long> {

    public LongHyFeProperty(String key, Long defaultValue) {
        super(key, defaultValue);
    }

    @Override
    public void setValueFromString(String value) {
        setValue(Long.parseLong(value));
    }
}
