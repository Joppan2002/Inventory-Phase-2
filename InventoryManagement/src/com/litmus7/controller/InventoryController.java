package com.litmus7.controller;

import java.io.File;
import com.litmus7.services.InventoryService;

public class InventoryController {

    private final InventoryService service = new InventoryService();

    public int triggerPhase2Processing() {
        File[] files = service.getCsvFiles().getData();
        if (files == null || files.length == 0) {
            System.out.println("No CSV files found in input folder.");
            return 0;
        }

        int processedCount =0;
        int positive =0;
        int negative =0;

        Thread[] threads = new Thread[files.length];

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            threads[i] = new Thread(() -> 
            {
                boolean result = service.processSingleFile(file).getData();
//                if (result) {
//                    positive++;
//                } else {
//                    negative++;
//                }
            });
            threads[i].start();
            processedCount++;
        }
        
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
       

        return processedCount;
    }
}
