package com.tayaba.simplestudyviewer.web.api.service;

import com.tayaba.simplestudyviewer.dto.PatientDto;
import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public Patient savePatient(PatientDto patientDto) {

        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setPersonCode(patientDto.getPersonCode());
        patient.setDateOfBirth(patientDto.getDateOfBirth());

        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(PatientDto patientDto) {

        Optional<Patient> patientOptional = patientRepository.findById(patientDto.getId());

        if (!patientOptional.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        Patient patient = patientOptional.get();

        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setPersonCode(patientDto.getPersonCode());

        return patientRepository.save(patient);
    }
}
