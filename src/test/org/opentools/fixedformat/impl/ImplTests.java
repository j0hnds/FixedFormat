package org.opentools.fixedformat.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ImplTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite(
                "Test for org.opentools.fixedformat.impl");
        //$JUnit-BEGIN$
        suite.addTestSuite(TestRecordFactoryBase.class);
        suite.addTestSuite(TestRecordBase.class);
        suite.addTestSuite(TestLongCodec.class);
        suite.addTestSuite(TestStringCodec.class);
        suite.addTestSuite(TestUSCurrencyCodec.class);
        suite.addTestSuite(TestBeanPopulator.class);
        suite.addTestSuite(TestFieldBase.class);
        suite.addTestSuite(TestMapPopulator.class);
        suite.addTestSuite(TestDateTimeCodec.class);
        //$JUnit-END$
        return suite;
    }

}
