package com.gmail.pzalejko.cdc.demo.outbox;

public record OutboxEvent<T>(String id, T payload) {

}