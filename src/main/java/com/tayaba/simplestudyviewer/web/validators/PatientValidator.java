package com.tayaba.simplestudyviewer.web.validators;

import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PatientValidator implements Validator {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public boolean supports(Class<?> paramClass) {

        return Patient.class.equals(paramClass);

    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");

        Patient patient = (Patient) target;

        if(patientRepository.existsPatientByPersonCode(patient.getPersonCode())){

            errors.rejectValue("personCode", "duplicateValue", new Object[]{"'personCode'"}, "personCode has to be unique");

        }
    }

}
