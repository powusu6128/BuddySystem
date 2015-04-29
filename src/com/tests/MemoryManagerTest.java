package com.tests;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.model.BlockOMemory;
import com.model.MemoryManagerModel;

import static org.junit.Assert.*;

/**
 * MemoryManagerModel Class JUnit Tests
 *
 * @author Justin Hyland
 */
public class MemoryManagerTest {

    public MemoryManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Test exception on constructor, of class MemoryManagerModel
     */
    @Test
    public void testExceptionConstructor() {
        System.out.println("ExceptionConstructor");
        exception.expect(IllegalArgumentException.class);
        // Max is not a power of 2
        new MemoryManagerModel(2, 65);
        // Min is not a power of 2
        new MemoryManagerModel(3, 64);
        //Both min and max is not a power of 2
        new MemoryManagerModel(3, 65);
    }

    /**
     * Test of getMinMemorySize method, of class MemoryManagerModel.
     */
    @Test
    public void testGetMinMemorySize() {
        System.out.println("getMinMemorySize");

        MemoryManagerModel memory = new MemoryManagerModel(0, 1024);

        double expResult = 0;
        double result = memory.getMinMemorySize();
        assertEquals(expResult, result, 0.000000001);

    }

    /**
     * Test of getMaxMemorySize method, of class MemoryManagerModel.
     */
    @Test
    public void testGetMaxMemorySize() {
        System.out.println("getMaxMemorySize");
        MemoryManagerModel memory = new MemoryManagerModel(2, 1024);
        double expResult = 1024;
        double result = memory.getMaxMemorySize();
        assertEquals(expResult, result, 0.000000001);

    }

    /**
     * Test of allocateMemory method, of class MemoryManagerModel.
     */
    @Test
    public void testAllocateMemory() {
        System.out.println("allocateMemory");

        MemoryManagerModel instance = new MemoryManagerModel(1, 64);
        int[] expected = { 0, 0, 0, 0, 0, 0, 1 };
        int[] result = instance.getFreeMemory();
        for (int i = 0; i < result.length; i++) {
            assertEquals(expected[i], result[i]);
        }

        instance.allocateMemory(7, 5);
        int[] expected2 = { 0, 0, 0, 1, 1, 1, 0 };
        int[] result2 = instance.getFreeMemory();
        for (int i = 0; i < result2.length; i++) {
            assertEquals(expected2[i], result2[i]);
        }

        instance.allocateMemory(7, 5);
        int[] expected3 = { 0, 0, 0, 1, 1, 1, 0 };
        int[] result3 = instance.getFreeMemory();
        for (int i = 0; i < result3.length; i++) {
            assertEquals(expected3[i], result3[i]);
        }

        instance.allocateMemory(7, 6);
        int[] expected4 = { 0, 0, 0, 0, 1, 1, 0 };
        int[] result4 = instance.getFreeMemory();
        for (int i = 0; i < result4.length; i++) {
            assertEquals(expected4[i], result4[i]);
        }

        instance.allocateMemory(30, 11);
        int[] expected5 = { 0, 0, 0, 0, 1, 0, 0 };
        int[] result5 = instance.getFreeMemory();
        for (int i = 0; i < result5.length; i++) {
            assertEquals(expected5[i], result5[i]);
        }

        instance.allocateMemory(65, 3);
        int[] expected6 = { 0, 0, 0, 0, 1, 0, 0 };
        int[] result6 = instance.getFreeMemory();
        for (int i = 0; i < result6.length; i++) {
            assertEquals(expected6[i], result6[i]);
        }
    }

