package de.feli490.hytale.hyfechats.data.properties;

public class StringHyFeProperty extends AbstractHyFeProperty<String> {

    public StringHyFeProperty(String key, String value) {
        super(key, value);
    }

    public StringHyFeProperty(String key) {
        this(key, null);
    }
}
