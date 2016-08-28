package com.spring.elevator.service;

import com.spring.elevator.model.POJO.Address;
import com.spring.elevator.persistence.DAO.IDAO.AddressDAO;
import com.spring.elevator.service.IService.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AddressService")
@Transactional
public class AddressImpl implements AddressService {
    @Autowired
    AddressDAO addressDAO;
    @Override
    public void saveAddress(Address address) {
        addressDAO.saveAddress(address);

    }
}
