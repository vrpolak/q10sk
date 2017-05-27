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
 * Immutable object for creating wnpo 1xy nodes from two arguments.
 *
 * @author Vratko Polak
 */
public interface Q10skWnpo1xyNodeFactory extends Q10skStateTreeNodeTwoArgumentFactory<Q10sWnpo1xyNode> {

    /**
     * Create new 1xy node using the arguments. May be called multiple times.
     *
     * @param argumentX the first argument to use.
     * @param argumentY the second argument to use.
     * @return created 1xy node, each call references different instance.
     */
    @Override
    Q10skWnpo1xyNode create(final Q10skWnpoNode argumentX, final Q10skWnpoNode argumentY);

}
