package com.litmus7.InventoryManagement;

import com.litmus7.controller.InventoryController;


public class App {
    public static void main(String[] args) {
        InventoryController controller = new InventoryController();


        int processedCount = controller.triggerPhase2Processing();

        System.out.println("Phase 2 processing finished.");
        System.out.println("Total files submitted: " + processedCount);
    }
}
