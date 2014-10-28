/*
 * Copyright 2014, Pavel Ponec
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
package org.ujorm.hotels.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.ujorm.Key;
import org.ujorm.core.KeyFactory;
import org.ujorm.hotels.entity.enums.Module;
import org.ujorm.hotels.services.annot.PersonalParam;
import org.ujorm.orm.annot.Comment;
/**
 * Common database service implementations
 * @author Ponec
 */
@Service("applParams")
public class ApplicationParams<U extends ApplicationParams> extends AbstractModuleParams {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationParams.class);

    /** Factory */
    private static final KeyFactory<ApplicationParams> f = newFactory(ApplicationParams.class);

    @Comment("Count of rows per a page in the table is a personal parameter")
    @PersonalParam
    public static final Key<ApplicationParams, Integer> ROWS_PER_PAGE = f.newKey("RowsPerPage", 10);
    @Comment("Parameter test 1 for the system")
    public static final Key<ApplicationParams, String> TEST1 = f.newKey("Test1", "A");
    @Comment("Parameter test 2 for the system")
    public static final Key<ApplicationParams, String> TEST2 = f.newKey("Test2", "B");
    @PersonalParam
    @Comment("Parameter test 3 for the user")
    public static final Key<ApplicationParams, String> TEST3 = f.newKey("Test3", "C");
    @PersonalParam
    @Comment("Parameter test 3 for the user")
    public static final Key<ApplicationParams, String> TEST4 = f.newKey("Test4", "D");

    static { f.lock(); }

    @Override
    public Module getModule() {
        return Module.HOTELS;
    }

    //<editor-fold defaultstate="collapsed" desc="Generated getters">
    public Integer getRowsPerPage() {
        return ROWS_PER_PAGE.of(this);
    }
    //</editor-fold>

}