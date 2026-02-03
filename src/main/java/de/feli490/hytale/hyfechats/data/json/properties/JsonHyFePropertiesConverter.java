package de.feli490.hytale.hyfechats.data.json.properties;

import de.feli490.hytale.hyfechats.data.properties.DoubleHyFeProperty;
import de.feli490.hytale.hyfechats.data.properties.HyFeProperty;
import de.feli490.hytale.hyfechats.data.properties.IntegerHyFeProperty;
import de.feli490.hytale.hyfechats.data.properties.StringHyFeProperty;

public class JsonHyFePropertiesConverter {

    public JsonHyFePropertiesData toJson(HyFeProperty<?> hyFeProperty) {
        return new JsonHyFePropertiesData(hyFeProperty);
    }

    public HyFeProperty<?> fromJson(JsonHyFePropertiesData jsonHyFePropertiesData) {

        String propertyClass = jsonHyFePropertiesData.getPropertyClass();

        switch (propertyClass) {
            case "DoubleHyFeProperty":
                return new DoubleHyFeProperty(jsonHyFePropertiesData.getKey(), Double.parseDouble(jsonHyFePropertiesData.getValue()));
            case "IntegerHyFeProperty":
                return new IntegerHyFeProperty(jsonHyFePropertiesData.getKey(), Integer.parseInt(jsonHyFePropertiesData.getValue()));
            case "StringHyFeProperty":
                return new StringHyFeProperty(jsonHyFePropertiesData.getKey(), jsonHyFePropertiesData.getValue());
            default:
                throw new IllegalStateException("Unknown HyFeProperty Class: " + propertyClass);
        }

    }
}
