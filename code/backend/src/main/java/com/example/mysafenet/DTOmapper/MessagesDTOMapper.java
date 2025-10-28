package com.example.mysafenet.DTOmapper;

import com.example.mysafenet.dto.MessagesDTO;
import com.example.mysafenet.entity.Messages;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Function;

@Service
public class MessagesDTOMapper implements Function<Messages, MessagesDTO> {
    @Override
    public MessagesDTO apply(Messages messages){
        return new MessagesDTO(messages.getId(),messages.getChat(),messages.getSender(),messages.getContent(),messages.isRead(), messages.isDeleted(), messages.isEdited(), messages.isSent(), messages.isDelivered(),messages.isSeen(),messages.getSentAt(),messages.getDeliveredAt(),messages.getSentAt(),messages.getEditedAt(),messages.getDeletedAt(),messages.getMessageType());
    }
}
