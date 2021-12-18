package fsoft.mock_project.movie_theater.security.service;

import fsoft.mock_project.movie_theater.security.model.Account;
import fsoft.mock_project.movie_theater.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        log.info("Inside saveAccount method of AccountService");
        return accountRepository.save(account);
    }

    public Account findAccountById(String accountId) {
        log.info("Inside findAccountById method of AccountService");
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalStateException("Cannot find account with id = " + accountId));
    }

    public Account findByUsername(String username) {
        log.info("Inside findByUsername method of AccountService");
        return accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Cannot find account with username = " + username));
    }
}
