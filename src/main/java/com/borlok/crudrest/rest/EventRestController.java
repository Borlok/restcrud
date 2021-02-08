package com.borlok.crudrest.rest;

import com.borlok.crudrest.dto.EventDto;
import com.borlok.crudrest.model.Event;
import com.borlok.crudrest.model.User;
import com.borlok.crudrest.service.EventService;
import com.borlok.crudrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/events")
public class EventRestController {
    private EventService eventService;
    private UserService userService;

    @Autowired
    public EventRestController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public EventDto getById (@PathVariable("id") Integer id) {
        return EventDto.fromEvent(eventService.getById(id));
    }


    @GetMapping
    public List<EventDto> getAll() {
        return eventService.getAll().stream().map(EventDto::fromEvent).collect(Collectors.toList());
    }

    @PostMapping
    public EventDto create (@RequestBody EventDto eventDto) {
        Event event = eventDto.toEvent();
        User user = new User();
        if (eventDto.getUser_id() != 0)
            user = userService.getById(eventDto.getUser_id());
        user.getEvents().add(event);
        event.setUser(user);
        return EventDto.fromEvent(eventService.create(event));
    }

    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable("id") Integer id) {
        eventService.deleteById(id);
    }

}
