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

package com.github.vrpolak.q10sk.reference.api;

/**
 * Object used for running q10sk programs.
 *
 * <p>The behavior corresponds to an immutable object
 * whose method mutates some of the given arguments.
 * Implementations are free to contain mutable data themselves,
 * for example various caches.
 *
 * @author Vratko Polak
 */
public interface Q10skRunner {

    /*
     * Run program defined by state, calling consumer and producer for output and input repsectively.
     *
     * <p>If the program reached halted state, return the state.
     *
     * @param state  the initial state of the program, never changed
     * @param consumer  the consumer to be called when the program want to output, mutated on call
     * @param producer  the producer to call when the program requires input, mutated on call
     * @return  the halted state
     */
    Q10skStateTreeGeneralNode run(final Q10skStateTreeGeneralNode initialState, final BitConsumer consumer, final BitProducer producer);

}
