package com.steve.ev.Contoller;

import com.steve.ev.Dto.TransactionDto;
import com.steve.ev.Entity.Transaction;
import com.steve.ev.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TransactionController {

    private final DataService dataService;

    @Autowired
    public TransactionController(DataService dataService) {
        this.dataService = dataService;
    }

    @ResponseBody
    @GetMapping("/api/transactionDto")
    public List<TransactionDto> getAllTransactions() {
        return dataService.getAllTransactionFromDto();
    }
}
