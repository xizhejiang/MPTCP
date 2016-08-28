package com.spring.elevator.persistence.DAO.IDAO;

import com.spring.elevator.model.POJO.PageData;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

/**
 * Created by AlexJIANG on 7/11/16.
 */
public interface PageDataDAO {
    public void savePageData(PageData pageData);
    public List<PageData> getPageDataList();
}
