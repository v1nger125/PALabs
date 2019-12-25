package Application.services;

import Application.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Application.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
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
}
