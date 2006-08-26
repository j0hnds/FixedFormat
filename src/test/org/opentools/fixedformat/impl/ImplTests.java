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
        //$JUnit-END$
        return suite;
    }

}
