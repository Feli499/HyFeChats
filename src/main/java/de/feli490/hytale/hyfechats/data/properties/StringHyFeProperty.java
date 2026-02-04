package de.feli490.hytale.hyfechats.data.properties;

public class StringHyFeProperty extends AbstractHyFeProperty<String> {

    public StringHyFeProperty(String key, String defaultValue) {
        super(key, defaultValue);
    }

    @Override
    public void setValueFromString(String value) {
        setValue(value);
    }

    @Override
    public String getValueAsString() {
        return getValue();
    }
}
