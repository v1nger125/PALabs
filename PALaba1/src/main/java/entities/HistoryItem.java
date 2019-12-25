package entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "history")
public class HistoryItem {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private LocalDate banDate;
    @Column
    private Integer duration;
    public  HistoryItem(){
    }
    public HistoryItem(User user, Integer duration){
        this.user = user;
        this.banDate = LocalDate.now();
        this.duration = duration;
    }
    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getBanDate() {
        return this.banDate;
    }

    public void setBanDate(LocalDate banDate) {
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
