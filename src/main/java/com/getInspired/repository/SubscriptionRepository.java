package com.getInspired.repository;


import com.getInspired.dto.SubscriptionDto;
import com.getInspired.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository  extends JpaRepository<Subscription, Long> {
    @Query(value = "SELECT COUNT(*) FROM subscription WHERE is_confirmed=true and end_date>CURRENT_DATE",nativeQuery = true)
    Long countSubscriptionConfirmed();

    Subscription findByMembre_IdAndType(Long idMembre, String type);


    @Query("SELECT s.type, COUNT(s.id) FROM Subscription s GROUP BY s.type")
    List<Object[]> countSubscriptionsByType();

    @Query(value = "SELECT new com.getInspired.dto.SubscriptionDto(s.id,m.username,s.type,s.start_date,s.end_date,s.isConfirmed,s.notification,m.id) FROM Subscription s " +
            "join s.membre m" +
            " where s.end_date>=current_date " +
            "and s.isConfirmed=false")
    List<SubscriptionDto> findAllRequestSubscription();

    @Query(value = "SELECT new com.getInspired.dto.SubscriptionDto(s.id,m.username,s.type,s.start_date,s.end_date,s.isConfirmed,s.notification,m.id) " +
            "FROM Subscription s join s.membre m where s.end_date>=current_date" +
            " and s.isConfirmed=true and s.type='annually'")
    List<SubscriptionDto> findAllSubscriptionAnnualy();

    @Query(value = "SELECT new com.getInspired.dto.SubscriptionDto(s.id,m.username,s.type,s.start_date,s.end_date,s.isConfirmed,s.notification,m.id) " +
            "FROM Subscription s join s.membre m where s.end_date>=current_date " +
            "and s.isConfirmed=true and s.type='Monthly'")
    List<SubscriptionDto> findAllSubscriptionMonthly();


    @Query(value = "SELECT new com.getInspired.dto.SubscriptionDto(s.id,m.username,s.type,s.start_date,s.end_date,s.isConfirmed,s.notification,m.id) FROM Subscription s " +
            "join s.membre m" +
            " where s.end_date>=current_date " +
            "and s.isConfirmed=false and m.username like :username%")
    List<SubscriptionDto> findAllRequestSubscriptionByUsername(String username);

    @Query(value = "SELECT new com.getInspired.dto.SubscriptionDto(s.id,m.username,s.type,s.start_date,s.end_date,s.isConfirmed,s.notification,m.id) " +
            "FROM Subscription s join s.membre m where s.end_date>=current_date" +
            " and s.isConfirmed=true and s.type='annually' and m.username like :username%")
    List<SubscriptionDto> findAllSubscriptionAnnualyByUsername(String username);

    @Query(value = "SELECT new com.getInspired.dto.SubscriptionDto(s.id,m.username,s.type,s.start_date,s.end_date,s.isConfirmed,s.notification,m.id) " +
            "FROM Subscription s join s.membre m where s.end_date>=current_date " +
            "and s.isConfirmed=true and s.type='Monthly' and m.username like :username%")
    List<SubscriptionDto> findAllSubscriptionMonthlyByUsername(String username);


    List<Subscription> findByMembre_Id(Long id);

}
