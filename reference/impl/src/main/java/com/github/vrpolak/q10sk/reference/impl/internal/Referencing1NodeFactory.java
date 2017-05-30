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

package com.github.vrpolak.q10sk.reference.impl;

import com.github.vrpolak.q10sk.reference.api.Q10skWnpo1Node;
import com.github.vrpolak.q10sk.reference.api.Q10skWnpo1NodeFactory;

/**
 * Immutable object for referencing a single 1 node of q10sk state tree.
 *
 * @author Vratko Polak
 */
public class Referencing1NodeFactory implements Q10skWnpo1NodeFactory {

    /**
     * The remembered node.
     */
    private final Q10skWnpo1Node node;

    /**
     * Constructor which remembers a node.
     */
    public Referencing1NodeFactory(final Q10skWnpo1Node node) {
        this.node = node;
    }

    /**
     * The remembered node. May be called multiple times.
     *
     * @return 1 node, each call references the same instance.
     */
    @Override
    public Q10skWnpo1Node create() {
        return node;
    }

}
