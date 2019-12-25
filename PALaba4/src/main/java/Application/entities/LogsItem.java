package Application.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="logs")
public class LogsItem implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "action_id")
    private Long id;
    @Column
    private String actionType;
    @Column
    private String actionObject;
    @Column
    private String actionValue;
    public LogsItem(){

    }
    public LogsItem(String actionType,String actionObject, String actionValue){
        this.actionType = actionType;
        this.actionValue = actionValue;
        this.actionObject = actionObject;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public String getActionObject() {
        return actionObject;
    }

    public void setActionObject(String actionObject) {
        this.actionObject = actionObject;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
