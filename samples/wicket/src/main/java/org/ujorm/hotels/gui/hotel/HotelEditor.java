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
package org.ujorm.hotels.gui.hotel;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.model.IModel;
import org.ujorm.hotels.entity.City;
import org.ujorm.hotels.entity.Hotel;
import org.ujorm.wicket.component.dialog.EntityDialogPanel;

/**
 * Hotel Editor
 * @author Pavel Ponec
 */
public class HotelEditor extends EntityDialogPanel<Hotel> {
    private static final long serialVersionUID = 0L;

    public HotelEditor(ModalWindow modalWindow, IModel<Hotel> model) {
        super(modalWindow, model);

        // Editable fields:
        fields.add(Hotel.NAME);
        fields.add(Hotel.CITY, City.ID.forAll(), City.NAME);
        fields.add(Hotel.STREET);
        fields.add(Hotel.PHONE);
        fields.add(Hotel.STARS);
        fields.add(Hotel.PRICE);
        fields.add(Hotel.CURRENCY);
        fields.add(Hotel.NOTE);
        fields.add(Hotel.ACTIVE);
    }

}