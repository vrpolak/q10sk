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

import com.github.vrpolak.q10sk.reference.api.BitConsumer;
import com.github.vrpolak.q10sk.reference.api.BitSupplier;
import com.github.vrpolak.q10sk.reference.api.BitSystem;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoInputNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoOutputNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoRunner;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoWnizedNode;

/**
 * Stateless implementation of hlwnpo runner.
 *
 * @author Vratko Polak
 */
public class SimpleRunner implements Q10skHlwnpoRunner {

    // The implicit zero-argument constructor is public for anyone to use.

    /*
     * Repeatedly call weak normalization and perform output and input, return halted state.
     *
     * @param system  the system to be called when the program wants to output or input, mutated on call
     * @param initialState  the initial state of the program, never changed
     * @return  the halted state
     */
    @Override
    public Q10skHlwnpoWnizedNode run(final BitSystem system, final Q10skHlwnpoNode initialState) {
        Q10skHlwnpoWnizedNode currentState = initialState.weaklyNormalize();
        while(true) {
            if (currentState instanceof Q10skHlwnpoOutputNode) {
                currentState = ((Q10skHlwnpoOutputNode) currentState).output((BitConsumer) system).weaklyNormalize();
                continue;
            }
            if (currentState instanceof Q10skHlwnpoInputNode) {
                currentState = ((Q10skHlwnpoInputNode) currentState).input((BitSupplier) system).weaklyNormalize();
                continue;
            }
            return currentState;
        }
    }

}
