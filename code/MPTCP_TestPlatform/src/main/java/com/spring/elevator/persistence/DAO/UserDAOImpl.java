package com.spring.elevator.persistence.DAO;


import com.spring.elevator.persistence.DAO.IDAO.UserDAO;
import com.spring.elevator.persistence.common.AbstractDAO;
import com.spring.elevator.model.POJO.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AlexJIANG on 12/26/15.
 */
@Repository("UserDAO")
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public void deleteUserByName(String name) {

    }

    @Override
    public User findUserByName(String name) {
        Query query = getSession().getNamedQuery("user.finduser");
        query.setString("name",name);
        if(query.list().size()==0)
            return null;
        return (User)query.list().get(0);

    }

    @Override
    public void UpdateUser(User user) {

    }

}
