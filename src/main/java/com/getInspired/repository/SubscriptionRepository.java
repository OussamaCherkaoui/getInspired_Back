package com.getInspired.repository;


import com.getInspired.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository  extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByMembre_Id(Long aLong);
}
