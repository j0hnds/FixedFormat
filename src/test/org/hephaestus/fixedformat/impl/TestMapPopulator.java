package org.hephaestus.fixedformat.impl;

import java.util.HashMap;
import java.util.Map;

import org.hephaestus.fixedformat.ObjectPopulator;
import org.hephaestus.fixedformat.impl.MapPopulator;

import junit.framework.TestCase;

public class TestMapPopulator extends TestCase
{
    private ObjectPopulator populator;
    private Map<String,Object> testMap;
    
    protected void setUp() throws Exception
    {
        populator = new MapPopulator();
        testMap = new HashMap<String,Object>();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testPopulateValueNullMap()
    {
        try 
        {
            populator.populateValue(null, "name", "value");
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testPopulateValueNullPropertyName()
    {
        try 
        {
            populator.populateValue(testMap, null, "value");
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testPopulateValueEmptyPropertyName()
    {
        try 
        {
            populator.populateValue(testMap, "", "value");
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testPopulateValueGood()
    {
        populator.populateValue(testMap, "name", "value");
        
        assertTrue(testMap.containsKey("name"));
        assertEquals("value", testMap.get("name"));
    }

    public void testPopulateValueNull()
    {
        populator.populateValue(testMap, "name", null);
        
        assertTrue(testMap.containsKey("name"));
        assertNull(null, testMap.get("name"));
    }
}
