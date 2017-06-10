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
 * Immutable object representing a hlwnpo node of q10sk state tree able to perform input operation.
 *
 * @author Vratko Polak
 */
public interface Q10skHlwnpoInputNode<APPLIED extends Q10skHlwnpoKnownizedNode<APPLIED>>
        extends Q10skHlwnpoKnownizedNode<APPLIED> {

    /**
     * Return a root node equivalent to the state tree after performing input operation.
     *
     * <p>Returns always and quickly, unless producer interferes.
     *
     * @return node, perhaps newly constructed, perhaps reference to existing one.
     */
    Q10skHlwnpoNode input(final BitProducer producer);

}
