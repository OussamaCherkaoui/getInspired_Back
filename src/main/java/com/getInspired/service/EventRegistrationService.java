package com.getInspired.service;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.EventRegistration;
import com.getInspired.repository.EventRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventRegistrationService {
    private final EventRegistrationRepository eventRegistrationRepository;

    public List<EventRegistration> getAll() {
        List<EventRegistration> eventRegistrations = eventRegistrationRepository.findAll();
        if (eventRegistrations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return eventRegistrations;
    }

    public EventRegistration save(EventRegistration eventRegistration) {
        return eventRegistrationRepository.save(eventRegistration);
    }

    public EventRegistration confirmRegistration(Long id) {
        EventRegistration eventRegistration = eventRegistrationRepository.findById(id).get();
        eventRegistration.setIsConfirmed(true);
        return eventRegistrationRepository.save(eventRegistration);
    }


}
