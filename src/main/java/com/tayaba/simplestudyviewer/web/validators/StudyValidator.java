package com.tayaba.simplestudyviewer.web.validators;

import com.tayaba.simplestudyviewer.dto.StudyDto;
import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class StudyValidator implements Validator {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public boolean supports(Class<?> paramClass) {
        return StudyDto.class.equals(paramClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudyDto studyDto = (StudyDto) target;

        Optional<Patient> patientOptional = patientRepository.findById(studyDto.getPatientId());
        if (!patientOptional.isPresent()) {
            errors.rejectValue("patientId", "Patient with this ID does not exists",
                    "Patient with this ID does not exists");
        }
    }
}
