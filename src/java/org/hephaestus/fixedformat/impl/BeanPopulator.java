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

import org.apache.commons.beanutils.PropertyUtils;
import org.hephaestus.fixedformat.ObjectPopulator;
import org.hephaestus.textutils.TextUtils;

public class BeanPopulator implements ObjectPopulator
{

    public final void populateValue(Object objectToPopulate, String propertyName,
            Object value)
    {
        if (objectToPopulate == null)
        {
            throw new IllegalArgumentException("Object to populate must be non-null");
        }
        
        if (TextUtils.isBlank(propertyName))
        {
            throw new IllegalArgumentException("Property name must be non-null and not empty");
        }
        
        try
        {
            PropertyUtils.setProperty(objectToPopulate, propertyName, value);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error setting property value", e);
        }

    }

    public final Object getValue(Object objectToAccess, String propertyName)
    {
        if (objectToAccess == null)
        {
            throw new IllegalArgumentException("Object to access must be non-null");
        }
        
        if (TextUtils.isBlank(propertyName))
        {
            throw new IllegalArgumentException("Property name must be non-null and not empty");
        }
        
        try
        {
            return PropertyUtils.getProperty(objectToAccess, propertyName);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error getting property value", e);
        }
        
    }

}
