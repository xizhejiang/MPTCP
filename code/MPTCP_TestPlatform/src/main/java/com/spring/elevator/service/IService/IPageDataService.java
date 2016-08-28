package com.spring.elevator.service.IService;

import com.spring.elevator.model.POJO.PageData;

import java.util.List;

/**
 * Created by AlexJIANG on 7/11/16.
 */
public interface IPageDataService {
    public void save(PageData pd);
    public List<PageData> getAllPageData();

}
