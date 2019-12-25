package Application.repositories;

import Application.entities.EmailHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EmailHistoryRepository extends CrudRepository<EmailHistory, UUID> {

}
