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
 * Immutable object serving as a source of wired hlwnpo leaf nodes for q10sk state tree.
 *
 * @author Vratko Polak
 */
public interface Q10skHlwnpoWiring {

    /**
     * Return a 0 node.
     *
     * @return 0 node, perhaps newly constructed, perhaps reference to existing one.
     */
    Q10skHlwnpo0Node get0Node();

    /**
     * Return a 1 node.
     *
     * @return 1 node, perhaps newly constructed, perhaps reference to existing one.
     */
    Q10skHlwnpo1Node get1Node();

    /**
     * Return a K node.
     *
     * @return K node, perhaps newly constructed, perhaps reference to existing one.
     */
    Q10skHlwnpoKNode getKNode();

    /**
     * Return a Q node.
     *
     * @return Q node, perhaps newly constructed, perhaps reference to existing one.
     */
    Q10skHlwnpoQNode getQNode();

    /**
     * Return a S node.
     *
     * @return S node, perhaps newly constructed, perhaps reference to existing one.
     */
    Q10skHlwnpoSNode getSNode();

}
