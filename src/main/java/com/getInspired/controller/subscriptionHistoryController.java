package com.getInspired.controller;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.mapper.SubscriptionHistoryMapper;
import com.getInspired.model.Subscription;
import com.getInspired.model.SubscriptionHistory;
import com.getInspired.service.SubscriptionHistoryService;
import com.getInspired.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptionHistory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class subscriptionHistoryController{

    private final SubscriptionHistoryService subscriptionHistoryService;
    private final SubscriptionHistoryMapper subscriptionHistoryMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBRE')")
    @GetMapping("/getSubscriptionHistoryByIdMembre/{id}")
    public ResponseEntity<?> getAll(@PathVariable Long id) {
        try {
            List<SubscriptionHistory> subscriptionHistories = subscriptionHistoryService.getAllSubscriptionHistoryByIdMembre(id);
            return ResponseEntity.ok(subscriptionHistoryMapper.toDTO(subscriptionHistories));
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBRE')")
    @GetMapping("/getSubscriptionHistoryByIdSubscription/{id}")
    public ResponseEntity<?> getSubscriptionHistoryByIdSubscription(@PathVariable Long id) {
        try {
            List<SubscriptionHistory> subscriptionHistories = subscriptionHistoryService.getAllSubscriptionHistoryByIdSubscription(id);
            return ResponseEntity.ok(subscriptionHistoryMapper.toDTO(subscriptionHistories));
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
