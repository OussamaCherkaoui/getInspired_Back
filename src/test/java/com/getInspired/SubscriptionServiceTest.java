package com.getInspired;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.Membre;
import com.getInspired.model.Subscription;
import com.getInspired.model.SubscriptionHistory;
import com.getInspired.repository.SubscriptionRepository;
import com.getInspired.service.SubscriptionHistoryService;
import com.getInspired.service.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubscriptionServiceTest {
    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionHistoryService subscriptionHistoryService;

    @Autowired
    private SubscriptionService subscriptionService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllSubscriptionsWhenNoSubscriptionsFound() {
        when(subscriptionRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(DatabaseEmptyException.class, () -> subscriptionService.getAllSubscriptions());
    }

    @Test
    void testConfirmSubscription_success() {
        Subscription mockSubscription = new Subscription();
        mockSubscription.setId(1L);
        mockSubscription.setIsConfirmed(false);

        when(subscriptionRepository.findById(1L)).thenReturn(Optional.of(mockSubscription));
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(mockSubscription);

        Subscription result = subscriptionService.confirmSubscription(1L);

        assertNotNull(result);
        assertTrue(result.getIsConfirmed());

        verify(subscriptionRepository, times(1)).findById(1L);
        verify(subscriptionRepository, times(1)).save(mockSubscription);
    }

}
