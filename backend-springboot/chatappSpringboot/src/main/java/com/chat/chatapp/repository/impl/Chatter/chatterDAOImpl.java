package com.chat.chatapp.repository.impl.Chatter;

import com.chat.chatapp.model.Chatter;
import com.chat.chatapp.model.admin;
import com.chat.chatapp.repository.Chatter.chatterDAO;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;


import java.util.List;
import java.util.Optional;
@Repository
public class chatterDAOImpl implements chatterDAO {

    @Autowired
    private final SessionFactory sessionFactory;
    private final EntityManager entityManager;

    public chatterDAOImpl(SessionFactory sessionFactory,
                          EntityManager entityManager) {
        this.sessionFactory = sessionFactory;
        this.entityManager = entityManager;
    }
//    @Override
//    @Transactional
//    public Optional<Chatter> findOneByPhoneNumber(String phoneNumber) {
//      Chatter chatter = (Chatter) sessionFactory.getCurrentSession().createQuery("from Chatter where phoneNumber = :phoneNumber", Chatter.class);
//      return Optional.ofNullable(chatter);
//    }
    @Override
    @Transactional
    public List<Chatter> SelectAllChetter() {
        Session session = entityManager.unwrap(Session.class);

        List<Chatter> result = session.createQuery("from Chatter ", Chatter.class).getResultList();

        return result;
    }

    @Override
    @Transactional
    public String insert(Chatter chatter)
    {
        Session session = entityManager.unwrap(Session.class);
        session.save(chatter);
        return "success";
    }
    @Override
    @Transactional
    public String delete(Long id)
    {
        Session session = entityManager.unwrap(Session.class);
        Chatter chatter = session.get(Chatter.class, id);
        if (chatter != null) {
            session.delete(chatter);
        }
        return "success";
    }
}
