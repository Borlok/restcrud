package com.borlok.crudrest.service;

import com.borlok.crudrest.model.Event;
import com.borlok.crudrest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService {
    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventService() {
    }

    public Event create(Event event) {
        eventRepository.save(event);
        eventRepository.flush();
        return event;
    }

    public Event getById(int id) {
        List<Event> event = eventRepository.findAll();
        System.out.println(event);
        return event.get(0);
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

}
