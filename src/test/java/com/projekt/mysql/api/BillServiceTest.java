package com.projekt.mysql.api;

import com.projekt.mysql.controller.BillController;
import com.projekt.mysql.model.Bill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BillServiceTest {

    @Autowired
    private BillController billController;

    @Test
    public void getAllBills(){
        ResponseEntity<List<Bill>> allBills = billController.getAllBills();
        assertNotNull(billController.getAllBills());
    }

}
