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
import java.util.ArrayList;
import java.util.List;

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

        paymentService.newPayment("soini", "jules", 5, "test payment");
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String loadIndex(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Account account = accountRepository.findByUsername(name);

//        This is bad
        int unseen = 0;
        for (Payment p : account.getReceivedPayments()) {
            if (!p.getSeen()) unseen++;
        }

//        So is this :D
        List<Account> accounts = accountRepository.findAll();
        List<Account> others = new ArrayList<>();
        for(Account a : accounts){
            if(!a.getUsername().equals(name)) others.add(a);
        }


        model.addAttribute("account", account);
        model.addAttribute("funds", account.getFunds());
        if(unseen>0) model.addAttribute("unseen", unseen);
        model.addAttribute("users", others);
//        model.addAttribute("payments", paymentRepository.FindByTo(account));

        return "index";
    }


}
