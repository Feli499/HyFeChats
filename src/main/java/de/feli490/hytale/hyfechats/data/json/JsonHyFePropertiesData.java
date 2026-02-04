package de.feli490.hytale.hyfechats.data.json;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.array.ArrayCodec;
import de.feli490.hytale.hyfechats.data.properties.HyFeProperty;

public class JsonHyFePropertiesData {
    public final static Codec<JsonHyFePropertiesData> CODEC = BuilderCodec.builder(JsonHyFePropertiesData.class,
                                                                                   JsonHyFePropertiesData::new)
                                                                    .addField(new KeyedCodec<>("Key", Codec.STRING),
                                                                              JsonHyFePropertiesData::setKey,
                                                                              JsonHyFePropertiesData::getKey)
                                                                    .addField(new KeyedCodec<>("Value", Codec.STRING),
                                                                              JsonHyFePropertiesData::setValue,
                                                                              JsonHyFePropertiesData::getValue)
                                                                    .build();

    public final static ArrayCodec<JsonHyFePropertiesData> ARRAY_CODEC = new ArrayCodec<>(CODEC,
                                                                                          JsonHyFePropertiesData[]::new,
                                                                                          JsonHyFePropertiesData::new);

    private String key;
    private String value;

    public JsonHyFePropertiesData() {}

    public JsonHyFePropertiesData(HyFeProperty<?> property) {
        key = property.getKey();
        value = property.getValueAsString();
    }

    public String getKey() {
        return key;
    }

    private void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }
}
