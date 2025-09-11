package com.litmus7.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.litmus7.services.InventoryService;

public class InventoryController {

    private final InventoryService service = new InventoryService();

    public int[] triggerPhase2Processing() {
        File[] files = service.getCsvFiles().getData();
        if (files == null || files.length == 0) {
            System.out.println("No CSV files found in input folder.");
            return new int[]{0, 0, 0};
        }

        AtomicInteger positive = new AtomicInteger(0);
        AtomicInteger negative = new AtomicInteger(0);
        List<Thread> threads = new ArrayList<>();

        for (File file : files) {
            Thread thread = new Thread(() -> {
                boolean result = service.processSingleFile(file).getData();
                if (result) {
                    positive.incrementAndGet();
                } else {
                    negative.incrementAndGet();
                }
            });

            threads.add(thread);
            thread.start();
        }


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted while waiting: " + e.getMessage());
            }
        }

        int processedCount = files.length;
        return new int[]{processedCount, positive.get(), negative.get()};
    }
}
