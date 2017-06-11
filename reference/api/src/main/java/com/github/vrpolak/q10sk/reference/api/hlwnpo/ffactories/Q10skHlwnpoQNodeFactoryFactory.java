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
 * Immutable object for creating hlwnpo Q node factories from Qx node factories.
 *
 * <p>As Q apply method creates Qx nodes, it needs access to a Qx node factory.
 * This is the way to create a Q factory which has such an access.
 *
 * @author Vratko Polak
 */
public interface Q10skHlwnpoQNodeFactoryFactory extends OneArgumentFactory<Q10skHlwnpoQNodeFactory, Q10skHlwnpoQxNodeFactory> {

    // Nothing to add to what parent interfaces imply.

}
