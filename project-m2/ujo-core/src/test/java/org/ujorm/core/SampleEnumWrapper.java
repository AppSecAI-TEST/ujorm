/*
 *  Copyright 2007-2014 Pavel Ponec
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ujorm.core;

import org.ujorm.extensions.StringWrapper;

/**
 *
 * @author Pavel Ponec
 */
public enum SampleEnumWrapper implements StringWrapper {
    ZERO, ONE, TWO;

    /** Returns the first character */
    public String exportToString() {
        return name().substring(0, 1);
    }
}
