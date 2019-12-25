package Application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @XmlElement
    private Long id;
    @Column
    @XmlElement
    private String nickname;
    @Column
    @XmlElement
    @XmlSchemaType(name="date")
    private Date registerDate;
    @Column
    @XmlElement
    private Integer banNumber;
    @Column
    @XmlElement
    private Integer banChecker;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    @XmlTransient
    private List<HistoryItem> history;

    public User(){
    }
    public User(String nickname){
        this.nickname = nickname;
        this.registerDate = new Date();
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

    public Date getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(Date registerDate) {
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

