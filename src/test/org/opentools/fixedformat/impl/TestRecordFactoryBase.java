package org.opentools.fixedformat.impl;

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
    private static final String DETAIL_RECORD_1 = "2Item 1    000000000303";
    
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
    }
    
}
