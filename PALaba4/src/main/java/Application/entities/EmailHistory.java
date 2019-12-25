package Application.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "emailhistory")
public class EmailHistory implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String condition;

    public EmailHistory(){}

    public EmailHistory(String email, String condition){
        this.email = email;
        this.condition = condition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
