/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * BlockOMemory Class JUnit Tests
 * 
 * @author Justin Hyland
 */
public class BlockOMemoryTest {
	private static final double DELTA = 0.0000000001;
    
    public BlockOMemoryTest() {
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
     * Test of getProcessSize method, of class BlockOMemory.
     */
    @Test
    public void testGetProcessSize() {
        System.out.println("getProcessSize");
        BlockOMemory instance = new BlockOMemory(32,16,true,null,0);
        BlockOMemory instance1 = new BlockOMemory(32,8,true,null,0);
        BlockOMemory instance2 = new BlockOMemory(32,256,true,null,0);
        BlockOMemory instance3 = new BlockOMemory(32,64,true,null,0);
        
        double expResult = 16;
        double result = instance.getProcessSize();
        
        double expResult1 = 8;
        double result1 = instance1.getProcessSize();
        
        double expResult2 = 256;
        double result2 = instance2.getProcessSize();
        
        double expResult3 = 64;
        double result3 = instance3.getProcessSize();
        assertEquals(expResult, result,  DELTA);
        assertEquals(expResult1, result1,DELTA);
        assertEquals(expResult2, result2,DELTA);
        assertEquals(expResult3, result3,DELTA);
        
        
    }

    /**
     * Test of setProcessSize method, of class BlockOMemory.
     */
    @Test
    public void testSetProcessSize() {
        System.out.println("setProcessSize");
        double processSize = 32;
        BlockOMemory instance = new BlockOMemory(32,16,true,null,0);
        instance.setProcessSize(processSize);
        assertEquals(processSize, instance.getProcessSize(), 0.0000000001);
    }

    /**
     * Test of isProcess method, of class BlockOMemory.
     */
    @Test
    public void testIsProcess() {
        System.out.println("isProcess");
        BlockOMemory instance = new BlockOMemory(32,16,true,null,0);
        BlockOMemory instance1 = new BlockOMemory(32,16,false,null,0);
        BlockOMemory instance2 = new BlockOMemory(32,16,true,null,0);
        BlockOMemory instance3 = new BlockOMemory(32,16,false,null,0);
        boolean expResult = true;
        boolean result = instance.isProcess();
        assertEquals(expResult, result);
        
        boolean expResult1 = false;
        boolean result1 = instance1.isProcess();
        assertEquals(expResult1, result1);
        
        boolean expResult2 = true;
        boolean result2 = instance2.isProcess();
        assertEquals(expResult2, result2);
        
        boolean expResult3 = false;
        boolean result3 = instance3.isProcess();
        assertEquals(expResult3, result3);
        
       
    }

    /**
     * Test of setIsProcess method, of class BlockOMemory.
     */
    @Test
    public void testSetIsProcess() {
        System.out.println("setIsProcess");
        boolean haveProcess = false;
        boolean haveProcess1 = false;
        boolean haveProcess2 = false;
        boolean haveProcess3 = false;
        
        BlockOMemory instance = new BlockOMemory(32,16,true,null,0);
        BlockOMemory instance1 = new BlockOMemory(32,16,false,null,0);
        BlockOMemory instance2 = new BlockOMemory(32,16,true,null,0);
        BlockOMemory instance3 = new BlockOMemory(32,16,false,null,0);
        
        instance.setIsProcess(haveProcess);
        instance1.setIsProcess(haveProcess1);
        instance2.setIsProcess(haveProcess2);
        instance3.setIsProcess(haveProcess3);
        
        boolean expResult = haveProcess;
        boolean result = instance.isProcess();
        assertEquals(expResult, result );
        
        boolean expResult1 = haveProcess1;
        boolean result1 = instance1.isProcess();
        assertEquals(expResult1, result1);
        
        boolean expResult2 = haveProcess2;
        boolean result2 = instance2.isProcess();
        assertEquals(expResult2, result2);
        
        boolean expResult3 = haveProcess2;
        boolean result3 = instance3.isProcess();
        assertEquals(expResult3, result3);
        
       
    }

    /**
     * Test of getMemorySize method, of class BlockOMemory.
     */
    @Test
    public void testGetMemorySize() {
        System.out.println("getMemorySize");
         BlockOMemory instance = new BlockOMemory(32,16,true,null,0);
         BlockOMemory instance1 = new BlockOMemory(64,16,true,null,0);
        long expResult = 32;
        long result = instance.getMemorySize();
        assertEquals(expResult, result);
        
        long expResult1 = 64;
        long result1 = instance1.getMemorySize();
        assertEquals(expResult1, result1);
        
    }

    /**
     * Test of getProcessID method, of class BlockOMemory.
     */
    @Test
    public void testGetProcessID() {
        System.out.println("getProcessID");
       BlockOMemory instance = new BlockOMemory(32,16,true,null,2);
       BlockOMemory instance1 = new BlockOMemory(64,16,true,null,34);
        int expResult = 2;
        int result = instance.getProcessID();
        assertEquals(expResult, result);
        
        int expResult1 = 34;
        int result1 = instance1.getProcessID();
        assertEquals(expResult1, result1);
        
    }

    /**
     * Test of setMemorySize method, of class BlockOMemory.
     */
    @Test
    public void testSetMemorySize() {
        System.out.println("setMemorySize");
        long size = 21223324;
        long size1 = 642342;
        long size2 = 122342348;
        
        BlockOMemory instance = new BlockOMemory(size,0,true,null,1);
        BlockOMemory instance1 = new BlockOMemory(size1,0,true,null,2);
        BlockOMemory instance2 = new BlockOMemory(size2,0,true,null,3);
        long expResult = size;
        long result = instance.getMemorySize();
        assertEquals(expResult, result );
        
        long expResult1 = size1;
        long result1 = instance1.getMemorySize();
        assertEquals(expResult1, result1);
        
        long expResult2 = size2;
        long result2 = instance2.getMemorySize();
        assertEquals(expResult2, result2);
        
    }

    /**
     * Test of setProcessID method, of class BlockOMemory.
     */
    @Test
    public void testSetProcessID() {
        System.out.println("setProcessID");
        int id = 5;
        int id1 = 64;
        int id2 = 65;
        
        BlockOMemory instance = new BlockOMemory(32,16,true,null,2);
        BlockOMemory instance1 = new BlockOMemory(32,16,true,null,3);
        BlockOMemory instance2 = new BlockOMemory(32,16,true,null,5);
        instance.setProcessID(id);
        instance1.setProcessID(id1);
        instance2.setProcessID(id2);
        
        int expResult = id;
        int result = instance.getProcessID();
        assertEquals(expResult, result );
        
        int expResult1 = id1;
        int result1 = instance1.getProcessID();
        assertEquals(expResult1, result1);
        
        int expResult2 = id2;
        int result2 = instance2.getProcessID();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of toString method, of class BlockOMemory.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        
        BlockOMemory instance = new BlockOMemory(32,16,true,null,2);
        
        /*String expResult = "BlockOMemory [memorySize=" + 32 + ", processSize="
				+ "16.0" + ", haveProcess=" + true + ", processID="
				+ 1 + ", buddy=" + 2 + "]";*/
        String expResult = "BlockOMemory [memorySize=32, processSize=16.0, haveProcess=true, processID=2, parent=null]";
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);
       
    }
    
}
