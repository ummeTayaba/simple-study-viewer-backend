package com.tayaba.simplestudyviewer.web.api.controller;

import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public Iterable<Patient> getPatients() {

        return patientRepository.findAll();
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public Patient addPatient(Patient patient) {

        return patientRepository.save(patient);
    }

    @RequestMapping(value = "/patients", method = RequestMethod.PATCH)
    public Patient updatePatient(Patient patient) {
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
