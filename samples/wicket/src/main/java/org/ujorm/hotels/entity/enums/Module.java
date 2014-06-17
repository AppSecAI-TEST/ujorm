/*
 *  Copyright 2014 Pavel Ponec
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package org.ujorm.hotels.entity.enums;

import org.ujorm.extensions.StringWrapper;

/**
 * Param module
 * @author Pavel Ponec
 */
public enum Module implements StringWrapper {

    /** The base applicatin */
    HOTELS("ht");

    /** Short identifier */
    private final String id;

    private Module(String id) {
        this.id = id;
    }

    @Override
    public String exportToString() {
        return id;
    }

}
