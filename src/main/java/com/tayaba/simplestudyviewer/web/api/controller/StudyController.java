package com.tayaba.simplestudyviewer.web.api.controller;

import com.tayaba.simplestudyviewer.dto.StudyDto;
import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.models.Study;
import com.tayaba.simplestudyviewer.utils.exceptions.ApiValidationException;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import com.tayaba.simplestudyviewer.web.api.repositories.StudyRepository;
import com.tayaba.simplestudyviewer.web.api.service.StudyService;
import com.tayaba.simplestudyviewer.web.validators.StudyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudyController {

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private StudyService studyService;

    @Autowired
    private StudyValidator validator;

    @InitBinder
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/studies", method = RequestMethod.GET)
    public Iterable<Study> getStudies() {

        return studyRepository.findAllByOrderByStudyCreationTimeDescStudyUpdateTimeDesc();
    }

    @RequestMapping(value = "/studies", method = RequestMethod.POST)
    public Study addStudy(@Valid @RequestBody StudyDto studyDto, BindingResult result) {

        if (result.hasErrors()) {

            throw new ApiValidationException(result.getFieldErrors());
        }

        return studyService.saveStudy(studyDto);
    }

    @RequestMapping(value = "/studies/{id}", method = RequestMethod.POST)
    public Study updateStudy(@Valid @RequestBody StudyDto studyDto, BindingResult result) {

        if (result.hasErrors()) {

            throw new ApiValidationException(result.getFieldErrors());
        }

        return studyService.updateStudy(studyDto);
    }

    @RequestMapping(value = "/studies/{id}", method = RequestMethod.DELETE)
    public Study removeStudy(@PathVariable("id") Long id) {
        Optional<Study> study = studyRepository.findById(id);

        if (study.isPresent()) {

            studyRepository.deleteById(id);
            return study.get();
        }

        throw new EmptyResultDataAccessException(1);
    }

    @RequestMapping(value = "/studies/{id}", method = RequestMethod.GET)
    public Study getStudy(@PathVariable("id") Long id) {
        Optional<Study> study = studyRepository.findById(id);

        if (study.isPresent()) {

            return study.get();
        }

        throw new EmptyResultDataAccessException(1);
    }
}
