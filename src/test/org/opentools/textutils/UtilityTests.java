package org.opentools.textutils;

import junit.framework.Test;
import junit.framework.TestSuite;

public class UtilityTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for org.opentools.textutils");
        //$JUnit-BEGIN$
        suite.addTestSuite(TestTextUtils.class);
        suite.addTestSuite(TestNumberUtils.class);
        suite.addTestSuite(TestDateTimeUtils.class);
        //$JUnit-END$
        return suite;
    }

}
