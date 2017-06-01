package ru.gothmog.db.service;

import ru.gothmog.db.entity.Bank;

import java.util.List;

/**
 * @author d.grushetskiy
 */
public interface BankService {

    Bank addBank(Bank bank);

    void delete(long id);

    Bank getByName(String name);

    Bank editBank(Bank bank);

    List<Bank> getAll();
}
