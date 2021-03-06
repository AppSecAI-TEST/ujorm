/**
  * Copyright (C) 2008, Paval Ponec, contact: http://ujorm.org/
  *
  * This program is free software; you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation version 2 of the License.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You may obtain a copy of the License at
  * http://www.gnu.org/licenses/gpl-2.0.txt
  */

package org.impl;

import org.UIComponent;

/**
 * Component implementation
 * @author Ponec
 */
public class UIComponentDouble implements UIComponent<Double> {

    private Double value;

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public void setStyle(String attr, String value) {
        // map.put(attr, value);
    }

    @Override
    public boolean isValid() {
        return true;
    }

}
