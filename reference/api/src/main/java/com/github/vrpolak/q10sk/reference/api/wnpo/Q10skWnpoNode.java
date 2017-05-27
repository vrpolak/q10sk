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

import java.util.function.Function;

/**
 * Immutable object representing a wnpo node of q10sk state tree.
 *
 * <p>Wnpo stands for Weakly Normalize and Perform Operation.
 * Wnpo is an execution scheme which allows a Q10skRunner to be implemented.
 * Each wnpo node is either weakly normalizable, or known to be already weakly normalized.
 * Some weakly normalized nodes lead to known input/output operations,
 * others represent halted state.
 *
 * <p>All wnpo nodes can act as function nodes and root nodes both.
 * To ensure applied nodes remain within wnpo scheme, all arguments
 * of methods of this and extended interfaces are required to be of Q10skWnpoNode type.
 *
 * <p>Currently, no rule is applied without explicit call for weak normalization.
 * TODO: Should K, 0 and 1 rules be applied unconditionally as soon as possible?
 *
 * @author Vratko Polak
 */
public interface Q10skWnpoNode extends Q10skStateTreeRootNode, Function<Q10skWnpoNode, Q10skWnpoNode> {

    // Just a marker interface, no specific methods.
    // Weak normalizability shall be tested as an interface check.

}
