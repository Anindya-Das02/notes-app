package in.das.core.services;

import in.das.entity.Account;
import in.das.repository.impl.AccountRepoImpl;
import in.das.shared.exception.AccountCreationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepoImpl accountRepositoryImpl;

    public Account createAccount(final Account account){
        log.info("creating account");
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        account.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        Account createdAccount = accountRepositoryImpl.createAccount(account);
        if(createdAccount == null){
            throw new AccountCreationException("An error occurred while creating account!");
        }
        log.info("account created successfully, accountId:{}",createdAccount.getAccountId());
        return account;
    }

    public List<Account> fetchAllAccounts(){
        return accountRepositoryImpl.fetchAllAccounts();
    }
}
