package com.tayaba.simplestudyviewer.web.validators;

import com.tayaba.simplestudyviewer.dto.PatientDto;
import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.Optional;

@Component
public class PatientValidator implements Validator {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public boolean supports(Class<?> paramClass) {

        return PatientDto.class.equals(paramClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PatientDto patient = (PatientDto) target;
        Optional<Patient> patientOptional = patientRepository.findById(patient.getId());

        boolean shouldCheckForPersonCode = patientOptional
                                                .map(value -> !value.getPersonCode().equals(patient.getPersonCode()))
                                                .orElse(true);

        if(shouldCheckForPersonCode
                && patientRepository.existsPatientByPersonCode(patient.getPersonCode())){

            errors.rejectValue("personCode", "duplicateValue", new Object[]{"'personCode'"}, "Person Code has to be unique");
        }

        // Not considering Timezones for now
        if (patient.getDateOfBirth().after(new Date())) {
            errors.rejectValue("dateOfBirth", "dateOfBirth", new Object[]{"'dateOfBirth'"}, "Date of birth cannot be in the future");
        }
    }

}
