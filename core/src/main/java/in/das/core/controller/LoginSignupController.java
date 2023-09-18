package in.das.core.controller;

import in.das.core.services.AccountService;
import in.das.entity.Account;
import in.das.shared.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes-app")
@Slf4j
public class LoginSignupController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account createAccount(RequestEntity<Account> accountRequestEntity){
        log.info("LoginSignupController::createAccount");
        if(!accountRequestEntity.hasBody() || accountRequestEntity.getBody() == null){
            throw new BadRequestException("Request has no body!");
        }
        return accountService.createAccount(accountRequestEntity.getBody());
    }
}
