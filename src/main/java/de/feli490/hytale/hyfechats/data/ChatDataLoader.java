package de.feli490.hytale.hyfechats.data;

import java.io.IOException;
import java.util.Collection;

public interface ChatDataLoader {

    Collection<ChatData> loadChats() throws IOException;

}
