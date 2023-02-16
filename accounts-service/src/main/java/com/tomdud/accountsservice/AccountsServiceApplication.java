package com.tomdud.accountsservice;

import com.tomdud.accountsservice.model.Account;
import com.tomdud.accountsservice.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class AccountsServiceApplication {

	private final AccountRepository accountRepository;
	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}


	@Bean
	protected void createAccounts() {
		Account account1 =
				Account.builder()
					.id(1L)
					.accountName("Main account 1")
					.accountNumber("952349872398472389")
					.balance(1000L)
					.customerId(1L)
					.build();
		accountRepository.saveAndFlush(account1);

		Account account2 =
				Account.builder()
						.id(2L)
						.accountName("Main account 2")
						.accountNumber("23423764523765786236235")
						.balance(100000L)
						.customerId(2L)
						.build();
		accountRepository.saveAndFlush(account2);
	}

}
