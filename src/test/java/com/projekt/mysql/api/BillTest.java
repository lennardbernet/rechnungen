package com.projekt.mysql.api;

import com.projekt.mysql.controller.BillController;
import com.projekt.mysql.model.Adress;
import com.projekt.mysql.model.Bill;
import org.apache.coyote.Response;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class BillTest {

    @InjectMocks
    private BillController billController;


    @Mock
    private BillService billServiceMock;

    @Test
    public void getAllBills(){
        Bill bill = new Bill("lennard", "bernet", 500, "CH123", new Date(20150423), new Adress("zürcherstrasse", 2, 8154, "oberglatt"));
        List<Bill> bills = new ArrayList<>();
        bills.add(bill);

        when(billServiceMock.getAllBills()).thenReturn(bills);

        ResponseEntity<List<Bill>> responseEntity = billController.getAllBills();
        List<Bill> listMocked = responseEntity.getBody();

        assertEquals(listMocked,bills);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void getAllBillsNoContent(){

        when(billServiceMock.getAllBills()).thenReturn(null);

        assertEquals(billController.getAllBills().getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void findByFirstname(){
        Bill bill = new Bill("lennard", "bernet", 500, "CH123", new Date(20150423), new Adress("zürcherstrasse", 2, 8154, "oberglatt"));
        List<Bill> bills = new ArrayList<>();
        bills.add(bill);

        when(billServiceMock.findByFirstname("lennard")).thenReturn(bills);

        ResponseEntity<List<Bill>> responseEntity = billController.findByFirstname("lennard");
        List<Bill> listMocked = responseEntity.getBody();

        assertEquals(listMocked,bills);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void findByFirstnameNoContent(){
        Bill bill = new Bill("lennard", "bernet", 500, "CH123", new Date(20150423), new Adress("zürcherstrasse", 2, 8154, "oberglatt"));
        List<Bill> bills = new ArrayList<>();
        bills.add(bill);

        when(billServiceMock.findByFirstname("lennardd")).thenReturn(null);

        ResponseEntity<List<Bill>> responseEntity = billController.findByFirstname("lennardd");
        List<Bill> listMocked = responseEntity.getBody();

        assertEquals(listMocked,null);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void saveBill(){
        Bill bill = new Bill("lennard", "bernet", 500, "CH123", new Date(20150423), new Adress("zürcherstrasse", 2, 8154, "oberglatt"));
        List<Bill> bills = new ArrayList<>();
        when(billServiceMock.saveBill(bill)).thenReturn(bills);

        ResponseEntity<List<Bill>> responseEntity = billController.saveBill(bill);
        List<Bill> listMocked = responseEntity.getBody();

        assertEquals(listMocked,bills);
        assertEquals(responseEntity.getStatusCode(),HttpStatus.CREATED);

    }

    @Test
    public void deleteBillById(){

        ResponseEntity responseEntity = billController.deleteBillById(1);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }

}
