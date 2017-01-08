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

package com.github.vrpolak.q10sk.reference.implementation.api;

/**
 * Immutable object representing a leaf node of q10sk state tree, so a known combinator.
 *
 * <p>Each Q10skStateTreeGeneralNode instance with isApplication==false has to be of class which implements this interface.
 *
 * @author Vratko Polak
 */
public interface Q10skStateTreeLeafNode extends Q10skStateTreeGeneralNode {
    /*
     * Return 'K', 'S', '0', '1' or 'Q', depending on which combinator is in this leaf.
     *
     * @return name of the combinator.
     */
    char name();
}
