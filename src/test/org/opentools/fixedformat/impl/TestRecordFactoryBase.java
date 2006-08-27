package org.opentools.fixedformat.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.opentools.fixedformat.RecordFactory;
import org.opentools.fixedformat.impl.test.TestDetailRecord;
import org.opentools.fixedformat.impl.test.TestHeaderRecord;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import junit.framework.TestCase;

public class TestRecordFactoryBase extends TestCase
{
    private static final String HEADER_RECORD_1 = "1HeaderDescription   ";
    private static final String DETAIL_RECORD_1 = "2Item 1    00000000030308312333";
    
    private RecordFactory recordFactory;
    
    protected void setUp() throws Exception
    {
        // Load the application context
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("TestRecordFactoryBaseContext.xml", getClass()));
        
        recordFactory = (RecordFactory) bf.getBean("TestFactory");
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testHeader1Scan()
    {
        Object vo = recordFactory.createRecord(HEADER_RECORD_1);
        assertNotNull(vo);
        assertTrue(vo instanceof TestHeaderRecord);
        
        TestHeaderRecord thr = (TestHeaderRecord) vo;
        assertEquals(1L, thr.getRecordType());
        assertEquals("HeaderDescription", thr.getDescription());
    }
    
    public void testDetail1Scan()
    {
        Object vo = recordFactory.createRecord(DETAIL_RECORD_1);
        assertNotNull(vo);
        assertTrue(vo instanceof TestDetailRecord);
        
        TestDetailRecord tdr = (TestDetailRecord) vo;
        assertEquals(2L, tdr.getRecordType());
        assertEquals("Item 1", tdr.getDescription());
        assertEquals(303L, tdr.getAmount().longValue());
        
        Date dt = tdr.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        assertEquals(Calendar.AUGUST, cal.get(Calendar.MONTH));
        assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
        
        dt = tdr.getTime();
        cal = Calendar.getInstance();
        cal.setTime(dt);
        assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(33, cal.get(Calendar.MINUTE));
    }
    
    public void testHeader1Format()
    {
        TestHeaderRecord thr = new TestHeaderRecord();
        thr.setRecordType(1L);
        thr.setDescription("HeaderDescription");
        
        assertEquals(HEADER_RECORD_1, recordFactory.formatRecord(thr));
    }
    
    public void testDetail1Format()
    {
        TestDetailRecord tdr = new TestDetailRecord();
        tdr.setRecordType(2L);
        tdr.setDescription("Item 1");
        tdr.setAmount(new BigDecimal("3.03").setScale(2, BigDecimal.ROUND_HALF_EVEN));
        
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.AUGUST);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 33);
        tdr.setDate(cal.getTime());
        tdr.setTime(cal.getTime());
        
        assertEquals(DETAIL_RECORD_1, recordFactory.formatRecord(tdr));
    }
    
}
