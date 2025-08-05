package com.chat.chatapp.repository.impl;

import com.chat.chatapp.model.admin;
import com.chat.chatapp.repository.adminDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class adminDAOImpl implements adminDAO
{
    @Autowired
    private final SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public adminDAOImpl(SessionFactory sessionFactory,
                        EntityManager entityManager
    ) {
        this.sessionFactory = sessionFactory;
        this.entityManager = entityManager;
    }

//    @Override
//    public Optional<admin> findOneByEmail() {
//        sessionFactory.getCurrentSession();
//        Query<admin> query = sessionFactory.getCurrentSession().createQuery("from admin where email = :email", admin.class);
//        query.setParameter("email", "<EMAIL>");
//        admin email = query.getSingleResult();
//        return Optional.ofNullable(email);
//    }

    @Transactional
    @Override
    public Optional<admin> findOneByEmail(String email) {
        try {
            Session session = entityManager.unwrap(Session.class);
            Query<admin> query = session.createQuery("from admin where email = :email", admin.class);
            query.setParameter("email", email);
            admin admin = query.getSingleResult();
            return Optional.of(admin);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    @Transactional
    @Override
    public String insert(admin admin)
    {
        Session session = entityManager.unwrap(Session.class);
        session.save(admin);
        return "success";
    }

    @Transactional
    @Override
    public List<admin> select() {
        Session session = entityManager.unwrap(Session.class);

        List<admin> result = session.createQuery("from admin ", admin.class).getResultList();

        return result;
    }
    @Transactional
    @Override
    public String DeleteAdminById(Long id)
    {
        Session session = entityManager.unwrap(Session.class);
        admin admin = session.get(admin.class, id);
        if (admin != null) {
            session.delete(admin);
            return "success";
        }else
            return "no admin found";

    }
}
