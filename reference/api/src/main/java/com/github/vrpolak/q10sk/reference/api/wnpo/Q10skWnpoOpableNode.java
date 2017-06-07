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
 * Immutable object representing a wnpo node of q10sk state tree able to perform a root operation.
 *
 * @author Vratko Polak
 */
public interface Q10skWnpoOpableNode<OPERATED extends Q10skWnpoNode, APPLIED extends Q10skWnpoNode>
        extends Q10skWnpoWnizedNode<APPLIED> {

    /**
     * Return a root node equivalent to the state tree after performing an operation.
     *
     * <p>Returns always and quickly.
     * No side-effect is performed, the runner is expected to perform that,
     * based on an sub-interface check and of course only if this is the current root node.
     *
     * @return node, perhaps newly constructed, perhaps reference to existing one.
     */
    OPERATED operate();

}
