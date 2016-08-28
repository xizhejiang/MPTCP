package com.spring.elevator.service;

import com.spring.elevator.model.POJO.User;
import com.spring.elevator.persistence.DAO.IDAO.AddressDAO;
import com.spring.elevator.persistence.DAO.IDAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AlexJIANG on 12/26/15.
 */
@Service("UserService")
@Transactional
public class UserServiceImpl implements com.spring.elevator.service.IService.UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }
}
