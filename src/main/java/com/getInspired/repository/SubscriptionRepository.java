package com.getInspired.repository;


import com.getInspired.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository  extends JpaRepository<Subscription, Long> {

}
