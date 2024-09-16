package com.getInspired.service;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.Subscription;
import com.getInspired.model.SubscriptionHistory;
import com.getInspired.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionHistoryService subscriptionHistoryService;

    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptions;
    }

    public Subscription saveSubscription(Subscription subscription) {
        Optional<Subscription> subscriptionMember = subscriptionRepository.findByMembre_Id(subscription.getMembre().getId());
        Subscription subscriptionSaved=new Subscription();
        subscriptionMember.ifPresent(value -> subscription.setId(value.getId()));
        subscriptionSaved = subscriptionRepository.save(subscription);
        SubscriptionHistory subscriptionHistory = new SubscriptionHistory();
        subscriptionHistory.setSubscription(subscriptionSaved);
        subscriptionHistory.setType(subscription.getType());
        subscriptionHistory.setStart_date(subscription.getStart_date());
        subscriptionHistory.setEnd_date(subscription.getEnd_date());
        subscriptionHistoryService.saveSubscriptionHistory(subscriptionHistory);
        return subscriptionSaved;
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
