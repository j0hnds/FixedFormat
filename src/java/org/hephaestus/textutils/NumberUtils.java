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

public class NumberUtils {
    private NumberUtils() {

    }

    public static String zeroPadNumber(String numValue, int length) {
        if (numValue.length() > length) {
            throw new IllegalArgumentException("Long value is too long to pad");
        }

        return TextUtils.rightJustifyText(numValue, length, '0');
    }

    public static String zeroPadLong(long longValue, int length) {
        String originalValue = Long.toString(longValue);

        if (originalValue.length() > length) {
            throw new IllegalArgumentException("Long value is too long to pad");
        }

        return TextUtils.rightJustifyText(originalValue, length, '0');
    }
}
