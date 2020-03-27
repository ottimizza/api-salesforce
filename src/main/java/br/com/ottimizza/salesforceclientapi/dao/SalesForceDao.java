package br.com.ottimizza.salesforceclientapi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;

@Component("salesForceDao")
public class SalesForceDao {

    @PersistenceContext
    private EntityManager em;
    
    public List executeQueryOnMirroredDatabase(String querySql){
        Query query = em.createNativeQuery(querySql);
        return query.getResultList();
    }
}
