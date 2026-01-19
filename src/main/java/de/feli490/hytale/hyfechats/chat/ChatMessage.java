package de.feli490.hytale.hyfechats.chat;

import java.util.UUID;

public record ChatMessage(UUID id, UUID senderId, String message, Long timestamp) {
    
}
