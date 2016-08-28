package com.spring.elevator.persistence.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * Created by AlexJIANG on 12/26/15.
 */

public abstract class AbstractDAO {

        @Autowired
        private SessionFactory sessionFactory;

        protected Session getSession() {
            return sessionFactory.getCurrentSession();
        }

        public void persist(Object entity) {
            getSession().persist(entity);
        }

        public void delete(Object entity) {
            getSession().delete(entity);
        }

}
