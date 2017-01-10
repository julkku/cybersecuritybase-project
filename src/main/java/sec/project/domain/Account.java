package sec.project.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by julkku on 1/10/17.
 */
@Entity
public class Account extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String username;

    private String password;

    private Integer funds;

    @OneToMany(mappedBy = "to")
    private List<Payment> receivedPayments;

    @OneToMany(mappedBy = "from")
    private List<Payment> sentPayments;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }
}

