package com.litmus7.InventoryManagement;

import com.litmus7.controller.InventoryController;

public class App {
    public static void main(String[] args) {
        InventoryController controller = new InventoryController();

        System.out.println("Starting Phase 2 processing (single-threaded)...");

        int processedCount[] = controller.triggerPhase2Processing();

        System.out.println("Phase 2 processing finished.");
        System.out.println("Total files processed: " + processedCount[0]);
        System.out.println("Moved to processed: "+processedCount[1]);
        System.out.println("Moved to error: "+processedCount[2]);
        
        
    }
}
