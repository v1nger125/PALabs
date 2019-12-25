package Application.repositories;

import Application.entities.LogsItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogsRepository extends CrudRepository<LogsItem, Long> {
    List<LogsItem> findAll();
}