    /**
     * Test of deallocateMemory method, of class MemoryManagerModel.
     */
    @Test
    public void testDeallocateMemory() {
        System.out.println("deallocateMemory");

        MemoryManagerModel instance = new MemoryManagerModel(1, 64);
        System.out.println(instance);
        instance.allocateMemory(8, 4);
        instance.allocateMemory(16, 9);
        instance.allocateMemory(9, 56);
        instance.allocateMemory(2, 89);
        instance.allocateMemory(15, 99);

        int[] expected = { 0, 1, 1, 0, 0, 0, 0 };
        int[] result = instance.getFreeMemory();
        for (int i = 0; i < result.length; i++) {
            assertEquals(expected[i], result[i]);
        }

        instance.deallocateMemory(89);
        int[] expected2 = { 0, 0, 0, 1, 0, 0, 0 };
        int[] result2 = instance.getFreeMemory();
        for (int i = 0; i < result2.length; i++) {
            assertEquals(expected2[i], result2[i]);
        }

        instance.deallocateMemory(4);
        int[] expected7 = { 0, 0, 0, 0, 1, 0, 0 };
        int[] result7 = instance.getFreeMemory();
        for (int i = 0; i < result7.length; i++) {
            assertEquals(expected7[i], result7[i]);
        }

        instance.deallocateMemory(56);
        int[] expected3 = { 0, 0, 0, 0, 2, 0, 0 };
        int[] result3 = instance.getFreeMemory();
        for (int i = 0; i < result3.length; i++) {
            assertEquals(expected3[i], result3[i]);
        }

        instance.deallocateMemory(56);
        int[] expected4 = { 0, 0, 0, 0, 2, 0, 0 };
        int[] result4 = instance.getFreeMemory();
        for (int i = 0; i < result4.length; i++) {
            assertEquals(expected4[i], result4[i]);
        }

        instance.deallocateMemory(9);
        int[] expected5 = { 0, 0, 0, 0, 1, 1, 0 };
        int[] result5 = instance.getFreeMemory();
        for (int i = 0; i < result5.length; i++) {
            assertEquals(expected5[i], result5[i]);
        }

        instance.deallocateMemory(99);
        int[] expected6 = { 0, 0, 0, 0, 0, 0, 1 };
        int[] result6 = instance.getFreeMemory();
        for (int i = 0; i < result6.length; i++) {
            assertEquals(expected6[i], result6[i]);
        }
    }

    /**
     * Test of getMemoryBlocks method, of class MemoryManagerModel.
     */
    @Test
    public void testGetMemoryBlocks() {
        System.out.println("getMemoryBlocks");
        MemoryManagerModel memory = new MemoryManagerModel(1, 64);
        ArrayList<BlockOMemory> result = memory.getMemoryBlocks();
        memory.allocateMemory(32, 9);
        int expResult = 2;
        assertEquals(expResult, result.size());
        memory.allocateMemory(4, 5);
        expResult = 5;
        result = memory.getMemoryBlocks();
        assertEquals(expResult, result.size());
        memory.deallocateMemory(5);
        expResult = 2;
        result = memory.getMemoryBlocks();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getProcessIDs method, of class MemoryManagerModel.
     */
    @Test
    public void testGetProcessIDs() {
        System.out.println("getProcessIDs");
        MemoryManagerModel memory = new MemoryManagerModel(1, 64);
        memory.allocateMemory(32, 55);
        HashMap<Integer, Boolean> expResult = new HashMap<>();
        expResult.put(55, true);
        HashMap<Integer, Boolean> result = memory.getProcessIDs();
        assertEquals(expResult, result);
        memory.allocateMemory(16, 99);
        expResult.put(99, true);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFreeMemory method, of class MemoryManagerModel.
     */
    @Test
    public void testGetFreeMemory() {
        System.out.println("getFreeMemory");

        MemoryManagerModel memory = new MemoryManagerModel(1, 64);

        int expResultLength = 7;
        int result = memory.getFreeMemory().length;
        assertEquals(expResultLength, result);
    }

    /**
     * Test of toString method, of class MemoryManagerModel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MemoryManagerModel instance = new MemoryManagerModel(1, 64);
        String expResult = "MemoryManager [minMemory=1, maxMemory=64, FreeMemory={[0, 0, 0, 0, 0, 0, 1],\n"
                           + "MemoryBlocks=\n"
                           + "{Index 0: BlockOMemory [memorySize=64, processSize=0.0, haveProcess=false, processID=-1, parent=null],\n"
                           + "}]";
        String result = instance.toString();
        System.out.println("RESULT = " + result);
        assertEquals(expResult, result);
    }
}
