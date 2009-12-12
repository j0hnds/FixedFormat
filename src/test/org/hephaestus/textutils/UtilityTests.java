package org.hephaestus.textutils;
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
