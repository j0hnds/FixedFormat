package org.opentools.fixedformat.impl;

import org.opentools.fixedformat.ObjectPopulator;

import junit.framework.TestCase;

public class TestBeanPopulator extends TestCase
{
    private BeanToTest beanToTest;
    private ObjectPopulator populator;
    
    protected void setUp() throws Exception
    {
        beanToTest = new BeanToTest();
        populator = new BeanPopulator();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testPopulateValueNullBean()
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
            populator.populateValue(beanToTest, null, "value");
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
            populator.populateValue(beanToTest, "", "value");
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testPopulateValueGood()
    {
        populator.populateValue(beanToTest, "name", "value");
        
        assertEquals("value", beanToTest.getName());
    }

    public void testPopulateValueNull()
    {
        populator.populateValue(beanToTest, "name", null);
        
        assertNull(beanToTest.getName());
    }

    public static class BeanToTest
    {
        private String name;
        
        public BeanToTest()
        {
            
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }
        
        
    }
}
