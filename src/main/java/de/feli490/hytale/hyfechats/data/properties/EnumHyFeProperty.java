package de.feli490.hytale.hyfechats.data.properties;

public class EnumHyFeProperty<E extends Enum<E>> extends AbstractHyFeProperty<E> {

    public EnumHyFeProperty(String key, E value) {
        super(key, value);
    }

    public EnumHyFeProperty(String key) {
        this(key, null);
    }
}
