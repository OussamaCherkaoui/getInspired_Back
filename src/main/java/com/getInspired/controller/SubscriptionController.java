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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

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
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllRequestSubscription")
    public ResponseEntity<?> getAllRequestSubscription() {
        try {
            List<SubscriptionDto> subscriptions = subscriptionService.getAllRequestSubscription();
            return ResponseEntity.ok(subscriptions);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllSubscriptionAnnualy")
    public ResponseEntity<?> getAllSubscriptionAnnualy() {
        try {
            List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptionAnnualy();
            return ResponseEntity.ok(subscriptions);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllSubscriptionMonthly")
    public ResponseEntity<?> getAllSubscriptionMonthly() {
        try {
            List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptionMonthly();
            return ResponseEntity.ok(subscriptions);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllRequestSubscriptionByUsername/{username}")
    public ResponseEntity<?> getAllRequestSubscriptionByUsername(@PathVariable String username) {
        try {
            List<SubscriptionDto> subscriptions = subscriptionService.getAllRequestSubscriptionByUsername(username);
            if(subscriptions.isEmpty())
            {
                return null;
            }
            return ResponseEntity.ok(subscriptions);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllSubscriptionAnnualyByUsername/{username}")
    public ResponseEntity<?> getAllSubscriptionAnnualyByUsername(@PathVariable String username) {
        try {
            List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptionAnnualyByUsername(username);
            if(subscriptions.isEmpty())
            {
                return null;
            }
            return ResponseEntity.ok(subscriptions);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllSubscriptionMonthlyByUsername/{username}")
    public ResponseEntity<?> getAllSubscriptionMonthlyByUsername(@PathVariable String username) {
        try {
            List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptionMonthlyByUsername(username);
            if(subscriptions.isEmpty())
            {
                return null;
            }
            return ResponseEntity.ok(subscriptions);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PreAuthorize("hasAuthority('MEMBRE')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SubscriptionDto subscriptionDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionMapper.toDTO(subscriptionService.saveSubscription(subscriptionDto)));
    }
    @PreAuthorize("hasAuthority('MEMBRE')")
    @PutMapping("/renewal")
    public ResponseEntity<?> renewal(@RequestBody SubscriptionDto subscriptionDto ){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionMapper.toDTO(subscriptionService.saveSubscription(subscriptionDto)));
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/cancelSubscription/{id}")
    public ResponseEntity<?> cancelSubscription(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionMapper.toDTO(subscriptionService.cancelSubscription(id)));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/countSubscriptionConfirmed")
    public ResponseEntity<?> countSubscriptionConfirmed() {
        return new ResponseEntity<>(subscriptionService.countSubscriptionConfirmed(), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/count-by-type")
    public Map<String, Long> getSubscriptionCountByType() {
        return subscriptionService.getSubscriptionCountByType();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBRE')")
    @DeleteMapping("/deleteSubscription/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.deleteSubscription(id));
    }

    @PreAuthorize("hasAuthority('MEMBRE')")
    @GetMapping("/getAllByIdMember/{id}")
    public ResponseEntity<?> getAllSubscriptionByIdMember(@PathVariable Long id) {
        try {
            List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptionsByIdMember(id);
            if(subscriptions.isEmpty())
            {
                return null;
            }
            return ResponseEntity.ok(subscriptions);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
