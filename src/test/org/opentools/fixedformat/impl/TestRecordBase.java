package org.opentools.fixedformat.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opentools.fixedformat.Field;
import org.opentools.fixedformat.Record;

import junit.framework.TestCase;

public class TestRecordBase extends TestCase
{
    private Record record;
    
    protected void setUp() throws Exception
    {
        record = new RecordBase();
        List fieldDefinitions = new ArrayList();
        
        Field fld = new FieldBase();
        fld.setName("recordType");
        fld.setLength(1);
        fld.setDescription("Record Type Holder");
        fld.setValueCodec(new StringCodec());
        fieldDefinitions.add(fld);
        
        fld = new FieldBase();
        fld.setName("otherValue");
        fld.setLength(4);
        fld.setDescription("Other Value Holder");
        fld.setValueCodec(new StringCodec());
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
        Map mappedRecord = record.scanRecord("1  cd");
        assertNotNull(mappedRecord);
        assertEquals(2, mappedRecord.size());
        assertTrue(mappedRecord.containsKey("recordType"));
        assertEquals("1", mappedRecord.get("recordType"));
        assertTrue(mappedRecord.containsKey("otherValue"));
        assertEquals("cd", mappedRecord.get("otherValue"));
    }
    
    public void testScanRecordBean()
    {
        BeanToTest beanToTest = new BeanToTest();
        record.scanRecord("1  cd", beanToTest);
        
        assertEquals("1", beanToTest.getRecordType());
        assertEquals("cd", beanToTest.getOtherValue());
    }
    
    public void testFormatBean()
    {
        BeanToTest beanToTest = new BeanToTest();
        beanToTest.setRecordType("3");
        beanToTest.setOtherValue("de");
        
        assertEquals("3  de", record.formatRecord(beanToTest));
    }
    
    public void testFormatMap()
    {
        Map mapToTest = new HashMap();
        mapToTest.put("recordType", "6");
        mapToTest.put("otherValue", "c");
        
        assertEquals("6   c", record.formatRecord(mapToTest));
    }

    public static class BeanToTest
    {
        private String recordType;
        private String otherValue;

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

    }
}
