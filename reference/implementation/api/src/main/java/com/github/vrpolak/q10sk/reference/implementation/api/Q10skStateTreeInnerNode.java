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
 * Immutable object representing an inner node of q10sk state tree, so a function application.
 *
 * <p>Each Q10skStateTreeNode instance with isApplication==true has to be of a class which implements this interface.
 *
 * @author Vratko Polak
 */
public interface Q10skStateTreeInnerNode extends Q10skStateTreeGeneralNode {
    /*
     * Return reference to the function node.
     *
     * @return node of the function.
     */
    Q10skStateTreeGeneralNode function();

    /*
     * Return reference to the argument node.
     *
     * @return node of the argument
     */
    Q10skStateTreeGeneralNode argument();
}
