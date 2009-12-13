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
 * Implement this interface to construct new records.
 * 
 * @author Dave Sieh
 */
public interface RecordFactory {
    /**
     * Sets the record identifier to use.
     * 
     * @param identifier
     *            the record identifier to use
     */
    void setRecordIdentifier(RecordIdentifier identifier);

    /**
     * Gets the record identifier being used.
     * 
     * @return the record identifier being used.
     */
    RecordIdentifier getRecordIdentifier();

    /**
     * Creates a populated record from the specified fixed-length record.
     * 
     * @param record
     *            the fixed length record to read
     * @return newly created fixed length record. null if unable to determine
     *         the record type.
     */
    Object createRecord(String record);

    String formatRecord(RecordTypeAccessor valueObject);
}
