package Application.controller;

import Application.entities.HistoryList;
import Application.entities.User;
import Application.entities.UserList;
import Application.services.HistoryService;
import Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = {"/rest", "/rest/list"}, method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserList> getList(){
        UserList users = new UserList(userService.findAll());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @RequestMapping(value = {"/rest/history"}, method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HistoryList> getHistory(){
        HistoryList history = new HistoryList(historyService.findAll());
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
    @RequestMapping(value = {"/rest/add"}, method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> add(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = {"/rest/ban"}, method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> ban(@RequestBody User user) {
        userService.ban(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = {"/rest/unBan"}, method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> unBan(@RequestBody User user) {
        userService.unBan(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(value = {"/xslt/list"}, method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getListXslt(){
        Resource resource = resourceLoader.getResource("classpath:xsl/list.xslt");
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = {"/xslt/history"}, method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getHistoryXslt(){
        Resource resource = resourceLoader.getResource("classpath:xsl/history.xslt");
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

}
