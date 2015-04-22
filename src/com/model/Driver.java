package com.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Justin Hyland
 */
import java.util.Random;
import com.model.MemoryManagerModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author hylan_000
 */
public class Driver implements ActionListener {

    private final Random random;

    private Timer timer;
    private MemoryManagerModel memoryManager;
    private static final int ALLOCATE = 1;
    private static final int DEALLOCATE = 0;

    public Driver(MemoryManagerModel memory) {

        random = new Random();
        memoryManager = memory;

         Timer timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                drive();
                

            }
        });
            timer.start();

      

    }//end constructor

    public void drive() {

        HashMap<Integer, Boolean> processIDs;

        processIDs = memoryManager.getProcessIDs();

        int processID;

        //allocated memory from 1-100 randomly 
        memoryManager.allocateMemory((long) random.nextInt(10) + 1, random.nextInt(10) + 1);

        boolean found = false;
        while (found == false) {

            processID = random.nextInt(10) + 1;
            if (processIDs.containsKey(processID)) { //finds a process id thats already in there

                memoryManager.deallocateMemory(processID);
                found = true;
            } //end if
            //end if

        }//end while

    }//ends drive

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}//end Driver
