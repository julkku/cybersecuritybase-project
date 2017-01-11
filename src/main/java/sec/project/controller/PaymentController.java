package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.PaymentTemplate;
import sec.project.repository.AccountRepository;
import sec.project.repository.PaymentRepository;
import sec.project.service.PaymentService;

/**
 * Created by julkku on 1/10/17.
 */

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(method = RequestMethod.POST)
    private String makePayment(@ModelAttribute PaymentTemplate payment) {
        System.out.println(payment.getEuro());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        paymentService.newPayment(name, payment.getTo(), payment.getEuro() * 100, payment.getMessage());

        return "redirect:/index";
    }

}
