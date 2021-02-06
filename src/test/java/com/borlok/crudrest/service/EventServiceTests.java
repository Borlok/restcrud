package com.borlok.crudrest.service;

import com.borlok.crudrest.model.Event;
import com.borlok.crudrest.model.User;
import com.borlok.crudrest.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTests {

    @Mock
    private Event event;

    @Mock
    private Event returnedEvent;

    @Spy
    private EventService eventService;

    @Spy
    private EventRepository eventRepository;

    @Mock
    private EventRepository eventRepository2;

    @Before
    public void setUp() {
        when(event.getId()).thenReturn(1);
        when(event.getName()).thenReturn("Miting");
        when(eventRepository2.getOne(1)).thenReturn(returnedEvent);
        when(returnedEvent.getId()).thenReturn(1);
        when(returnedEvent.getName()).thenReturn("Miting");

    }

    @Test
    public void getEventById() {
        Event event1 = eventRepository2.getOne(1);
        System.out.println(event1.getName());
        assertEquals(event.getName(), event1.getName());
    }

}
