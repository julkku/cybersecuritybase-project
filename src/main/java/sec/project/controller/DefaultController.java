package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.Account;
import sec.project.domain.Payment;
import sec.project.repository.AccountRepository;
import sec.project.repository.PaymentRepository;
import sec.project.service.PaymentService;

import javax.annotation.PostConstruct;

@Controller
public class DefaultController {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/index";
    }

    @PostConstruct
    public void init(){

        paymentService.newPayment("soini", "jules", 500, "test payment");
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String loadIndex(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Account account = accountRepository.findByUsername(name);

        int unseen = 0;
        for (Payment p : account.getReceivedPayments()) {
            if (!p.getSeen()) unseen++;
        }
        String funds = account.getFunds() + "";
        String parsed = funds.substring(0, funds.length() - 2) + "," + funds.substring(funds.length() - 2);


        model.addAttribute("username", account.getUsername());
        model.addAttribute("funds", parsed);
        if(unseen>0) model.addAttribute("unseen", unseen);
        model.addAttribute("users", accountRepository.findAll());
        model.addAttribute("payments", paymentRepository.findAll());

        return "index";
    }


}
