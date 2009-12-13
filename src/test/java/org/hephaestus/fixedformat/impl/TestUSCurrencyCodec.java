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

import java.math.BigDecimal;

import org.hephaestus.fixedformat.ValueCodec;
import org.hephaestus.fixedformat.impl.USCurrencyCodec;

import junit.framework.TestCase;

public class TestUSCurrencyCodec extends TestCase {
    private ValueCodec codec;

    protected void setUp() throws Exception {
        codec = new USCurrencyCodec();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testEncodeValueNull() {
        try {
            codec.encodeValue(null, 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e) {

        }
    }

    public void testEncodeValueWrongType() {
        try {
            codec.encodeValue("", 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e) {

        }
    }

    public void testEncodeValueTooLong() {
        try {
            codec.encodeValue(new BigDecimal("23.1"), 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e) {

        }
    }

    public void testEncodeValueShort() {
        codec.setPaddable(true);
        codec.setPadCharacter('0');
        codec.setJustification(ValueCodec.RIGHT_JUSTIFIED);
        assertEquals("002310", codec.encodeValue(new BigDecimal("23.1"), 6));
    }

    public void testEncodeValueEqual() {
        assertEquals("232", codec.encodeValue(new BigDecimal("2.32"), 3));
    }

    public void testDecodeValueNull() {
        try {
            codec.decodeValue(null);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e) {

        }
    }

    public void testDecodeValueEmpty() {
        try {
            codec.decodeValue("");
            fail("Should have thrown exception");
        }
        catch (NumberFormatException e) {

        }
    }

    public void testDecodeValueInvalid() {
        try {
            codec.decodeValue("ab23d");
            fail("Should have thrown exception");
        }
        catch (NumberFormatException e) {

        }
    }

    public void testDecodeValueNoPadding() {
        BigDecimal num = (BigDecimal) codec.decodeValue("232");
        assertEquals(232L, num.longValue());
    }

    public void testDecodeValueZeroPadding() {
        BigDecimal num = (BigDecimal) codec.decodeValue("000232");
        assertEquals(232L, num.longValue());
    }

}
