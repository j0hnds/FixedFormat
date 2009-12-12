package org.hephaestus.fixedformat.impl;
/*
 * Copyright (c) 2009 Dave Sieh
 *
 * This file is part of FixedFormat.
 *
 * FixedFormat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FixedFormat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FixedFormat.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.hephaestus.fixedformat.ObjectPopulator;
import org.hephaestus.fixedformat.impl.BeanPopulator;

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
