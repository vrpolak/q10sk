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

package com.github.vrpolak.q10sk.reference.impl.wiring;

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo0Node;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo1Node;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoKNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoQNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoWiring;
import com.github.vrpolak.q10sk.reference.impl._0.Simple0NodeFactoryFactory;
import com.github.vrpolak.q10sk.reference.impl._0x.Simple0xNodeFactory;
import com.github.vrpolak.q10sk.reference.impl._1.Simple1NodeFactoryFactory;
import com.github.vrpolak.q10sk.reference.impl._1x.Simple1xNodeFactory;
import com.github.vrpolak.q10sk.reference.impl.apply.SimpleApplyNodeFactory;
import com.github.vrpolak.q10sk.reference.impl.k.SimpleKNodeFactoryFactory;
import com.github.vrpolak.q10sk.reference.impl.kx.SimpleKxNodeFactory;
import com.github.vrpolak.q10sk.reference.impl.q.SimpleQNodeFactoryFactory;
import com.github.vrpolak.q10sk.reference.impl.qx.SimpleQxNodeFactoryFactory;
import com.github.vrpolak.q10sk.reference.impl.qxy.SimpleQxyNodeFactory;
import com.github.vrpolak.q10sk.reference.impl.s.SimpleSNodeFactoryFactory;
import com.github.vrpolak.q10sk.reference.impl.sx.SimpleSxNodeFactoryFactory;
import com.github.vrpolak.q10sk.reference.impl.sxy.SimpleSxyNodeFactoryFactory;
import com.github.vrpolak.q10sk.reference.impl.sxyz.SimpleSxyzNodeFactoryFactory;

/**
 * Default implementation of wiring without any additional arguments.
 *
 * @author Vratko Polak
 */
public class DefaultWiring implements Q10skHlwnpoWiring {

    /**
     * The 0 node to get.
     */
    private final Q10skHlwnpo0Node the0Node = (new Simple0NodeFactoryFactory())
                                                  .create(new Simple0xNodeFactory())
                                              .create();
    /**
     * The 1 node to get.
     */
    private final Q10skHlwnpo1Node the1Node = (new Simple1NodeFactoryFactory())
                                                  .create(new Simple1xNodeFactory())
                                             .create();
    /**
     * The K node to get.
     */
    private final Q10skHlwnpoKNode theKNode = (new SimpleKNodeFactoryFactory())
                                                  .create(new SimpleKxNodeFactory())
                                              .create();
    /**
     * The Q node to get.
     */
    private final Q10skHlwnpoQNode theQNode = (new SimpleQNodeFactoryFactory())
                                                  .create((new SimpleQxNodeFactoryFactory())
                                                      .create(new SimpleQxyNodeFactory()))
                                              .create();
    /**
     * The S node to get.
     */
    private final Q10skHlwnpoSNode theSNode = (new SimpleSNodeFactoryFactory())
                                                  .create((new SimpleSxNodeFactoryFactory())
                                                      .create((new SimpleSxyNodeFactoryFactory())
                                                          .create((new SimpleSxyzNodeFactoryFactory())
                                                              .create(new SimpleApplyNodeFactory()))))
                                              .create();

    // The implicit zero-argument constructor is public for anyone to use.

    /**
     * Return a simple implementation of 0 node.
     *
     * @return 0 node, reference to the one constructed in the constructor.
     */
    @Override
    public Q10skHlwnpo0Node get0Node() {
        return this.the0Node;
    }

    /**
     * Return a simple implementation of 1 node.
     *
     * @return 1 node, reference to the one constructed in the constructor.
     */
    @Override
    public Q10skHlwnpo1Node get1Node() {
        return this.the1Node;
    }

    /**
     * Return a simple implementation of K node.
     *
     * @return K node, reference to the one constructed in the constructor.
     */
    @Override
    public Q10skHlwnpoKNode getKNode() {
        return this.theKNode;
    }

    /**
     * Return a simple implementation of Q node.
     *
     * @return Q node, reference to the one constructed in the constructor.
     */
    @Override
    public Q10skHlwnpoQNode getQNode() {
        return this.theQNode;
    }

    /**
     * Return a simple implementation of S node.
     *
     * @return S node, reference to the one constructed in the constructor.
     */
    @Override
    public Q10skHlwnpoSNode getSNode() {
        return this.theSNode;
    }

}
