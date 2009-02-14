/*
 *  Copyright 2009 Paul Ponec
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

package org.ujoframework.orm.sample;

import java.util.Date;
import org.ujoframework.UjoProperty;
import org.ujoframework.orm.DbType;
import org.ujoframework.orm.annot.Column;
import org.ujoframework.implementation.orm.TableUjo;
import org.ujoframework.implementation.orm.RelationToMany;

/**
 * Simple order for goods with a collection of Items (a sample).
 * @Table=bo_order
 */
public class BoOrder extends TableUjo {

    /** Unique key */
    @Column(pk=true)
    public static final UjoProperty<BoOrder,Long> ID = newProperty("id", Long.class);
    /** User key */
    public static final UjoProperty<BoOrder,Integer> USER_ID = newProperty("usrId", Integer.class);
    /** Description of the order */
    @Column(type=DbType.VARCHAR, name="DESCR")
    public static final UjoProperty<BoOrder,String> DESCR = newProperty("description", String.class);
    /** Date of creation */
    public static final UjoProperty<BoOrder,Date> DATE = newProperty("date", Date.class);
    /** References to Itemsr */
    public static final RelationToMany<BoOrder,BoItem> ITEMS = newRelation("items", BoItem.class);

}
