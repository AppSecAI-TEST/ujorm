/*
 *  Copyright 2012-2013 Pavel Ponec
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
package org.ujorm.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ujorm.Key;
import org.ujorm.Ujo;
import org.ujorm.Validator;
import org.ujorm.criterion.BinaryOperator;
import org.ujorm.validator.impl.CompositeValidator;
import org.ujorm.validator.impl.LengthValidator;
import org.ujorm.validator.impl.NotNullValidator;

/**
 * Validator utils
 * @author Pavel Ponec
 */
public final class ValidatorUtils {
    
    /** Returns true if the validator contains a NotNull validator */
    public static boolean isMandatoryValidator(Validator validator) {
        List<LengthValidator> vals = new ArrayList<LengthValidator>();
        boolean ok = findValidators(validator, NotNullValidator.class, vals);        
        return ok && !vals.isEmpty();
    }
    
    /** Returns the maximal lenght from all LengthValidators. Undefined value is -1. */
    public static int getMaxLength(Validator validator) {
        int result = -1;
        List<LengthValidator> vals = new ArrayList<LengthValidator>();
        findValidators(validator, LengthValidator.class, vals);
        
        for (LengthValidator v : vals) {
            result = Math.max(result, v.getMaxLength());
        }
        return result;
    }
    
    /** Find a required validators 
     * @param validator Nullable validator
     * @param requiredType requiredType
     * @param validators Result list of validators
     * @return return true if all operators are AND.
     */
    protected static boolean findValidators(Validator validator, Class<? extends Validator> requiredType, List<? extends Validator> validators) {
        boolean and = true;
        if (validator instanceof CompositeValidator) {
            CompositeValidator cv = (CompositeValidator) validator;
            and = cv.getOperator() == BinaryOperator.AND;
            and = and && findValidators(cv.getLeftValidator(), requiredType, validators);
            and = and && findValidators(cv.getRightValidator(), requiredType, validators);
        } else if (requiredType.isInstance(validator)) {
            ((List<Validator>)validators).add(validator);
        }
        return and;
    }
    
    /** Validate the argument using all keys from the object. */
    public static List<ValidationError> validate(final Ujo ujo) {
        final ArrayList<ValidationError> result = new ArrayList<ValidationError>();
        for (Key key : ujo.readKeys()) {
            final ValidationError err = key.getValidator().validate(key.of(ujo), key, ujo);
            if (err!=null) {
                result.add(err);
            }
        }
        return result;
    }

    /** Validate the argument using all keys from the collection. */
    public static List<ValidationError> validate(final Collection<Ujo> ujos) {
        final ArrayList<ValidationError> result = new ArrayList<ValidationError>();
        for (Ujo ujo : ujos) {
            for (Key key : ujo.readKeys()) {
                final ValidationError err = key.getValidator().validate(key.of(ujo), key, ujo);
                if (err!=null) {
                    result.add(err);
                }
            }
        }
        return result;
    }
    

}
