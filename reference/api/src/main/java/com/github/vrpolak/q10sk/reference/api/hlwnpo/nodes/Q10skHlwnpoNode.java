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
 * Immutable object representing a hlwnpo node of q10sk state tree.
 *
 * <p>Hlwnpo is an execution scheme which allows a Q10skRunner to be implemented.
 * Hlwnpo stands for Half Lazily Weakly Normalize and Perform Operation.
 *
 * <p>All hlwnpo nodes can act both as function nodes and as root nodes.
 * To ensure all applied nodes remain within hlwnpo scheme, all arguments
 * of methods of this and extended interfaces are required to be
 * (subtypes) of Q10skHlwnpoNode type.
 *
 * <p>Each hlwnpo node is either weakly normalizable, or known to be already weakly normalized.
 * Some weakly normalized nodes allow input/output operations (only in root position),
 * others represent halted state.
 *
 * <p>Half lazily implies S is the only rule not applied as soon as possible,
 * only when required by weak normalization.
 *
 * @author Vratko Polak
 */
public interface Q10skHlwnpoNode extends Q10skStateTreeRootNode, Function<Q10skHlwnpoNode, Q10skHlwnpoNode> {

    /**
     * Return a weakly normalized node equivalent to this. May never return.
     *
     * <p>Nodes which are already weakly normalized should return themselves.
     * Even though only some nodes are caled weakly normalizable (meaning they do not return themselves),
     * it is easier to allow this call on all nodes.
     *
     * <p>With half lazy evaluation, weakly normalizable nodes have S as leftmost leaf,
     * so it is not feasible to restrict the wnormalization result type.
     *
     * @return node, perhaps newly constructed, perhaps reference to existing one.
     */
    Q10skHlwnpoWnizedNode weaklyNormalize();

}
