package ru.kpfu.itis.app.validators;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.app.forms.CalcForm;
import ru.kpfu.itis.app.other.MyClass;

@Component
public class CalcFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(CalcForm.class.getName());
    }
    @Transactional
    @Override
    public void validate(Object target, Errors errors) {
        CalcForm form = (CalcForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "a", "empty.a", "пустой a");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "b", "empty.b", "пустой b");

       if(!MyClass.isDouble(form.getA())){
            errors.reject("bad.a", "a - введено не число");
        }
        if (!MyClass.isDouble(form.getB())) {
            errors.reject("bad.b", "b - введено не число");
        }


    }
}
