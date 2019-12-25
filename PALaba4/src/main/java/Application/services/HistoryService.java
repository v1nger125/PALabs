package Application.services;

import Application.entities.HistoryItem;
import Application.entities.LogsItem;
import Application.repositories.HistoryRepository;
import Application.repositories.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository repo;
    @Autowired
    private JmsTemplate template;
    public List<HistoryItem> findAll(){
        LogsItem logs = new LogsItem("findAll","history", "");
        template.send(new LogsMessageCreator(logs));
        return repo.findAll();
    }
}
