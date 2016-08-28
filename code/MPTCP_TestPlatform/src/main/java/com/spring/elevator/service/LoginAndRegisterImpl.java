package com.spring.elevator.service;

import com.spring.elevator.model.POJO.User;
import com.spring.elevator.persistence.DAO.IDAO.AddressDAO;
import com.spring.elevator.persistence.DAO.IDAO.UserDAO;
import com.spring.elevator.service.IService.LoginAndRegister;
import com.spring.elevator.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AlexJIANG on 12/29/15.
 */
@Service("loginAndRegister")
@Transactional
public class LoginAndRegisterImpl implements LoginAndRegister {

    @Autowired
    AddressDAO addressDAO;
    @Autowired
    UserDAO userDAO;

    @Autowired
    MD5 md5;

    @Override
    public boolean login(User user) {

        return false;
    }

    @Override
    public boolean register(User user) {
       // user.setId(null);
       // user.getAddress().setAddress_id(null);
        user.setPasswd(md5.getResult(user.getPasswd()));
        if(null==userDAO.findUserByName(user.getName())){
            addressDAO.saveAddress(user.getAddress());
            userDAO.saveUser(user);
            return true;
        }
        return false;
    }
}
