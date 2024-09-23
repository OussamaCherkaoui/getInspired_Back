package com.getInspired;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.SubscriptionHistory;
import com.getInspired.repository.SubscriptionHistoryRepository;
import com.getInspired.service.SubscriptionHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriptionHistoryServiceTest {
    @MockBean
    private SubscriptionHistoryRepository subscriptionHistoryRepository;

    @Autowired
    private SubscriptionHistoryService subscriptionHistoryService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllSubscriptionHistoryByIdMembreWhenNoHistoryFound() {
        Long memberId = 1L;
        when(subscriptionHistoryRepository.findAllBySubscription_Membre_Id(memberId)).thenReturn(new ArrayList<>());

        assertThrows(DatabaseEmptyException.class, () -> subscriptionHistoryService.getAllSubscriptionHistoryByIdMembre(memberId));
    }

    @Test
    void testSaveSubscriptionHistory() {
        SubscriptionHistory subscriptionHistory = new SubscriptionHistory();
        when(subscriptionHistoryRepository.save(subscriptionHistory)).thenReturn(subscriptionHistory);

        SubscriptionHistory savedHistory = subscriptionHistoryService.saveSubscriptionHistory(subscriptionHistory);
        assertNotNull(savedHistory);
    }
}
