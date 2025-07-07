package com.steve.ev.Service;

import com.steve.ev.Dto.TransactionDto;
import com.steve.ev.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    private final TransactionRepo transactionRepo;

    @Autowired
    private DataService (TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<TransactionDto> getAllTransactionFromDto() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return transactionRepo.findAll().stream()
                .map(transaction -> new TransactionDto(transaction.getIdTag(),
                        transaction.getConnectorId(),
                        transaction.getStartTime(),
                        transaction.getStopTime(),
                        transaction.getMeterStart(),
                        transaction.getMeterStop()))
                .collect(Collectors.toList());
    }
}