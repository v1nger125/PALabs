package Application.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @Column
    private String nickname;
    @Column
    private LocalDate registerDate;
    @Column
    private Integer banNumber;
    @Column
    private Integer banChecker;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<HistoryItem> history;

    public User(){
    }
    public User(String nickname){
        this.nickname = nickname;
        this.registerDate = LocalDate.now();
        this.banChecker = 0;
        this.banNumber = 0;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getBanNumber() {
        return this.banNumber;
    }

    public void setBanNumber(Integer banNumber) {
        this.banNumber = banNumber;
    }

    public Integer getBanChecker() {
        return this.banChecker;
    }

    public void setBanChecker(Integer banChecker) {
        this.banChecker = banChecker;
    }

    public List<HistoryItem> getHistory() {
        return this.history;
    }

    public void setHistory(List<HistoryItem> history) {
        this.history = history;
    }
}

