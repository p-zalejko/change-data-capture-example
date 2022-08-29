package com.gmail.pzalejko.cdc.demo.account.application;

import com.gmail.pzalejko.cdc.demo.account.domain.MoneyTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class MoneyTransferController {

    private final MoneyTransferService transferService;

    @PostMapping("/accounts/{clientId}/{id}/sendMoney")
    void sendMoney(@PathVariable long id, @RequestBody MoneyTransferService.SendMoneyDto dto) {
        transferService.sendMoney(id, dto);
    }
}
