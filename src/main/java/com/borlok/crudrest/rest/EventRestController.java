package com.borlok.crudrest.rest;

import com.borlok.crudrest.model.Event;
import com.borlok.crudrest.model.User;
import com.borlok.crudrest.repository.EventRepository;
import com.borlok.crudrest.service.EventService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventRestController {
    private EventService eventService;

    @Autowired
    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public Event getById (@PathVariable("id") Integer id) {
        return eventService.getById(id);
    }


    @GetMapping
    public List<Event> getAll() {
        return eventService.getAll();
    }

    @PostMapping
    public Event create (Event event) {
        return eventService.create(event);
    }

}
