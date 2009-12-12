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

import java.util.List;

public interface Record
{
    /**
     * Reads the record into the specified value object. Uses
     * the field names to map to properties in the value object
     * to set the values.
     * 
     * @param record the record to read.
     * @param valueObject the object in which to set the values.
     */
    void scanRecord(String record, Object valueObject);
    
    /**
     * Formats the fixed length record using the data in the
     * specified object. If the specified object is a map, it
     * is assumed that the keys in the map are the field names. If
     * it is just a POJO, it is assumed that the property names
     * map to the field names (bean-style).
     * 
     * @param record the data record.
     * @return formatted fixed-field record.
     */
    String formatRecord(Object record);
    
    /**
     * Sets the field definitions on the record.
     * 
     * @param fieldList the list of field definitions to set.
     */
    void setFieldDefinitions(List<Field> fieldList);
    
    /**
     * Returns the list of field definitions for the record.
     * 
     * @return the list of field definitions.
     */
    List<Field> getFieldDefinitions();
    
    void setPopulator(ObjectPopulator populator);
    
    ObjectPopulator getPopulator();
}
