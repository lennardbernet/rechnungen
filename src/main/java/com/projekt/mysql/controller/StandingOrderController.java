package com.projekt.mysql.controller;

import com.projekt.mysql.api.StandingOrderService;
import com.projekt.mysql.model.StandingOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "standingOrder")
public class StandingOrderController {

    private StandingOrderService standingOrderService;

    @Autowired
    public StandingOrderController(StandingOrderService standingOrderService) {
        this.standingOrderService = standingOrderService;
    }

    @GetMapping(value = "getAllStandingOrders")
    public ResponseEntity<List<StandingOrder>> getDauerauftragAll() {
        try {
            List<StandingOrder> standingOrders = standingOrderService.getAllStandingOrders();
            if(standingOrders.size() == 0){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            else {
                return ok(standingOrders);
            }
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    @PostMapping(value = "saveStandingOrder",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StandingOrder>> saveStandingOrderWithoutAdress(@RequestBody StandingOrder standingOrder) {
        try {
            List<StandingOrder> standingOrders = standingOrderService.saveStandingOrder(standingOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(standingOrders);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    @DeleteMapping(value = "deleteStandingOrder/{id}")
    public ResponseEntity deleteStandingOrderById(@PathVariable("id") Integer id) {
        try {
            standingOrderService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    @PutMapping(value = "executeStandingOrder/{id}")
    public ResponseEntity executeStandingOrderById(@PathVariable("id") Integer id) {
        try {
            standingOrderService.executeStandingOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

}
