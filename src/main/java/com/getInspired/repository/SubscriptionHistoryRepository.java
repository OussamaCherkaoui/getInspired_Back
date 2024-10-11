package com.getInspired.repository;

import com.getInspired.model.SubscriptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistory, Long> {
    List<SubscriptionHistory> findAllBySubscription_Membre_Id(Long id);
    List<SubscriptionHistory> findAllBySubscription_Id(Long id);
    void deleteBySubscription_Id(Long id);

    @Query(value = "select * from Subscription_History where start_date=:start_date and end_date=:end_date and type=:type",nativeQuery = true)
    List<SubscriptionHistory> findByStart_dateAndEnd_dateAndType(LocalDate end_date,LocalDate start_date,String type);
}
