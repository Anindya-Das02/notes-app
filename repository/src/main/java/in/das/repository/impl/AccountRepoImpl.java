package in.das.repository.impl;

import in.das.entity.Account;
import in.das.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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


}
