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
 * Implement this interface to identify the type of record entering the system.
 * We cannot tell which record type to create to read the record unless we know
 * what kind of record it is.
 * 
 * Implicit in this is that the fixed format records have a consistent means of
 * determinine the record type.
 * 
 * @author Dave Sieh
 */
public interface RecordIdentifier {
    /**
     * Scan the input record and return the unique identifier of the record
     * type.
     * 
     * @param record
     *            the full record to be identified.
     * @return the record type. null if unable to determine the record type.
     */
    String getRecordIdentifier(String record);
}
