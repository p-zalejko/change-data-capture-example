package com.gmail.pzalejko.cdc.demo.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AccountOwnerRepository extends JpaRepository<AccountOwner, Long> {
}
