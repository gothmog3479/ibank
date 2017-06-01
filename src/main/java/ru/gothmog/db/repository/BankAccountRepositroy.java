package ru.gothmog.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gothmog.db.entity.BankAccount;

/**
 * @author d.grushetskiy
 */
public interface BankAccountRepositroy extends JpaRepository<BankAccount, Long> {
}
