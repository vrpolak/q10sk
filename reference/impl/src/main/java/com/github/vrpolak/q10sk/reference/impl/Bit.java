/* Project q10sk, a minimalistic model of computation.
 * Copyright (C) 2017  Vratko Polak
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
// TODO: Also add information on how to contact you by electronic and paper mail.

package com.github.vrpolak.q10sk.reference.impl;

import com.github.vrpolak.q10sk.reference.api.ImmutableBit;

/**
 * Single immutable bit value. Only "zero" and "one" values are possible.
 *
 * @author Vratko Polak
 */
public class Bit implements ImmutableBit {

    /**
     * Zero bit, one of two allowed instances available as static field.
     */
    public static final Bit ZERO = new Bit(true);
    /**
     * One bit, one of two allowed instances available as static field.
     */
    public static final Bit ONE = new Bit(false);

    /**
     * Private fiel to hold the boolean value.
     */
    private final boolean isZero;

    /**
     * Private constructor used to create the two instances.
     */
    private Bit(final boolean isZero) {
        this.isZero = isZero;
    }

    /**
     * Return true if the value is zero, return false if the value is one.
     *
     * @return boolean value
     */
    @Override
    public boolean isZero() {
        return isZero;
    }

}
