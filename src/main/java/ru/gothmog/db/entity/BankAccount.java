package ru.gothmog.db.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author d.grushetskiy
 */
@Entity
@Table(name = "bank_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "currency")
    private double currency;

    @Column(name = "amount")
    private double amount;

    @Column(name = "amount_of_credit")
    private double amountOfCredit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "client_id")
    private Client client;

    public BankAccount() {
    }

    public BankAccount(double currency, double amount, double amountOfCredit, Client client) {
        this.currency = currency;
        this.amount = amount;
        this.amountOfCredit = amountOfCredit;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmountOfCredit() {
        return amountOfCredit;
    }

    public void setAmountOfCredit(double amountOfCredit) {
        this.amountOfCredit = amountOfCredit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
