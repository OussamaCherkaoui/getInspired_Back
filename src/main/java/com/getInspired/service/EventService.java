package com.getInspired.service;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.exception.EventNotFoundException;
import com.getInspired.model.Event;
import com.getInspired.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getAllEvent() {
        List<Event> events = eventRepository.findAllByOrderByDateDesc();
        if (events.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return events;
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event getByIdEvent(Long id) throws EventNotFoundException {
        return eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
    }
    public Event deleteEvent(Long id) throws EventNotFoundException {
        Event event = eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
        eventRepository.delete(event);
        return event;
    }
    public Long countEvent(){
        return eventRepository.countEvent();
    }

    public List<Event> getAllEventByDate(LocalDate date) {
        List<Event> events = eventRepository.findAllByDateOrderByDateDesc(date);
        if (events.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return events;
    }
}
