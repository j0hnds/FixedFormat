package org.hephaestus.textutils;
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

import org.hephaestus.textutils.NumberUtils;

import junit.framework.TestCase;

public class TestNumberUtils extends TestCase
{

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testZeroPadLongZero()
    {
        assertEquals("00000", NumberUtils.zeroPadLong(0L, 5));
    }
    
    public void testZeroPadLongShortNumber()
    {
        assertEquals("00023", NumberUtils.zeroPadLong(23L, 5));
    }
    
    public void testZeroPadLongEqualNumber()
    {
        assertEquals("12345", NumberUtils.zeroPadLong(12345L, 5));
    }
    
    public void testZeroPadLongBigNumber()
    {
        try 
        {
            NumberUtils.zeroPadLong(1234567890L, 5);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

}
