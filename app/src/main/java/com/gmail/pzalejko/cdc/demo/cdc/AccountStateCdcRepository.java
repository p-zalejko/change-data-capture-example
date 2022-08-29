package com.gmail.pzalejko.cdc.demo.cdc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface AccountStateCdcRepository extends JpaRepository<AccountStateCdc, Long> {

    Optional<AccountStateCdc> findByAccountId(Long accountId);
}
