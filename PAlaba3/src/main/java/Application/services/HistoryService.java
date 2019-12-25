package Application.services;

import Application.entities.HistoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Application.repositories.HistoryRepository;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository repo;

    public List<HistoryItem> findAll(){
        return repo.findAll();
    }

}
