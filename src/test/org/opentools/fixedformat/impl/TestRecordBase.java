package org.opentools.fixedformat.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opentools.fixedformat.Field;
import org.opentools.fixedformat.Record;
import org.opentools.fixedformat.ValueCodec;

import junit.framework.TestCase;

public class TestRecordBase extends TestCase
{
    private Record record;
    
    protected void setUp() throws Exception
    {
        record = new RecordBase();
        List fieldDefinitions = new ArrayList();
        
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
        record.setBeanPopulator(new BeanPopulator());
        record.setMapPopulator(new MapPopulator());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testScanRecordIntoMap()
    {
        Map mappedRecord = record.scanRecord("1cd  0033000000000389");
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
    
    public void testScanRecordBean()
    {
        BeanToTest beanToTest = new BeanToTest();
        record.scanRecord("1cd  0033000000000389", beanToTest);
        
        assertEquals("1", beanToTest.getRecordType());
        assertEquals("cd", beanToTest.getOtherValue());
        assertEquals(new Long(33), beanToTest.getLongValue());
        BigDecimal num = beanToTest.getDecimalValue();
        assertEquals(389, num.longValue());
    }
    
    public void testFormatBean()
    {
        BeanToTest beanToTest = new BeanToTest();
        beanToTest.setRecordType("3");
        beanToTest.setOtherValue("de");
        beanToTest.setLongValue(new Long(9988));
        beanToTest.setDecimalValue(new BigDecimal("148.9"));
        
        assertEquals("3de  9988000000014890", record.formatRecord(beanToTest));
    }
    
    public void testFormatMap()
    {
        Map mapToTest = new HashMap();
        mapToTest.put("recordType", "6");
        mapToTest.put("otherValue", "c");
        mapToTest.put("longValue", new Long(9988));
        mapToTest.put("decimalValue", new BigDecimal("148.9"));
        
        assertEquals("6c   9988000000014890", record.formatRecord(mapToTest));
    }

    public static class BeanToTest
    {
        private String recordType;
        private String otherValue;
        private Long longValue;
        private BigDecimal decimalValue;

        public String getOtherValue()
        {
            return otherValue;
        }
        public void setOtherValue(String otherValue)
        {
            this.otherValue = otherValue;
        }
        public String getRecordType()
        {
            return recordType;
        }
        public void setRecordType(String recordType)
        {
            this.recordType = recordType;
        }
        public BigDecimal getDecimalValue()
        {
            return decimalValue;
        }
        public void setDecimalValue(BigDecimal decimalValue)
        {
            this.decimalValue = decimalValue;
        }
        public Long getLongValue()
        {
            return longValue;
        }
        public void setLongValue(Long longValue)
        {
            this.longValue = longValue;
        }

    }
}
