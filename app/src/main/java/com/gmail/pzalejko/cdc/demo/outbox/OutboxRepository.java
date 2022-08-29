package com.gmail.pzalejko.cdc.demo.outbox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface OutboxRepository extends JpaRepository<Outbox, UUID> {
}