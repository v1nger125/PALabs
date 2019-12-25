package Application.entities;


import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "history")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryItem {
    @Id
    @GeneratedValue
    @XmlElement
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @XmlElement
    private User user;
    @Column
    @XmlElement
    @XmlSchemaType(name="date")
    private Date banDate;
    @Column
    @XmlElement
    private Integer duration;
    public  HistoryItem(){
    }
    public HistoryItem(User user, Integer duration){
        this.user = user;
        this.banDate = new Date();
        this.duration = duration;
    }
    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getBanDate() {
        return this.banDate;
    }

    public void setBanDate(Date banDate) {
        this.banDate = banDate;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
