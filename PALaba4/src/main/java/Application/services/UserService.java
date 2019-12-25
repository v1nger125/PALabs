package Application.services;

import Application.entities.EmailHistory;
import Application.entities.HistoryItem;
import Application.entities.LogsItem;
import Application.entities.User;
import Application.repositories.HistoryRepository;
import Application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private HistoryRepository historyRepo;
    @Autowired
    private JmsTemplate template;
    public List<User> findAll(){
        LogsItem logs = new LogsItem("findAll","users","");
        template.send(new LogsMessageCreator(logs));
        return repo.findAll();
    }
    public User findById(Long id){
        LogsItem logs = new LogsItem("findById","users", id.toString());
        template.send(new LogsMessageCreator(logs));
        return repo.findById(id).get();
    }
    public User add(User user){
        repo.save(user);
        LogsItem logs = new LogsItem("add","users", user.getId().toString());
        template.send(new LogsMessageCreator(logs));
        return user;
    }
    public User ban(Long id){
        User user = findById(id);
        if (user.getBanChecker() == 0) {
            if (user.getNickname().equals("email")){
                template.send(new EmailMessageCreator(new EmailHistory("Dima11609@mail.ru", "You have been banned")));
            }
            HistoryItem historyItem = new HistoryItem(user, 60);
            user.setBanChecker(1);
            user.setBanNumber(user.getBanNumber() + 1);
            repo.save(user);
            historyRepo.save(historyItem);
            LogsItem logs = new LogsItem("ban","users", user.getId().toString());
            template.send(new LogsMessageCreator(logs));
        }
        else{
            LogsItem logs = new LogsItem("ban","users", user.getId().toString() + "skipped");
            template.send(new LogsMessageCreator(logs));
        }
        return user;
    }
    public User unBan(Long id){
        User user = findById(id);
        if (user.getBanChecker() == 1) {
            user.setBanChecker(0);
            repo.save(user);
            LogsItem logs = new LogsItem("unban","users", user.getId().toString());
            template.send(new LogsMessageCreator(logs));
        }
        else {
            LogsItem logs = new LogsItem("unban","users", user.getId().toString() + "skipped");
            template.send(new LogsMessageCreator(logs));
        }
        return user;
    }
}
