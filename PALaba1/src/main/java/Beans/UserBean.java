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
public class UserBean {
    @PersistenceContext(name = "myUnit")
    private EntityManager em;

    private static final String findAll = "SELECT u FROM User u";
    public User add(User user){
        return em.merge(user);
    }
    public User get(Long id){
        return em.find(User.class, id);
    }
    public void update(User user){
        add(user);
    }
    public HistoryItem banUser(User user, Integer duration){
        if(user.getBanChecker() == 1) return null;
        user.setBanChecker(1);
        user.setBanNumber(user.getBanNumber() + 1);
        update(user);
        HistoryItem item = new HistoryItem(user, duration);
        return em.merge(item);
    }

    public void unBanUser(User user){
        if(user.getBanChecker() == 0) return;
        user.setBanChecker(0);
        update(user);
    }
    public List<User> getAll(){
        TypedQuery<User> namedQuery = em.createQuery(findAll, User.class);
        return namedQuery.getResultList();
    }
}
