package com.tayaba.simplestudyviewer.web.validators;

import com.tayaba.simplestudyviewer.models.Study;
import com.tayaba.simplestudyviewer.web.api.repositories.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudyValidator implements Validator {

    @Autowired
    private StudyRepository studyRepository;

    @Override
    public boolean supports(Class<?> paramClass) {

        return Study.class.equals(paramClass);

    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");

        Study study = (Study) target;

        if(studyRepository.existsStudyByPersonCode(study.getPersonCode())){

            errors.rejectValue("personCode", "duplicateValue", new Object[]{"'personCode'"}, "personCode has to be unique");

        }
    }
}
