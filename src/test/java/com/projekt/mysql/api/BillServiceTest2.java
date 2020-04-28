package com.projekt.mysql.api;

import com.projekt.mysql.controller.BillController;
import com.projekt.mysql.model.Adress;
import com.projekt.mysql.model.Bill;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class BillServiceTest2 {

    @InjectMocks
    private BillController billController;


    @Mock
    private BillService billServiceMock;

    @Test
    public void getAllBills(){
        Bill bill = new Bill("lennard", "bernet", 500, "CH123", new Date(20150423), new Adress("zürcherstrasse", 2, 8154, "oberglatt"));
        List<Bill> list = new ArrayList<>();
        list.add(bill);

        when(billServiceMock.getAllBills()).thenReturn(list);

        List<Bill> listMocked = billController.getAllBills().getBody();

        System.out.println(listMocked.get(0).getFirstname());

        assertEquals(listMocked,list);
    }

}
