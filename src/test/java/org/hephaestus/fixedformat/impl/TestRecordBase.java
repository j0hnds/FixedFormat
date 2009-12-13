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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hephaestus.fixedformat.Field;
import org.hephaestus.fixedformat.ObjectPopulator;
import org.hephaestus.fixedformat.Record;
import org.hephaestus.fixedformat.ValueCodec;
import org.hephaestus.fixedformat.impl.BeanPopulator;
import org.hephaestus.fixedformat.impl.FieldBase;
import org.hephaestus.fixedformat.impl.LongCodec;
import org.hephaestus.fixedformat.impl.MapPopulator;
import org.hephaestus.fixedformat.impl.RecordBase;
import org.hephaestus.fixedformat.impl.StringCodec;
import org.hephaestus.fixedformat.impl.USCurrencyCodec;

import junit.framework.TestCase;

public class TestRecordBase extends TestCase {
    private Record record;
    private ObjectPopulator beanPopulator;
    private ObjectPopulator mapPopulator;

    protected void setUp() throws Exception {
        beanPopulator = new BeanPopulator();
        mapPopulator = new MapPopulator();

        record = new RecordBase();
        List<Field> fieldDefinitions = new ArrayList<Field>();

        ValueCodec stringCodec = new StringCodec();
        stringCodec.setPaddable(true);
        stringCodec.setPadCharacter(' ');
        stringCodec.setJustification(ValueCodec.LEFT_JUSTIFIED);

        ValueCodec longCodec = new LongCodec();
        longCodec.setPaddable(true);
        longCodec.setPadCharacter('0');
        longCodec.setJustification(ValueCodec.RIGHT_JUSTIFIED);

        ValueCodec usCurrencyCodec = new USCurrencyCodec();
        usCurrencyCodec.setPaddable(true);
        usCurrencyCodec.setPadCharacter('0');
        usCurrencyCodec.setJustification(ValueCodec.RIGHT_JUSTIFIED);

        Field fld = new FieldBase();
        fld.setName("recordType");
        fld.setLength(1);
        fld.setDescription("Record Type Holder");
        fld.setValueCodec(stringCodec);
        fieldDefinitions.add(fld);

        fld = new FieldBase();
        fld.setName("otherValue");
        fld.setLength(4);
        fld.setDescription("Other Value Holder");
        fld.setValueCodec(stringCodec);
        fieldDefinitions.add(fld);

        fld = new FieldBase();
        fld.setName("longValue");
        fld.setLength(4);
        fld.setDescription("Long Value Holder");
        fld.setValueCodec(longCodec);
        fieldDefinitions.add(fld);

        fld = new FieldBase();
        fld.setName("decimalValue");
        fld.setLength(12);
        fld.setDescription("Decimal Value Holder");
        fld.setValueCodec(usCurrencyCodec);
        fieldDefinitions.add(fld);

        record.setFieldDefinitions(fieldDefinitions);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testScanRecordIntoMap() {
        record.setPopulator(mapPopulator);
        Map<String, Record> mappedRecord = new HashMap<String, Record>();
        record.scanRecord("1cd  0033000000000389", mappedRecord);
        assertNotNull(mappedRecord);
        assertEquals(4, mappedRecord.size());
        assertTrue(mappedRecord.containsKey("recordType"));
        assertEquals("1", mappedRecord.get("recordType"));
        assertTrue(mappedRecord.containsKey("otherValue"));
        assertEquals("cd", mappedRecord.get("otherValue"));
        assertTrue(mappedRecord.containsKey("longValue"));
        assertEquals(new Long(33), mappedRecord.get("longValue"));
        assertTrue(mappedRecord.containsKey("decimalValue"));
        BigDecimal decimalValue = (BigDecimal) mappedRecord.get("decimalValue");
        assertEquals(389, decimalValue.longValue());
    }

    public void testScanRecordBean() {
        record.setPopulator(beanPopulator);
        BeanToTest beanToTest = new BeanToTest();
        record.scanRecord("1cd  0033000000000389", beanToTest);

        assertEquals("1", beanToTest.getRecordType());
        assertEquals("cd", beanToTest.getOtherValue());
        assertEquals(new Long(33), beanToTest.getLongValue());
        BigDecimal num = beanToTest.getDecimalValue();
        assertEquals(389, num.longValue());
    }

    public void testFormatBean() {
        record.setPopulator(beanPopulator);
        BeanToTest beanToTest = new BeanToTest();
        beanToTest.setRecordType("3");
        beanToTest.setOtherValue("de");
        beanToTest.setLongValue(new Long(9988));
        beanToTest.setDecimalValue(new BigDecimal("148.9"));

        assertEquals("3de  9988000000014890", record.formatRecord(beanToTest));
    }

    public void testFormatMap() {
        record.setPopulator(mapPopulator);
        Map<String, Object> mapToTest = new HashMap<String, Object>();
        mapToTest.put("recordType", "6");
        mapToTest.put("otherValue", "c");
        mapToTest.put("longValue", new Long(9988));
        mapToTest.put("decimalValue", new BigDecimal("148.9"));

        assertEquals("6c   9988000000014890", record.formatRecord(mapToTest));
    }

    public static class BeanToTest {
        private String recordType;
        private String otherValue;
        private Long longValue;
        private BigDecimal decimalValue;

        public String getOtherValue() {
            return otherValue;
        }

        public void setOtherValue(String otherValue) {
            this.otherValue = otherValue;
        }

        public String getRecordType() {
            return recordType;
        }

        public void setRecordType(String recordType) {
            this.recordType = recordType;
        }

        public BigDecimal getDecimalValue() {
            return decimalValue;
        }

        public void setDecimalValue(BigDecimal decimalValue) {
            this.decimalValue = decimalValue;
        }

        public Long getLongValue() {
            return longValue;
        }

        public void setLongValue(Long longValue) {
            this.longValue = longValue;
        }

    }
}
