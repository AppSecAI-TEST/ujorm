/*
 * Copyright 2013, Pavel Ponec
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
package org.ujorm.hotels.gui.customer;

import org.apache.wicket.markup.html.panel.Panel;
import org.ujorm.hotels.domains.Customer;
import org.ujorm.wicket.component.gridView.UjoDataProvider;

/**
 *
 * @author Pavel Ponec
 */
public class CustomerPanel extends Panel {

    public CustomerPanel(String id) {
        super(id);

        UjoDataProvider<Customer> dataProvider = UjoDataProvider.of(Customer.ACTIVE.whereEq(true));

        dataProvider.addColumn(Customer.LOGIN);
        dataProvider.addColumn(Customer.TITLE);
        dataProvider.addColumn(Customer.FIRSTNAME);
        dataProvider.addColumn(Customer.SURENAME);
        dataProvider.addColumn(Customer.EMAIL);
        dataProvider.addColumn(Customer.ADMIN);
        dataProvider.addColumn(Customer.ACTIVE);
        dataProvider.setSort(Customer.LOGIN);

        add(dataProvider.createDataTable("datatable", 10));

    }

}