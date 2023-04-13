package com.myflux.myflux.filter;

import org.springframework.stereotype.Component;

import java.util.*;

public class EventNotify {

    private Queue<String> events = new LinkedList<>();

    private boolean change = false;

    public void add(String data) {
        this.events.add(data);
        change = true;
    }

    public Queue<String> getEvents() {
        return this.events;
    }

    public boolean getChange() {
        return this.change;
    }

    public void setChange() {
        this.change = false;
    }
}
