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
        suite.addTestSuite(TestRecordBase.class);
        suite.addTestSuite(TestStringCodec.class);
        suite.addTestSuite(TestBeanPopulator.class);
        suite.addTestSuite(TestFieldBase.class);
        suite.addTestSuite(TestMapPopulator.class);
        //$JUnit-END$
        return suite;
    }

}
