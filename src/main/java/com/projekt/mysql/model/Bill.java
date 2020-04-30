package com.projekt.mysql.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="bill")
@NamedQueries({
        @NamedQuery(name="bill.getAllBills",query="select b from Bill b"),
        @NamedQuery(name="bill.findByFirstname",query="select b from Bill b where b.firstname = ?1 ")
})
public class Bill {

    @Id
    @Column(name="bill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="amount")
    private int amount;

    @Column(name="iban")
    private String iban;

    @Column(name="date")
    private Date date;

    @OneToOne
    @JoinColumn(name = "adress_idfk", referencedColumnName = "adress_id")
    private Adress adress;

    public Bill(){

    }

    public Bill(String firstname, String lastname, int amount, String iban, Date date, Adress adress){
        this.firstname = firstname;
        this.lastname = lastname;
        this.amount = amount;
        this.iban = iban;
        this.date = date;
        this.adress = adress;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int rechnungId) {
        this.billId = rechnungId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
