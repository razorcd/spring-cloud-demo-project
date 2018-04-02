package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private List<String> store = new ArrayList<>();

    public synchronized void add(String id, String data) {
        store.add(id + " " + data);
    }

    public List<String> getAll() {
        return store;
    }


}
