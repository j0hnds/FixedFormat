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

public final class LongCodec extends ValueCodecBase
{
    protected Object stringToObject(String value)
    {
        return new Long(value);
    }

    protected String objectToString(Object value)
    {
        String stringValue = null;
        
        if (value instanceof String)
        {
            stringValue = new Long((String) value).toString();
        }
        else if (value instanceof Number)
        {
            stringValue = Long.toString(((Number) value).longValue());
        }
        else
        {
            throw new IllegalArgumentException("Value must be a Number or convertable to number");
        }

        return stringValue;
    }

}
