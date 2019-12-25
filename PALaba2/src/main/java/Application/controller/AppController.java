package Application.controller;

import Application.entities.HistoryItem;
import Application.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import Application.services.HistoryService;
import Application.services.UserService;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserService userService;
    @Autowired
    private HistoryService historyService;
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String showList(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "list";
    }
    @RequestMapping(value = {"/history"}, method = RequestMethod.GET)
    public String showHistory(Model model){
        List<HistoryItem> history = historyService.findAll();
        model.addAttribute("history", history);
        return "history";
    }
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAdd(Model model){
        return "add";
    }
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addUser(Model model, @RequestParam("nickname") String nickname){
        User user = new User(nickname);
        userService.add(user);
        return showList(model);
    }
    @RequestMapping(value = {"/ban"}, method = RequestMethod.GET)
    public String banUser(Model model, @RequestParam("id") String id) {
        User user = userService.findById(Long.parseLong(id));
        if(user.getBanChecker() != 1){
            user.setBanChecker(1);
            user.setBanNumber(user.getBanNumber() + 1);
            HistoryItem historyItem = new HistoryItem(user, 60);
            userService.add(user);
            historyService.add(historyItem);
        }
        return showList(model);
    }
    @RequestMapping(value = {"/unBan"}, method = RequestMethod.GET)
    public String unBanUser(Model model, @RequestParam("id") String id) {
        User user = userService.findById(Long.parseLong(id));
        if(user.getBanChecker() != 0){
            user.setBanChecker(0);
            userService.add(user);
        }
        return showList(model);
    }
}
