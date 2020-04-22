package com.projekt.rechnungen.api;

import com.projekt.rechnungen.model.Bill;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BillService {

    @PersistenceContext
    private EntityManager em;

    public List<Bill> getAllBills() {
        List<Bill> list = null;
        try {
            list = em.createQuery("select r from Bill r", Bill.class).getResultList();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    public List<Bill> saveBill(Bill bill){
        List<Bill> list = null;
        try{
            System.out.println(bill.toString());
            em.persist(bill);
            list = getAllBills();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return list;
    }

}