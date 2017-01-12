package sec.project.domain;


import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
public class Payment extends AbstractPersistable<Long> {

    @Column(name = "PAYMENT_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private Account from;

    @ManyToOne
    private Account to;

    @Min(1)
    private Integer euro;

    private String message;

    private Boolean seen;


    public Payment(){
        this.seen = false;
        this.date = new Date();
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public Integer getEuro() {
        return euro;
    }

    public void setEuro(Integer euro) {
        this.euro = euro;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
