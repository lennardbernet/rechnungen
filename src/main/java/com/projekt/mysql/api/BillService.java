package com.projekt.mysql.api;

import com.projekt.mysql.model.Adress;
import com.projekt.mysql.model.Bill;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BillService {

    @PersistenceContext
    private EntityManager em;

    public List<Bill> getAllBills() {
        try {
            List<Bill> list = null;
            list = em.createNamedQuery("bill.getAllBills", Bill.class).getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    public List<Bill> findByFirstname(String firstname) {
        try {
            return em.createNamedQuery("bill.findByFirstname", Bill.class).setParameter(1, firstname).getResultList();
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    @Transactional
    public List<Bill> saveBill(Bill bill) {
        try {
            List<Bill> list = null;
            em.persist(bill.getAdress());
            bill.getAdress().setAdressId(em.find(Adress.class, bill.getAdress().getAdressId()).getAdressId());
            em.persist(bill);
            list = getAllBills();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    @Transactional
    public void deleteById(int id) {
        try {
            Bill matchedBill = null;
            matchedBill = em.find(Bill.class, id);
            if (matchedBill == null) {
            } else {
                matchedBill.setBillId(6);
                em.remove(matchedBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }
}
