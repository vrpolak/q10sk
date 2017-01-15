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

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the two instances return correct isZero().
 *
 * @author Vratko Polak
 */
public class BitTest {

    /**
     * Zero bit should return true.
     */
    @Test
    public void zeroTest() {
        final Bit zero = Bit.ZERO;
        Assert.assertTrue(zero.isZero());
    }

    /**
     * One bit should return false.
     */
    @Test
    public void oneTest() {
        final Bit one = Bit.ONE;
        Assert.assertFalse(one.isZero());
    }

}
