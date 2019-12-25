package Beans;

import entities.HistoryItem;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

@Stateless
public class HistoryBean {
    @PersistenceContext(name = "myUnit")
    private EntityManager em;
    private static final String findAll = "SELECT g FROM HistoryItem g";
    public HistoryItem add(HistoryItem item){
        return em.merge(item);
    }
    public List<HistoryItem> getAll(){
        TypedQuery<HistoryItem> namedQuery = em.createQuery(findAll, HistoryItem.class);
        return namedQuery.getResultList();
    }
}
