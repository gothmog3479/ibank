package ru.gothmog.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.gothmog.db.entity.Bank;
import ru.gothmog.db.repository.BankRepository;
import ru.gothmog.db.service.BankService;

import java.util.List;

/**
 * @author d.grushetskiy
 */
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public Bank addBank(Bank bank) {
        Bank savedBank = bankRepository.saveAndFlush(bank);
        return savedBank;
    }

    @Override
    public void delete(long id) {
        bankRepository.delete(id);
    }

    @Override
    public Bank getByName(String name) {
        return bankRepository.findByName(name);
    }

    @Override
    public Bank editBank(Bank bank) {
        return bankRepository.saveAndFlush(bank);
    }

    @Override
    public List<Bank> getAll() {
        return bankRepository.findAll();
    }
}
