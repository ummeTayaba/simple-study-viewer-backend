package com.tayaba.simplestudyviewer.web.api.controller;

import com.tayaba.simplestudyviewer.models.Study;
import com.tayaba.simplestudyviewer.utils.exceptions.ApiValidationException;
import com.tayaba.simplestudyviewer.web.api.repositories.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class StudyController {

    @Autowired
    private StudyRepository studyRepository;


    @RequestMapping(value = "/studies", method = RequestMethod.GET)
    public Iterable<Study> getStudies() {

        return studyRepository.findAll();
    }

    @RequestMapping(value = "/studies", method = RequestMethod.POST)
    public Study addStudy(@Valid @RequestBody Study study, BindingResult result) {

        if(result.hasErrors()){

            throw new ApiValidationException(result.getAllErrors());
        }
        return studyRepository.save(study);
    }

    @RequestMapping(value = "/studies", method = RequestMethod.PATCH)
    public Study updateStudy(@Valid @RequestBody Study study, BindingResult result) {

        if(result.hasErrors()){

            throw new ApiValidationException(result.getAllErrors());
        }
        return studyRepository.save(study);

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
}
