package com.tayaba.simplestudyviewer.web.api.controller;

import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.utils.exceptions.ApiValidationException;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import com.tayaba.simplestudyviewer.web.validators.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientValidator validator;

    @InitBinder
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public Iterable<Patient> getPatients() {

        return patientRepository.findAll();
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public Patient addPatient(@Valid @RequestBody Patient patient, BindingResult result) {

        if(result.hasErrors()){

            throw new ApiValidationException(result.getFieldErrors());
        }
        return patientRepository.save(patient);
    }

    @RequestMapping(value = "/patients", method = RequestMethod.PATCH)
    public Patient updatePatient(@Valid @RequestBody Patient patient, BindingResult result) {

        if(result.hasErrors()){

            throw new ApiValidationException(result.getFieldErrors());
        }

        return patientRepository.save(patient);
    }

    @RequestMapping(value = "/patients/{id}", method = RequestMethod.DELETE)
    public Patient removePatient(@PathVariable("id") Long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {

            patientRepository.deleteById(id);
            return patient.get();
        }

        throw new EmptyResultDataAccessException(1);
    }


}
