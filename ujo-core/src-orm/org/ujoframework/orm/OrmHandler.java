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

package org.ujoframework.orm;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ujoframework.UjoProperty;
import org.ujoframework.core.UjoManager;
import org.ujoframework.orm.metaModel.OrmDatabase;
import org.ujoframework.orm.metaModel.OrmRoot;
import org.ujoframework.implementation.orm.TableUjo;
import org.ujoframework.orm.metaModel.OrmColumn;
import org.ujoframework.orm.metaModel.OrmRelation2Many;
import org.ujoframework.orm.metaModel.OrmTable;

/**
 * The basic class for an ORM support.
 * @author Pavel Ponec
 * @composed 1 - 1 OrmRoot
 */
public class OrmHandler {

    public static final Logger LOGGER = Logger.getLogger(OrmHandler.class.getName());

    private static OrmHandler handler = new OrmHandler();

    private Session session = new Session(this);
    private OrmRoot databases = new OrmRoot();

    /** Map a property to a database column model */
    private HashMap<UjoProperty,OrmRelation2Many> propertyMap = new HashMap<UjoProperty,OrmRelation2Many> ();

    /** The Sigleton constructor */
    protected OrmHandler() {
    }

    public static OrmHandler getInstance() {
        return handler;
    }

     /** Get Session 
      * <br/>TODO: getSession from a map by key.
      */
    public Session getSession() {
        return session;
    }

    /** Is the parameter a persistent property? */
    public boolean isPersistent(UjoProperty property) {
        
        final boolean resultFalse
        =  property.isTypeOf(List.class)
        || UjoManager.getInstance().isTransientProperty(property)
        ;
        return !resultFalse;
    }

    /** load a database model from paramater */
    public <UJO extends TableUjo> OrmDatabase loadDatabase(Class<? extends UJO> databaseModel) {
        UJO model = getInstance(databaseModel);
        OrmDatabase dbModel  = new OrmDatabase(model);
        databases.add(dbModel);

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("DATABASE META-MODEL:\n"+databases.toString());
        }
        
        return dbModel;
    }

    /** load a metada and create database */
    public <UJO extends TableUjo> OrmDatabase createDatabase(Class<UJO> databaseModel) {

        OrmDatabase dbModel = loadDatabase(databaseModel);
        dbModel.create();

        return dbModel;
    }


    /** Create an instance from the class */
    private <UJO extends TableUjo> UJO getInstance(Class<UJO> databaseModel) {
        try {
            return databaseModel.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't create instance of " + databaseModel, e);
        }
    }



    /** Map a property to the table */
    @SuppressWarnings("unchecked")
    public void addProperty(UjoProperty property, OrmRelation2Many newColumn) {

        OrmRelation2Many oldColumn = findColumnModel(property);

        if (oldColumn == null) {
            propertyMap.put(property, newColumn);
        } else {
            final OrmTable oldTable = OrmColumn.TABLE.of(oldColumn);
            final OrmTable newTable = OrmColumn.TABLE.of(newColumn);

            final Class oldType = OrmTable.DB_PROPERTY.of(oldTable).getItemType();
            final Class newType = OrmTable.DB_PROPERTY.of(newTable).getItemType();

            if (newType.isAssignableFrom(oldType)) {
                // Only a parent can be assigned:
                propertyMap.put(property, newColumn);
            }
        }
    }

    /** Find a table of the paramemeter property. */
    public OrmTable findTableModel(UjoProperty property) {
        final OrmRelation2Many column = findColumnModel(property);
        return column!=null ? OrmRelation2Many.TABLE.of(column) : null ;
    }

    /** Find a table of the paramemeter property. */
    public OrmRelation2Many findColumnModel(UjoProperty property) {
        final OrmRelation2Many result = propertyMap.get(property);
        return result;
    }

    /** Find a table model by the dbClass. Returns null of table is not found. */
    public OrmTable findTableModel(Class<? extends TableUjo> dbClass) {
        for (OrmDatabase db : OrmRoot.DATABASES.getList(databases)) {
            for (OrmTable table : OrmDatabase.TABLES.getList(db)) {
                // Class has a unique instance in the same classloader:
                if (OrmTable.DB_PROPERTY.of(table).getItemType()==dbClass) {
                    return table;
                }
            }
        }
        return null;
    }

}
