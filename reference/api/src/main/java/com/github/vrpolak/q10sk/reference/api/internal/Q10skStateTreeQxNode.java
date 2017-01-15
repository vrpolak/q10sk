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
 * Immutable object representing an inner node of q10sk state tree, where the function is Q.
 *
 * <p>Each Q10skStateTreeInnerNode instance with Q as a function should be of a class which implements this interface.
 *
 * @author Vratko Polak
 */
public interface Q10skStateTreeQxNode extends Q10skStateTreeInnerNode {

    /**
     * Return a new node which applies (without evaluating) this as a function to the given argument.
     *
     * @return result Qxy node, either newly constructed, or reference to existing one.
     */
    @Override
    Q10skStateTreeQxyNode applyTo(final Q10skStateTreeGeneralNode argumentY);

}
