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
import org.junit.Test;
import static org.junit.Assert.*;

/**
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

    /**
     * Test of getMinMemorySize method, of class MemoryManager.
     */
    @Test
    public void testGetMinMemorySize() {
        System.out.println("getMinMemorySize");
        
        MemoryManager memory = new MemoryManager(0, 1024);
                 
        double expResult = 0;
        double result = memory.getMinMemorySize();
        assertEquals(expResult, result, 0.000000001);
       
        
    }

    /**
     * Test of getMaxMemorySize method, of class MemoryManager.
     */
    @Test
    public void testGetMaxMemorySize() {
        System.out.println("getMaxMemorySize");
       MemoryManager memory = new MemoryManager(2, 1024);
        double expResult = 1024;
        double result = memory.getMaxMemorySize();
        assertEquals(expResult, result, 0.000000001);
       
    }

    /**
     * Test of allocateMemory method, of class MemoryManager.
     */
    @Test
    public void testAllocateMemory() {
        System.out.println("allocateMemory");
   
        MemoryManager instance = new MemoryManager(1,64);
        instance.allocateMemory(7, 5);
        System.out.println(instance.toString());
        instance.allocateMemory(7, 5);
        System.out.println(instance.toString());
        instance.allocateMemory(7, 6);
        System.out.println(instance.toString());
        instance.allocateMemory(30, 11);
        
        instance.allocateMemory(65, 3);
        
        
        int expected = 1;
        int[] result = instance.getFreeMemory();
        
        
//        int expected1 = 0;
//        int result1 = 0;
//        
//        int expected2 = 0;
//        int result2 = 0;
//        
//        int expected3 = 0;
//        int result3 = 0;
        
        assertEquals(expected,result[4]);
//        assertEquals(expected,result);
//        assertEquals(expected,result);
//        assertEquals(expected,result);
//        
        System.out.println(instance.toString());
        
        
        
     
    }

    /**
     * Test of deallocateMemory method, of class MemoryManager.
     */
    @Test
    public void testDeallocateMemory() {
        System.out.println("deallocateMemory");
        
        MemoryManager instance = new MemoryManager(1,64);
	instance.allocateMemory(32, 4);
	instance.allocateMemory(16, 9);
	instance.allocateMemory(32, 56);
        instance.allocateMemory(2, 89);
        instance.deallocateMemory(89);
        System.out.println(instance.toString());
        instance.deallocateMemory(56);
        System.out.println(instance.toString());
        instance.deallocateMemory(9);
        System.out.println(instance.toString());
        instance.deallocateMemory(4);
        System.out.println(instance.toString());
    }

    /**
     * Test of getMemoryBlocks method, of class MemoryManager.
     */
    @Test
    public void testGetMemoryBlocks() {
        System.out.println("getMemoryBlocks");

        ArrayList<BlockOMemory> result = MemoryManager.getMemoryBlocks();

        MemoryManager memory = new MemoryManager(1, 64);
       
        
       // ArrayList<BlockOMemory> expResult = 
         //       assertEquals(expResult, result);

    }

    /**
     * Test of getProcessIDs method, of class MemoryManager.
     */
    @Test
    public void testGetProcessIDs() {
        System.out.println("getProcessIDs");
        MemoryManager memory = new MemoryManager(1,64);
       //HashMap<Integer, Boolean> expResult =  
       //HashMap<Integer, Boolean> result = memory.getProcessIDs();
       //assertEquals(expResult, result);
        
    }

    /**
     * Test of getFreeMemory method, of class MemoryManager.
     */
    @Test
    public void testGetFreeMemory() {
        System.out.println("getFreeMemory");

        MemoryManager memory = new MemoryManager(1, 64);

        int expResultLength = 7;
        int result = memory.getFreeMemory().length;
        assertEquals(expResultLength, result);
        
        
    }
    /**
     * Test of toString method, of class MemoryManager.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MemoryManager instance = new MemoryManager(1,64);
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
      
    }
    
}
