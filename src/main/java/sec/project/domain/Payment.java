package sec.project.domain;


import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
public class Payment extends AbstractPersistable<Long> {

    private Account from;

    private Account to;

    private Integer amount;

    private String message;

    private Boolean seen;



}
