package com.getInspired.service;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.Space;
import com.getInspired.model.SubscriptionHistory;
import com.getInspired.repository.SpaceRepository;
import com.getInspired.repository.SubscriptionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionHistoryService {
    private final SubscriptionHistoryRepository subscriptionHistoryRepository;

    public List<SubscriptionHistory> getAllSubscriptionHistoryByIdMembre(Long id) throws DatabaseEmptyException {
        List<SubscriptionHistory> subscriptionHistories = subscriptionHistoryRepository.findAllBySubscription_Membre_Id(id);
        if (subscriptionHistories.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptionHistories;
    }

    public SubscriptionHistory saveSubscriptionHistory(SubscriptionHistory subscriptionHistory) {
        return subscriptionHistoryRepository.save(subscriptionHistory);
    }

    public List<SubscriptionHistory> getAllSubscriptionHistoryByIdSubscription(Long id) {
        List<SubscriptionHistory> subscriptionHistories = subscriptionHistoryRepository.findAllBySubscription_Id(id);
        if (subscriptionHistories.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptionHistories;
    }
}
