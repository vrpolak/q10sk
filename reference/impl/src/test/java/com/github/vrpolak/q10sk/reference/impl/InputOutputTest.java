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
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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

    private static final Q10skHlwnpoWiring wiring = new DefaultWiring();
    private static final Q10skHlwnpoNode s0 = wiring.get0Node();
    private static final Q10skHlwnpoNode s1 = wiring.get1Node();
    private static final Q10skHlwnpoNode sK = wiring.getKNode();
    private static final Q10skHlwnpoNode sQ = wiring.getQNode();
    private static final Q10skHlwnpoNode sS = wiring.getSNode();
    private static final Q10skHlwnpoNode sI = sS.apply(sK).apply(sK);
    private static final Q10skHlwnpoNode sii = sS.apply(sI).apply(sI);
    private static final AssertingSystem emptySystem = start();
    private static final Q10skHlwnpoRunner runner = new SimpleRunner();

    public static AssertingSystem start() {
        return new AssertingSystem();
    }

    // TODO: Create an API for BitSystem with assertExhausted.
    protected static void assertHalts(final Q10skHlwnpoNode initial, final AssertingSystem system, final Q10skHlwnpoNode expected, final String message) {
        final Q10skHlwnpoNode halted = runner.run(system, initial);
        system.assertExhausted();
        Assert.assertEquals(message, expected, halted);
    }

    protected static void assertHalted(final Q10skHlwnpoNode initial) {
        assertHalts(initial, emptySystem, initial, "Initial state is not halted.");
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
        assertHalted(sI);
    }

    /**
     * 0 and 1 nodes with one argument should output and return the argument.
     */
    @Test
    public void outputTest() {
        assertHalts(s0.apply(sK),
                    start().shallAccept(ZERO),
                    sK, "0 node has not returned its argument");
        assertHalts(s1.apply(sK),
                    start().shallAccept(ONE),
                    sK, "1 node has not returned its argument");
    }

    /**
     * Qxy node should return one of arguments.
     */
    @Test
    public void inputTest() {
        final Q10skHlwnpoNode qks = sQ.apply(sK).apply(sS);
        assertHalts(qks,
                    start().shallGet(ZERO),
                    sK, "Q node has not returned first argument");
        assertHalts(qks,
                    start().shallGet(ONE),
                    sS, "Q node has not returned second argument");
    }

    /**
     * Q01K should echo one bit. This tests AssertingSystem queueing.
     */
    @Test
    public void singleEchoTest() {
        final Q10skHlwnpoNode q01k = sQ.apply(s0).apply(s1).apply(sK);
        assertHalts(q01k,
                    start().shallGet(ZERO).shallAccept(ZERO),
                    sK, "Q01K has not returned K");
        assertHalts(q01k,
                    start().shallGet(ONE).shallAccept(ONE),
                    sK, "Q01K has not returned K");
    }

    /**
     * K01S should output 0 and return S.
     */
    @Test
    public void kOutputTest() {
        assertHalts(sK.apply(s0).apply(s1).apply(sS),
                    start().shallAccept(ZERO),
                    sS, "K01S has not returned S");
    }

    /**
     * SK01S should output 1 and return S.
     */
    @Test
    public void skOutputTest() {
        assertHalts(sS.apply(sK).apply(s0).apply(s1).apply(sS),
                    start().shallAccept(ONE),
                    sS, "SK01S has not returned S");
    }

    /**
     * Extracting output nodes as single arguments creates interesting S constructions.
     *
     * <p>01001K = (01)(001)K = S0(00)1K = S0((I0)(I0))1K = S0(SII0)1K = SS(SII)01K
     *
     * <p> so this also tests SII behavior.
     */
    @Test
    public void afterEffectTest() {
        assertHalts(sS.apply(sS).apply(sii).apply(s0).apply(s1).apply(sK),
                    start().shallAccept(ZERO).shallAccept(ONE).shallAccept(ZERO).shallAccept(ZERO).shallAccept(ONE),
                    sK, "After effect has not returned K");
    }

    /**
     * Boolean implication test, just to make sure input order maters.
     */
    @Test
    public void booleanImplicationTest() {
        final Q10skHlwnpoNode implication = sQ.apply(sQ.apply(s1).apply(s1)).apply(sQ.apply(s0).apply(s1)).apply(sK);
        final String message = "Implication program has not returned K";
        assertHalts(implication,
                    start().shallGet(ZERO).shallGet(ZERO).shallAccept(ONE),
                    sK, message);
        assertHalts(implication,
                    start().shallGet(ZERO).shallGet(ONE).shallAccept(ONE),
                    sK, message);
        assertHalts(implication,
                    start().shallGet(ONE).shallGet(ZERO).shallAccept(ZERO),
                    sK, message);
        assertHalts(implication,
                    start().shallGet(ONE).shallGet(ONE).shallAccept(ONE),
                    sK, message);
    }

    private ArrayDeque<Q10skHlwnpoNode> upcombine(final Collection<Q10skHlwnpoNode> base) {
        final ArrayDeque<Q10skHlwnpoNode> result = new ArrayDeque<Q10skHlwnpoNode>(base);
        for (final Q10skHlwnpoNode function : base) {
            for (final Q10skHlwnpoNode argument : base) {
                result.add(function.apply(argument));
            }
        }
        return result;
    }

    /**
     * Black hole test. The point is to make sure the eaten function do not operate.
     *
     * <p>This also acts as a scale test and partly as a performance test.
     */
    @Test
    public void blackHoleTest() {
        final Q10skHlwnpoNode halfHole = sS.apply(sK.apply(sK)).apply(sii);
        final List<Q10skHlwnpoNode> food = Arrays.asList(s0, s1, sK, sQ, sS, sI, sii, sS.apply(sK), sS.apply(sS));
        // Adding more items to food would lead to StackOverflow with double upcombining.
        Q10skHlwnpoNode program = sii.apply(halfHole);
        for (final Q10skHlwnpoNode item : upcombine(upcombine(food))) {
            program = program.apply(item);
        }
        // Do not call assertHalts, because  we do not have cache to recognize equivalent hungry form of black hole.
        runner.run(emptySystem, program);
    }

    // TODO: Figure out how to test non-halting programs,
    // such as SII(SII) and infinite echo.
}
