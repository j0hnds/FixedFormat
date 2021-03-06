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

import org.hephaestus.fixedformat.ValueCodec;
import org.hephaestus.fixedformat.impl.StringCodec;

import junit.framework.TestCase;

public class TestStringCodec extends TestCase {
    private ValueCodec codec;

    protected void setUp() throws Exception {
        codec = new StringCodec();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testEncodeValueNullPaddable() {
        codec.setPaddable(true);
        assertEquals("    ", codec.encodeValue(null, 4));
    }

    public void testEncodeValueNullNotPaddable() {
        try {
            assertEquals("    ", codec.encodeValue(null, 4));
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e) {

        }
    }

    public void testEncodeValueEmptyStringPaddable() {
        codec.setPaddable(true);
        assertEquals("    ", codec.encodeValue("", 4));
    }

    public void testEncodeValueEmptyStringNotPaddable() {
        assertEquals("", codec.encodeValue("", 4));
    }

    public void testEncodeValueShortStringNotPadded() {
        assertEquals("23", codec.encodeValue(new Long(23L), 4));
    }

    public void testEncodeValueShortStringPadded() {
        codec.setPaddable(true);
        assertEquals("23  ", codec.encodeValue(new Long(23L), 4));
    }

    public void testEncodeValueEqualString() {
        assertEquals("abcd", codec.encodeValue("abcd", 4));
    }

    public void testEncodeValueLongStringTrucated() {
        codec.setTruncatable(true);
        assertEquals("abcd", codec.encodeValue("abcde", 4));
    }

    public void testEncodeValueLongStringNotTrucated() {
        try {
            codec.encodeValue("abcde", 4);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e) {
        }
    }

    public void testDecodeValueNullString() {
        try {
            codec.decodeValue(null);
            fail("Should have thrown an exception");
        }
        catch (IllegalArgumentException e) {

        }
    }

    public void testDecodeValueEmptyString() {
        assertEquals("", codec.decodeValue(""));
    }

    public void testDecodeValueAllSpacesStringNotPaddable() {
        assertEquals("    ", codec.decodeValue("    "));
    }

    public void testDecodeValueAllSpacesStringPaddable() {
        codec.setPaddable(true);
        assertEquals("", codec.decodeValue("    "));
    }

    public void testDecodeValueLeftPaddedNoPadding() {
        assertEquals("  TestString", codec.decodeValue("  TestString"));
    }

    public void testDecodeValueLeftPaddedPadding() {
        codec.setPaddable(true);
        assertEquals("TestString", codec.decodeValue("TestString  "));
    }

    public void testDecodeValueRightPadded() {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.RIGHT_JUSTIFIED);
        assertEquals("TestString", codec.decodeValue("  TestString"));
    }

    public void testDecodeValueRightLeftPadded() {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.CENTER_JUSTIFIED);
        assertEquals("TestString", codec.decodeValue("  TestString  "));
    }

    public void testEncodeValueCenterPadding() {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.CENTER_JUSTIFIED);
        codec.setPadCharacter('*');

        assertEquals("TestString", codec.encodeValue("TestString", 10));
        assertEquals("TestString*", codec.encodeValue("TestString", 11));
        assertEquals("*TestString*", codec.encodeValue("TestString", 12));
    }

    public void testDecodeValueCenterPaddingWStrangePad() {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.CENTER_JUSTIFIED);
        codec.setPadCharacter('*');

        assertEquals("TestString", codec.decodeValue("TestString"));
        assertEquals("TestString", codec.decodeValue("TestString*"));
        assertEquals("TestString", codec.decodeValue("*TestString*"));

    }

}
