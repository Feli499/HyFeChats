package de.feli490.hytale.hyfechats.data.properties;

public class EnumHyFeProperty<E extends Enum<E>> extends AbstractHyFeProperty<E> {

    private final Class<E> declaringClass;

    public EnumHyFeProperty(String key, E defaultValue) {
        super(key, defaultValue);
        declaringClass = defaultValue.getDeclaringClass();
    }

    @Override
    public void setValueFromString(String value) {
        setValue(Enum.valueOf(declaringClass, value));
    }
}
