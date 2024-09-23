package com.getInspired;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.repository.ReservationRepository;
import com.getInspired.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReservationServiceTest {
    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetAllReservationWhenNoReservationsFound() {
        when(reservationRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(DatabaseEmptyException.class, () -> reservationService.getAllReservation());
    }

    @Test
    void testCancelReservationWhenReservationNotFound() {
        Long reservationId = 1L;
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> reservationService.cancelReservation(reservationId));
    }
}
