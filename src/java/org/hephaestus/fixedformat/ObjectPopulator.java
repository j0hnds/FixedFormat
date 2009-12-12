package org.hephaestus.fixedformat;
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

/**
 * This interface is implemented by classes tuned to
 * populate an object.
 * 
 * @author djs
 *
 */
public interface ObjectPopulator
{
    /**
     * Populates the specified object with the property name and value.
     * 
     * @param objectToPopulate the object to populate
     * @param propertyName the name of the property on the object to populate
     * @param value the value of the object to populate.
     */
    void populateValue(Object objectToPopulate, String propertyName, Object value);
    
    /**
     * Retrieves the value from the specified object.
     * 
     * @param objectToAccess the object from which to obtain the value.
     * @param propertyName the name of the property to access.
     * @return the value of the property.
     */
    Object getValue(Object objectToAccess, String propertyName);
}
