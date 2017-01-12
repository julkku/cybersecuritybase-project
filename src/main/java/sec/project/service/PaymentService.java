package sec.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sec.project.domain.Account;
import sec.project.domain.Payment;
import sec.project.repository.AccountRepository;
import sec.project.repository.PaymentRepository;

/**
 * Created by julkku on 1/11/17.
 */

@Service
public class PaymentService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Payment newPayment(String from, String to, Integer amount, String message) {
        System.out.println(from);
        System.out.println(to);
        System.out.println(amount);
        Account accTo = accountRepository.findByUsername(to);
        Account accFrom = accountRepository.findByUsername(from);

        if(accFrom.getFunds()<amount){
            return null;
        }

        Payment payment = new Payment();
        payment.setMessage(message);
        payment.setFrom(accFrom);
        payment.setTo(accTo);
        payment.setEuro(amount);

        accTo.setFunds(accTo.getFunds() + amount);
        accFrom.setFunds(accFrom.getFunds() - amount);

        payment = paymentRepository.save(payment);

        accTo.addReceived(payment);
        accFrom.addSent(payment);
        accountRepository.save(accTo);
        accountRepository.save(accFrom);

        return payment;
    }



}
