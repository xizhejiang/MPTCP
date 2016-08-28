package com.spring.elevator.persistence.DAO;

import com.spring.elevator.model.POJO.PageData;
import com.spring.elevator.persistence.DAO.IDAO.PageDataDAO;
import com.spring.elevator.persistence.common.AbstractDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AlexJIANG on 7/11/16.
 */
@Repository("PageDataDAO")
public class PageDataDAOImpl extends AbstractDAO implements PageDataDAO {
    @Override
    public void savePageData(PageData pageData) {
        persist(pageData);
    }

    @Override
    public List<PageData> getPageDataList() {
        Query query = getSession().getNamedQuery("pagedata.findall");
        if(query.list().size() == 0){
            return null;
        }
        return (List<PageData>)query.list();
    }
}
