/*
 *  Copyright 2009-2010 Pavel Ponec
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

package org.ujoframework.orm.annot;
import java.lang.annotation.*;
import org.ujoframework.orm.DbType;

/** 
 * Use the annotation to mark a UjoProperty static field like XML Attribute.
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
public @interface Column {

    /** A named parameter for the database column name.
     * If an appropriate UjoProperty is a relation to another ORM object with more primary keys, 
     * then the several names can be separated by a space or comma character.
     */
    String name() default Table.NULL;
    /** A shortcut for the attribute "name" of Column.
     * @see #name()
     */
    String value() default Table.NULL;
    /** The primary key */
    boolean pk() default false;
    /** Database column type */
    DbType type() default DbType.Automatic;
    /** Database column maximal length */
    int length() default -1;
    /** Database column presision */
    int precision() default -1;
    /** Not null value */
    boolean mandatory() default false;
    /** A name of the non-unique database index for the column, where the same index can contain more columns.
     * If a single column of the index is marked as non-unique, than the entire index will be non-unique. */
    String index() default Table.NULL;
    /** A name of the unique database index for the column, where the same index can contain more columns.
     * If a single column of the index is marked as non-unique, than the entire index will be non-unique. */
    String uniqueIndex() default Table.NULL;

    
}