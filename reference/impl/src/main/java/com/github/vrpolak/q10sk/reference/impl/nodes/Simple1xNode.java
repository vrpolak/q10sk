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

package com.github.vrpolak.q10sk.reference.impl._1x;  // to only allow 1x factory acces to the constructor

import com.github.vrpolak.q10sk.reference.api.BitConsumer;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo1xNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo1xNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;
import com.github.vrpolak.q10sk.reference.impl.SimpleBit;

/**
 * Immutable object representing a 1x hlwnpo node.
 *
 * @author Vratko Polak
 */
public class Simple1xNode implements Q10skHlwnpo1xNode {

    private final Q10skHlwnpoNode argumentX;
    private final Q10skHlwnpo1xNodeFactory simple1xFactory;

    // Package-private constructor for *Factory to use.
    Simple1xNode(final Q10skHlwnpoNode argumentX, final Q10skHlwnpo1xNodeFactory simple1xFactory) {
        this.argumentX = argumentX;
        this.simple1xFactory = simple1xFactory;
    }

    @Override
    public Q10skHlwnpo1xNode apply(final Q10skHlwnpoNode argumentY) {
        return this.simple1xFactory.create(this.argumentX.apply(argumentY));
    }

    @Override
    public Simple1xNode weaklyNormalize() {
        return this;
    }

    @Override
    public Q10skHlwnpoNode output(final BitConsumer consumer) {
        consumer.consumeBit(SimpleBit.ONE);
        return argumentX;
    }

}
