package com.chat.chatapp.repository.Chatter;

import com.chat.chatapp.model.Chatter;

import java.util.List;
import java.util.Optional;

public interface chatterDAO {
//    public Optional<Chatter> findOneByPhoneNumber(String phoneNumber) ;
    public List<Chatter> SelectAllChetter();
    public String insert(Chatter chatter);
    public String delete(Long id);
}
