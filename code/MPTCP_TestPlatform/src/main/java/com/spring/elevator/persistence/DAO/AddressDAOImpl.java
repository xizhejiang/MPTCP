package com.spring.elevator.persistence.DAO;

import com.spring.elevator.model.POJO.Address;
import com.spring.elevator.persistence.DAO.IDAO.AddressDAO;
import com.spring.elevator.persistence.common.AbstractDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by AlexJIANG on 12/27/15.
 */
@Repository("AddressDAO")
public class AddressDAOImpl extends AbstractDAO implements AddressDAO {
    @Override
    public void saveAddress(Address address) {
        persist(address);
    }
}
