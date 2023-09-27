package in.das.core.controller;

import in.das.core.services.AccountService;
import in.das.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes-app/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/details/all")
    public List<Account> getAllAccountDetails(){
        return accountService.fetchAllAccounts();
    }

    @GetMapping("/details")
    public Account getAccountDetails(@RequestParam("identifier") String identifier, @RequestParam("id") String id){
        log.info("Invoked AccountController::getAccountDetails");
        return accountService.getAccountDetails(id,identifier);
    }


}
