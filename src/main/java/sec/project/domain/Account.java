package sec.project.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "to")
    private List<Payment> receivedPayments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "from")
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

    public List<Payment> getReceivedPayments() {
        return receivedPayments;
    }

    public void setReceivedPayments(List<Payment> receivedPayments) {
        this.receivedPayments = receivedPayments;
    }

    public List<Payment> getSentPayments() {
        return sentPayments;
    }

    public void setSentPayments(List<Payment> sentPayments) {
        this.sentPayments = sentPayments;
    }

    public void addReceived(Payment p) {
        this.receivedPayments.add(p);
    }

    public void addSent(Payment payment) {
        this.sentPayments.add(payment);
    }
}

