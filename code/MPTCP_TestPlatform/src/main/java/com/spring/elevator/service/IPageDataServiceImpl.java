package com.spring.elevator.service;

import com.spring.elevator.model.POJO.PageData;
import com.spring.elevator.persistence.DAO.IDAO.PageDataDAO;
import com.spring.elevator.service.IService.IPageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AlexJIANG on 7/11/16.
 */
@Service("PageDataService")
@Transactional
public class IPageDataServiceImpl implements IPageDataService {
    @Autowired
    PageDataDAO pdDAO;
    @Override
    public void save(PageData pd) {
        if(pd!=null){
            pdDAO.savePageData(pd);
        }
    }

    @Override
    public List<PageData> getAllPageData() {
        return pdDAO.getPageDataList();
    }
}
