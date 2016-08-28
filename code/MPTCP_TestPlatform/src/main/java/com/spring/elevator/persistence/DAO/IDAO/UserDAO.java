package com.spring.elevator.persistence.DAO.IDAO;

import com.spring.elevator.model.POJO.User;

import java.util.List;

/**
 * Created by AlexJIANG on 12/26/15.
 */
public interface UserDAO {
    public void saveUser(User user);
    public List<User> findAllUsers();
    public void deleteUserByName(String name);
    public User findUserByName(String name);
    public void UpdateUser(User user);
}
