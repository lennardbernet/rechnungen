package com.projekt.mysql.api;

import com.projekt.mysql.model.Adress;
import com.projekt.mysql.model.StandingOrder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StandingOrderService {

    @PersistenceContext
    private EntityManager em;

    public List<StandingOrder> getAllStandingOrders() {
        try {
            List<StandingOrder> list = null;
            list = em.createQuery("select d from StandingOrder d", StandingOrder.class).getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    @Transactional
    public List<StandingOrder> saveStandingOrder(StandingOrder standingOrder) {
        List<StandingOrder> list = null;
        try {
            em.persist(standingOrder.getAdress());
            standingOrder.getAdress().setAdressId(em.find(Adress.class, standingOrder.getAdress().getAdressId()).getAdressId());
            em.persist(standingOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
        list = getAllStandingOrders();
        return list;
    }

    @Transactional
    public void deleteById(int id) {
        try {
            StandingOrder matchedStandingOrder = em.find(StandingOrder.class, id);
            if (matchedStandingOrder == null) {
            } else {
                em.remove(matchedStandingOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    @Transactional
    public void executeStandingOrder(int id) {
        StandingOrder matchedStandingOrder;
        try {
            matchedStandingOrder = em.find(StandingOrder.class, id);
            int executions = matchedStandingOrder.getExecutions();
            if (executions <= 1) {
                em.remove(matchedStandingOrder);
            } else {
                matchedStandingOrder.setExecutions(executions - 1);
                em.merge(matchedStandingOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

}
