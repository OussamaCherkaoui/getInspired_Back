package com.getInspired;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.EventRegistration;
import com.getInspired.repository.EventRegistrationRepository;
import com.getInspired.service.EventRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EventRegistrationServiceTest {

    @MockBean
    private EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetAllRegistrationWhenNoRegistrationsFound() {
        when(eventRegistrationRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(DatabaseEmptyException.class, () -> eventRegistrationService.getAllRegistration());
    }

    @Test
    void testConfirmRegistration() {
        Long registrationId = 1L;
        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setId(registrationId);
        eventRegistration.setIsConfirmed(false);

        when(eventRegistrationRepository.findById(registrationId)).thenReturn(Optional.of(eventRegistration));
        when(eventRegistrationRepository.save(eventRegistration)).thenReturn(eventRegistration);

        EventRegistration confirmedRegistration = eventRegistrationService.confirmRegistration(registrationId);


        assertTrue(confirmedRegistration.getIsConfirmed());
        verify(eventRegistrationRepository).findById(registrationId);
        verify(eventRegistrationRepository).save(eventRegistration);
    }
}
