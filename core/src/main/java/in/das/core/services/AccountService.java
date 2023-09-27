package in.das.core.services;

import in.das.entity.Account;
import in.das.repository.impl.AccountRepoImpl;
import in.das.shared.exception.AccountCreationException;
import in.das.shared.exception.BadRequestException;
import in.das.shared.exception.ValidationException;
import in.das.shared.models.AccountRequest;
import in.das.shared.models.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public LoginResponse accountLogin(final AccountRequest accountRequest){
        validateLoginCredentials(accountRequest);
        accountRequest.setPassword(new BCryptPasswordEncoder().encode(accountRequest.getPassword()));
        Optional<Account> accountResult = accountRepositoryImpl.loginByUsernameAndPassword(accountRequest.getUsername(),accountRequest.getPassword());
        if(accountResult.isEmpty()){
            log.error("Login failed! No Account found with given username & password");
            return LoginResponse.builder().loginStatus(false).build();
        }
        log.info("Login Success for user : {}",accountRequest.getUsername());
        return LoginResponse.builder()
                .loginStatus(true)
                .username(accountResult.get().getUsername())
                .accountId(accountResult.get().getAccountId())
                .email(accountResult.get().getEmailId())
                .build();
    }

    public Account getAccountDetails(String id, String identifier){
        Account account = null;
        if ("username".equalsIgnoreCase(identifier)){
            account = accountRepositoryImpl.fetchAccountByUsername(id);
        } else if ("email".equalsIgnoreCase(identifier)) {
            account = accountRepositoryImpl.fetchAccountByEmail(id);
        }else if ("accountId".equalsIgnoreCase(identifier)){
            account = accountRepositoryImpl.fetchAccountById(Long.parseLong(id));
        }else {
            log.error("Invalid \"identifier\" query provided : \"{}\"", identifier);
            throw new BadRequestException("Invalid or no \"identifier\" query provided");
        }
        return account;
    }

    private void validateLoginCredentials(AccountRequest accReq){
        if(StringUtils.isBlank(accReq.getPassword()) && (StringUtils.isBlank(accReq.getEmailId()) || StringUtils.isBlank(accReq.getUsername()))){
            throw new ValidationException("Login validation failed! Check password, email/username");
        }
    }
}
