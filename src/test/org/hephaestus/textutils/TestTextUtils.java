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

import org.hephaestus.textutils.TextUtils;

import junit.framework.TestCase;

public class TestTextUtils extends TestCase
{

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testIsBlankNullString()
    {
        assertTrue(TextUtils.isBlank(null));
    }
    
    public void testIsBlankZeroLengthString()
    {
        assertTrue(TextUtils.isBlank(""));
    }
    
    public void testIsBlankSpaceOnlyString()
    {
        assertTrue(TextUtils.isBlank("     "));
    }
    
    public void testIsBlankNonBlankString()
    {
        assertFalse(TextUtils.isBlank("  TestString  "));
    }
    
    public void testRightJustifyTextNullString()
    {
        assertEquals("    ", TextUtils.rightJustifyText(null, 4, ' '));
    }
    
    public void testRightJustifyTextEmptyString()
    {
        assertEquals("****", TextUtils.rightJustifyText("", 4, '*'));
    }
    
    public void testRightJustifyTextShortString()
    {
        assertEquals("**aa", TextUtils.rightJustifyText("aa", 4, '*'));
    }
    
    public void testRightJustifyTextEqualString()
    {
        assertEquals("abcd", TextUtils.rightJustifyText("abcd", 4, ' '));
    }
    
    public void testRightJustifyTextLongString()
    {
        assertEquals("abcde", TextUtils.rightJustifyText("abcde", 4, ' '));
    }

}
