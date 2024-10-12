package com.getInspired.service;

import com.getInspired.dto.SubscriptionDto;
import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.mapper.SubscriptionMapper;
import com.getInspired.model.Subscription;
import com.getInspired.model.SubscriptionHistory;
import com.getInspired.repository.SubscriptionHistoryRepository;
import com.getInspired.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionHistoryService subscriptionHistoryService;
    private final SubscriptionHistoryRepository subscriptionHistoryRepository;

    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptions;
    }

    public Subscription saveSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscriptionMember = subscriptionRepository.findByMembre_IdAndType(subscriptionDto.getIdMembre(),subscriptionDto.getType());
        if(subscriptionMember!=null){
            subscriptionDto.setId(subscriptionMember.getId());
            subscriptionDto.setNotification(subscriptionMember.getNotification());
        }
        subscriptionDto.setIsConfirmed(false);
        return subscriptionRepository.save(subscriptionMapper.toEntity(subscriptionDto));
    }


    public Subscription confirmSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).get();
        subscription.setIsConfirmed(true);
        List<SubscriptionHistory> subscriptionsHistory = subscriptionHistoryRepository.findByStart_dateAndEnd_dateAndType(subscription.getEnd_date(),subscription.getStart_date(),subscription.getType());
        if (subscriptionsHistory.isEmpty())
        {
            SubscriptionHistory subscriptionHistory = new SubscriptionHistory();
            subscriptionHistory.setSubscription(subscription);
            subscriptionHistory.setType(subscription.getType());
            subscriptionHistory.setStart_date(subscription.getStart_date());
            subscriptionHistory.setEnd_date(subscription.getEnd_date());
            subscriptionHistoryService.saveSubscriptionHistory(subscriptionHistory);
        }
        return subscriptionRepository.save(subscription);
    }

    public Subscription sendNotification(Long id,String notification) {
        Subscription subscription = subscriptionRepository.findById(id).get();
        subscription.setNotification(notification);
        return subscriptionRepository.save(subscription);
    }

    public Long countSubscriptionConfirmed(){
        return subscriptionRepository.countSubscriptionConfirmed();
    }

    public Map<String, Long> getSubscriptionCountByType() {
        List<Object[]> results = subscriptionRepository.countSubscriptionsByType();
        Map<String, Long> subscriptionCountMap = new HashMap<>();

        for (Object[] result : results) {
            String type = (String) result[0];
            Long count = (Long) result[1];
            subscriptionCountMap.put(type, count);
        }
        return subscriptionCountMap;
    }

    public List<SubscriptionDto> getAllRequestSubscription() {
        List<SubscriptionDto> subscriptions = subscriptionRepository.findAllRequestSubscription();
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptions;
    }

    public List<SubscriptionDto> getAllSubscriptionAnnualy() {
        List<SubscriptionDto> subscriptions = subscriptionRepository.findAllSubscriptionAnnualy();
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptions;
    }



    public List<SubscriptionDto> getAllSubscriptionMonthly() {
        List<SubscriptionDto> subscriptions = subscriptionRepository.findAllSubscriptionMonthly();
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptions;
    }

    public List<SubscriptionDto> getAllRequestSubscriptionByUsername(String username) {
        List<SubscriptionDto> subscriptions = subscriptionRepository.findAllRequestSubscriptionByUsername(username);
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptions;
    }

    public List<SubscriptionDto> getAllSubscriptionAnnualyByUsername(String username) {
        List<SubscriptionDto> subscriptions = subscriptionRepository.findAllSubscriptionAnnualyByUsername(username);
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptions;
    }



    public List<SubscriptionDto> getAllSubscriptionMonthlyByUsername(String username) {
        List<SubscriptionDto> subscriptions = subscriptionRepository.findAllSubscriptionMonthlyByUsername(username);
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptions;
    }

    public Subscription cancelSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).get();
        subscription.setIsConfirmed(false);
        return subscriptionRepository.save(subscription);
    }

    public Boolean deleteSubscription(Long id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isPresent()) {
            subscriptionHistoryRepository.deleteBySubscription_Id(id);
            subscriptionRepository.delete(subscription.get());
            return true;
        }
        return false;
    }

    public List<SubscriptionDto> getAllSubscriptionsByIdMember(Long id) {
        List<Subscription> subscriptions = subscriptionRepository.findByMembre_Id(id);
        if (subscriptions.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return subscriptionMapper.toDTO(subscriptions);
    }
}
