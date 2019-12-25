package Application.repositories;

import Application.entities.HistoryItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<HistoryItem, Long>{
    List<HistoryItem> findAll();
}
