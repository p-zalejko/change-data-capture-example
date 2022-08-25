package com.gmail.pzalejko.cdc.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountOwnerRepository extends JpaRepository<AccountOwner, Long> {
}