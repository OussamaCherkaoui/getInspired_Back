package com.getInspired.service;

import com.getInspired.dto.EventRegistrationDto;
import com.getInspired.dto.EventRegistrationReadDto;
import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.EventRegistration;
import com.getInspired.model.Reservation;
import com.getInspired.repository.EventRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventRegistrationService {
    private final EventRegistrationRepository eventRegistrationRepository;

    public List<EventRegistration> getAllRegistration() {
        List<EventRegistration> eventRegistrations = eventRegistrationRepository.findAll();
        if (eventRegistrations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return eventRegistrations;
    }

    public EventRegistration saveEventRegistration(EventRegistration eventRegistration) {
        return eventRegistrationRepository.save(eventRegistration);
    }

    public EventRegistration confirmRegistration(Long id) {
        EventRegistration registration = eventRegistrationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No registration found with id: " + id));

        registration.setIsConfirmed(true);
        return eventRegistrationRepository.save(registration);
    }


    public List<EventRegistrationReadDto> getAllEventRegistrationByIdEvent(Long id) {
        List<EventRegistrationReadDto> eventRegistrations = eventRegistrationRepository.findAllByEvent_Id(id);
        if (eventRegistrations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return eventRegistrations;
    }

    public EventRegistration cancelRegistration(Long id) {
        EventRegistration registration = eventRegistrationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No registration found with id: " + id));

        registration.setIsConfirmed(false);
        return eventRegistrationRepository.save(registration);
    }

    public List<EventRegistrationReadDto> getEventsRegistrationsByIdMember(Long id) {
        List<EventRegistrationReadDto> eventRegistrations = eventRegistrationRepository.findAllByIdMember(id);
        if (eventRegistrations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return eventRegistrations;
    }

    public boolean deleteRegistration(Long id) {
        Optional<EventRegistration> registration = eventRegistrationRepository.findById(id);
        if (registration.isPresent()){
            eventRegistrationRepository.delete(registration.get());
            return true;
        }
        return false;
    }
}
