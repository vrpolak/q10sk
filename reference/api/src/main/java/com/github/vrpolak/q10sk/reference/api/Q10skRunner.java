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
public interface Q10skRunner<NODE extends Q10skStateTreeRootNode> {

    /*
     * Run a program, calling the system for output and input, starting from initial state.
     *
     * <p>If the program reaches a halted state, return the final state, otherwise keep executing indefinitely.
     *
     * <p>This is a generic, as Q10skStateTreeRootNode itself does not expose any methods
     * which would enable program execution. It is assumed NODE type exposes such methods,
     * and runners using them would be of an interface extending this and documenting the execution method.
     *
     * @param system  the system to be called when the program wants to output or input, mutated on call
     * @param initialState  the initial state of the program, never changed
     * @return  the halted state
     */
    NODE run(final System system, final NODE initialState);

}
