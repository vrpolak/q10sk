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

package com.github.vrpolak.q10sk.reference.impl.test;

import com.github.vrpolak.q10sk.reference.api.BitSystem;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoRunner;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoWiring;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoWnizedNode;
import com.github.vrpolak.q10sk.reference.impl.SimpleRunner;
import com.github.vrpolak.q10sk.reference.impl.wiring.DefaultWiring;
import org.junit.Assert;
import org.junit.Test;

import static com.github.vrpolak.q10sk.reference.impl.SimpleBit.ONE;
import static com.github.vrpolak.q10sk.reference.impl.SimpleBit.ZERO;

/**
 * Test input-output behavior of several short programs.
 *
 * @author Vratko Polak
 */
public class InputOutputTest {

    private final Q10skHlwnpoWiring wiring = new DefaultWiring();
    private final Q10skHlwnpoNode s0 = wiring.get0Node();
    private final Q10skHlwnpoNode s1 = wiring.get1Node();
    private final Q10skHlwnpoNode sK = wiring.getKNode();
    private final Q10skHlwnpoNode sQ = wiring.getQNode();
    private final Q10skHlwnpoNode sS = wiring.getSNode();
    private final BitSystem emptySystem = new AssertingSystem();
    private final Q10skHlwnpoRunner runner = new SimpleRunner();

    protected void assertHalted(final Q10skHlwnpoNode initial) {
        final Q10skHlwnpoNode halted = runner.run(emptySystem, initial);
        Assert.assertEquals("Given state is not halted.", initial, halted);
    }

    /**
     * Leaf and near nodes which should.
     */
    @Test
    public void haltedTest() {
        assertHalted(s0);
        assertHalted(s1);
        assertHalted(sK);
        assertHalted(sQ);
        assertHalted(sS);
        assertHalted(sK.apply(sK));
        assertHalted(sQ.apply(sK));
        assertHalted(sS.apply(sK));
        assertHalted(sS.apply(sK).apply(sK));
    }

    /**
     * 0 and 1 nodes with one argument should output and return the argument.
     */
    @Test
    public void outputTest() {
        AssertingSystem system;  // TODO: Extract API for additinal methods not in BitSystem.
        Q10skHlwnpoWnizedNode halted;

        system = new AssertingSystem().shallAccept(ZERO);
        halted = runner.run(system, s0.apply(sK));
        system.assertExhausted();
        Assert.assertEquals("0 node has not returned its argument", sK, halted);

        system = new AssertingSystem().shallAccept(ONE);
        halted = runner.run(system, s1.apply(sK));
        system.assertExhausted();
        Assert.assertEquals("1 node has not returned its argument", sK, halted);
    }

    /**
     * Qxy node should return one of arguments.
     */
    @Test
    public void inputTest() {
        AssertingSystem system;  // TODO: Extract API for additinal methods not in BitSystem.
        Q10skHlwnpoWnizedNode halted;
        final Q10skHlwnpoNode qks = sQ.apply(sK).apply(sS);

        system = new AssertingSystem().shallGet(ZERO);
        halted = runner.run(system, qks);
        system.assertExhausted();
        Assert.assertEquals("Q node has not returned first argument", sK, halted);

        system = new AssertingSystem().shallGet(ONE);
        halted = runner.run(system, qks);
        system.assertExhausted();
        Assert.assertEquals("Q node has not returned first argument", sS, halted);
    }

}
