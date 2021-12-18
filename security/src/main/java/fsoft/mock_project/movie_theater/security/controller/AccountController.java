package fsoft.mock_project.movie_theater.security.controller;

import fsoft.mock_project.movie_theater.security.model.Account;
import fsoft.mock_project.movie_theater.security.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.saveAccount(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable("id") String accountId) {
        return ResponseEntity.ok(accountService.findAccountById(accountId));
    }
}
