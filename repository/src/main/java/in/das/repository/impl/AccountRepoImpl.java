package in.das.repository.impl;

import in.das.entity.Account;
import in.das.repository.AccountRepository;
import in.das.shared.exception.AccountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class AccountRepoImpl {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account){
        Account createdeAccount = null;
        try {
            createdeAccount  = accountRepository.save(account);
        }catch (Exception ex){
            log.error("An Exception occurred while inserting account record");
            ex.printStackTrace();
        }
        return createdeAccount;
    }

    public List<Account> fetchAllAccounts(){
        List<Account> result = null;
        try{
            result = accountRepository.findAll();
            log.info("accounts fetch successfully, fetched {} accounts",result.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Account fetchAccountByUsername(final String username){
        return accountRepository.findByUsername(username).orElseThrow(() -> new AccountException("Cannot find Account with given username"));
    }

    public Account fetchAccountByEmail(final String email){
        return accountRepository.findByEmailId(email).orElseThrow(() -> new AccountException("Cannot find Account with given email-id"));
    }

    public Account fetchAccountById(final long id){
        return accountRepository.findById(id).orElseThrow(() -> new AccountException("Cannot find Account with given id"));
    }

    public Optional<Account> loginByUsernameAndPassword(final String username, final String password){
        return accountRepository.findByUsernameAndPassword(username,password);
    }


}
