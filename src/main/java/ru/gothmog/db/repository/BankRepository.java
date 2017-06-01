package ru.gothmog.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gothmog.db.entity.Bank;

/**
 * @author d.grushetskiy
 */
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("SELECT b FROM Bank b WHERE b.name = :name")
    Bank findByName(@Param("name") String name);
}
