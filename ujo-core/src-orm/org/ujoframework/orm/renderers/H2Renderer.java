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

package org.ujoframework.orm.renderers;

import java.io.IOException;
import org.ujoframework.UjoProperty;
import org.ujoframework.orm.DbHandler;
import org.ujoframework.orm.SqlRenderer;
import org.ujoframework.orm.metaModel.Db;
import org.ujoframework.orm.metaModel.DbColumn;
import org.ujoframework.orm.metaModel.DbPK;
import org.ujoframework.orm.metaModel.DbTable;

/** H2 (http://www.h2database.com) */
public class H2Renderer implements SqlRenderer {

    /** Returns a default JDBC Driver */
    public String getJdbcUrl() {
        return "jdbc:h2:mem:";
    }

    /** Returns a JDBC Driver */
    public String getJdbcDriver() {
        return "org.h2.Driver";
    }

    /** Print a SQL script to crate database */
    public void createDatabase(Db database, Appendable writer) throws IOException {
        for (DbTable table : Db.TABLES.getList(database)) {
            printTable(table, writer);
            printForeignKey(table, writer);
        }
    }

    /** Print a SQL sript to create table */
    public void printTable(DbTable table, Appendable writer) throws IOException {
        writer.append("CREATE TABLE ");
        writer.append(DbTable.NAME.of(table));
        String separator = "\n\t( ";
        for (DbColumn column : DbTable.COLUMNS.getList(table)) {
            writer.append(separator);
            separator = "\n\t, ";

            if ( column.isForeignKey() ) {
                final UjoProperty property = DbColumn.TABLE_PROPERTY.of(column);
                @SuppressWarnings("unchecked")
                final DbTable foreignTable = DbHandler.getInstance().findTableModel(property.getType());
                printColumnFK(column, writer, getForeignKeyPrefix(foreignTable));
            } else {
                printColumn(column, writer, null);
            }
        }
        writer.append("\n\t);\n");
    }

    /** Print foreign key */
    public void printForeignKey(DbTable table, Appendable writer) throws IOException {
        for (DbColumn column : DbTable.COLUMNS.getList(table)) {
            if ( column.isForeignKey() ) {
                printForeignKey(column, table, writer);
            }
        }
    }

    /** Print foreign key for  */
    @SuppressWarnings("unchecked")
    public void printForeignKey(DbColumn column, DbTable table, Appendable writer) throws IOException {
        final UjoProperty property = DbColumn.TABLE_PROPERTY.of(column);
        final DbTable foreignTable = DbHandler.getInstance().findTableModel(property.getType());
        DbPK foreignKeys = DbTable.PK.of(foreignTable);

        writer.append("ALTER TABLE ");
        writer.append(DbTable.NAME.of(table));
        writer.append("\n\tADD FOREIGN KEY");

        String separator = "(";
        for (DbColumn fkColumn : DbPK.COLUMNS.of(foreignKeys)) {
            String prefix = getForeignKeyPrefix(foreignTable);


            writer.append(separator);
            separator = ", ";
            writer.append(prefix);
            writer.append(DbColumn.NAME.of(fkColumn));
        }

        writer.append(")\n\tREFERENCES ");
        writer.append(DbTable.NAME.of(foreignTable));
        separator = "(";

        for (DbColumn fkColumn : DbPK.COLUMNS.of(foreignKeys)) {
            writer.append(separator);
            separator = ", ";
            writer.append(DbColumn.NAME.of(fkColumn));
        }

        writer.append(")");
        //writer.append("\n\tON DELETE CASCADE");
        writer.append("\n\t;");

    }

    /** Print a SQL to create column */
    public void printColumn(DbColumn column, Appendable writer, String fkPrefix) throws IOException {

        if (fkPrefix!=null) {
            writer.append( fkPrefix );
        }

        writer.append( DbColumn.NAME.of(column) );
        writer.append( ' ' );
        writer.append( DbColumn.DB_TYPE.of(column).name() );

        if (!DbColumn.MAX_LENGTH.isDefault(column)) {
           writer.append( "(" + DbColumn.MAX_LENGTH.of(column) );
           if (!DbColumn.PRECISION.isDefault(column)) {
               writer.append( ", " + DbColumn.PRECISION.of(column) );
           }
           writer.append( ")" );
        }
        if (!DbColumn.MANDATORY.isDefault(column)) {
           writer.append( " NOT NULL" );
        }
        if (DbColumn.PRIMARY_KEY.of(column) && fkPrefix==null) {
           writer.append(" PRIMARY KEY");
        }
    }

    /** Print a SQL to create a Foreign key. */
    public void printColumnFK(DbColumn column, Appendable writer, String prefix) throws IOException {

        final UjoProperty property = DbColumn.TABLE_PROPERTY.of(column);
        final Class type = property.getType();
        final DbTable table = DbHandler.getInstance().findTableModel(property);
        final DbPK pk = DbTable.PK.of(table);
        String separator = "";
        
        for (DbColumn col : DbPK.COLUMNS.getList(pk)) {
            writer.append(separator);
            separator = "\n\t, ";
            printColumn(col, writer, prefix );
        }
    }

    /** Return a Primary Key prefix. */
    public String getForeignKeyPrefix(DbTable table) {

        StringBuilder sb = new StringBuilder(16);
        sb.append("fk_");
        sb.append(DbTable.NAME.of(table));
        sb.append("_");

        return sb.toString();
    }

}
