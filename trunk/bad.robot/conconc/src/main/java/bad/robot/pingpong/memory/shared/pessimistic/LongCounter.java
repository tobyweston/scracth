/*
 * Copyright (c) 2009-2010, bad robot (london) ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bad.robot.pingpong.memory.shared.pessimistic;

import bad.robot.pingpong.memory.shared.Counter;

import java.util.concurrent.atomic.AtomicLong;

public abstract class LongCounter implements Counter<Long> {

    private static AtomicLong value = new AtomicLong();

    public void increment() {
        value.incrementAndGet();
    }

    public void decrement() {
        value.decrementAndGet();
    }

    public Long get() {
        return value.get();
    }

    public void reset() {
        value = new AtomicLong();
    }
}
