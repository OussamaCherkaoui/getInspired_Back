package com.getInspired.service;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.Subscription;
import com.getInspired.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptions;
    }

    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription confirmSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).get();
        subscription.setIsConfirmed(true);
        return subscriptionRepository.save(subscription);
    }

    public Subscription sendNotification(Long id,String notification) {
        Subscription subscription = subscriptionRepository.findById(id).get();
        subscription.setNotification(notification);
        return subscriptionRepository.save(subscription);
    }

}
