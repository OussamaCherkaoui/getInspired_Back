package com.getInspired.controller;

import com.getInspired.dto.SubscriptionDto;
import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.mapper.SubscriptionMapper;
import com.getInspired.model.Reservation;
import com.getInspired.model.Subscription;
import com.getInspired.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SubscriptionController {


    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
            return ResponseEntity.ok(subscriptionMapper.toDTO(subscriptions));
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('MEMBRE')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SubscriptionDto subscriptionDto){
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionMapper.toDTO(subscriptionService.saveSubscription(subscription)));
    }
    @PreAuthorize("hasAuthority('MEMBRE')")
    @PutMapping("/renewal")
    public ResponseEntity<?> renewal(@RequestBody SubscriptionDto subscriptionDto ){
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDto);
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionMapper.toDTO(subscriptionService.saveSubscription(subscription)));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/sendNotification/{id}/{notification}")
    public ResponseEntity<?> sendNotification(@PathVariable Long id,@PathVariable String notification){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionMapper.toDTO(subscriptionService.sendNotification(id,notification)));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/confirmSubscription/{id}")
    public ResponseEntity<?> confirmSubscription(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionMapper.toDTO(subscriptionService.confirmSubscription(id)));
    }

}
