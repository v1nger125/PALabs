package Application.services;

import Application.entities.HistoryItem;
import Application.entities.User;
import Application.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Application.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private HistoryRepository historyRepo;
    public List<User> findAll(){
        return repo.findAll();
    }
    public User findById(Long id){
        return repo.findById(id).get();
    }
    public User add(User user){
        repo.save(user);
        return user;
    }
    public User ban(Long id){
        User user = findById(id);
        if (user.getBanChecker() == 0) {
            HistoryItem historyItem = new HistoryItem(user, 60);
            user.setBanChecker(1);
            user.setBanNumber(user.getBanNumber() + 1);
            repo.save(user);
            historyRepo.save(historyItem);
        }
        return user;
    }
    public User unBan(Long id){
        User user = findById(id);
        if (user.getBanChecker() == 1) {
            user.setBanChecker(0);
            repo.save(user);
        }
        return user;
    }
}
