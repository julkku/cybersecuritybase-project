package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.Payment;
import sec.project.domain.PaymentTemplate;
import sec.project.repository.AccountRepository;
import sec.project.repository.PaymentRepository;
import sec.project.service.PaymentService;

import javax.validation.Valid;

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
    private String makePayment(@Valid PaymentTemplate payment, BindingResult br) {
        if (br.hasErrors()) {
            return "redirect:/index";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        paymentService.newPayment(name, payment.getTo(), payment.getEuro(), payment.getMessage());
        return "redirect:/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private String getPayment(@PathVariable(value = "id") Long id, Model model) {
        Payment p = paymentRepository.findOne(id);
        p.setSeen(true);
        paymentRepository.save(p);
        model.addAttribute("payment", p);


        return "payment";
    }

}
