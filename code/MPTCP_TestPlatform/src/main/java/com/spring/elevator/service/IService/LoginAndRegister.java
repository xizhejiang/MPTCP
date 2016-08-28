package com.spring.elevator.service.IService;

import com.spring.elevator.model.POJO.User;

/**
 * Created by AlexJIANG on 12/29/15.
 */
public interface LoginAndRegister {
    boolean login(User user);
    boolean register(User user);

}
