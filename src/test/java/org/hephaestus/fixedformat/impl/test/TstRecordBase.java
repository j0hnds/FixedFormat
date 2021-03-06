package org.hephaestus.fixedformat.impl.test;

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

import org.hephaestus.fixedformat.RecordTypeAccessor;

public class TstRecordBase implements RecordTypeAccessor {
    private long recordType;

    public long getRecordType() {
        return recordType;
    }

    public void setRecordType(long recordType) {
        this.recordType = recordType;
    }

    public String getRecordTypeAsString() {
        return Long.toString(recordType);
    }

}
