package Application.controller;

import Application.entities.HistoryItem;
import Application.entities.User;
import Application.entities.UserList;
import Application.services.HistoryService;
import Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;

@Controller
public class AppController {
    @Autowired
    private UserService userService;
    @Autowired
    private HistoryService historyService;

    private static final String SERVER_URI = "http://localhost:8082/rest/";
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String showList(Model model) {
        String element = getHttp("list");
        Document document = getDocument(element);
        model.addAttribute("list", document);
        return "list";
    }
    @RequestMapping(value = {"/history"}, method = RequestMethod.GET)
    public String showHistory(Model model) {
        String element = getHttp("history");
        Document document = getDocument(element);
        model.addAttribute("history", document);
        return "history";
    }
    @RequestMapping(value = {"/ban"}, method = RequestMethod.GET)
    public ModelAndView ban(ModelMap model, @RequestParam("id") String id) {
        User user = userService.findById(Long.parseLong(id));
        postHttp("ban",user);
        return new ModelAndView("redirect:/list",model);
    }
    @RequestMapping(value = {"/unBan"}, method = RequestMethod.GET)
    public ModelAndView unBan(ModelMap model, @RequestParam("id") String id) {
        User user = userService.findById(Long.parseLong(id));
        postHttp("unBan",user);
        return new ModelAndView("redirect:/list",model);
    }
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public ModelAndView add(ModelMap model, @RequestParam("nickname") String nickname){
        User user = new User(nickname);
        postHttp("add", user);
        return new ModelAndView("redirect:/list", model);
    }

    public static String getHttp(String suffix){
        RestTemplate template = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_XML_VALUE);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        ResponseEntity<String> entity = template.exchange(URI.create(SERVER_URI + suffix), HttpMethod.GET, httpEntity, String.class);
        return entity.getBody();
    }

    public static String postHttp(String suffix, Object object){
        RestTemplate template = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(object, httpHeaders);
        ResponseEntity<String> entity = template.exchange(URI.create(SERVER_URI + suffix), HttpMethod.POST, httpEntity, String.class);
        return entity.getStatusCode().toString();
    }

    public static Document getDocument(String element) {
        Document document = null;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(element.getBytes()));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }
}
