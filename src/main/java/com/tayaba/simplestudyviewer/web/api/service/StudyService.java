package com.tayaba.simplestudyviewer.web.api.service;

import com.tayaba.simplestudyviewer.dto.StudyDto;
import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.models.Study;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import com.tayaba.simplestudyviewer.web.api.repositories.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudyService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Transactional
    public Study saveStudy(StudyDto studyDto) {

        Patient patient = getPatient(studyDto);

        Study study = new Study();
        study.setStudyName(studyDto.getStudyName());
        study.setStudyDescription(studyDto.getStudyDescription());
        study.setPatient(patient);

        return studyRepository.save(study);
    }

    @Transactional
    public Study updateStudy(StudyDto studyDto) {

        Patient patient = getPatient(studyDto);

        Optional<Study> studyOptional = studyRepository.findById(studyDto.getId());
        if (!studyOptional.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        Study study = studyOptional.get();

        study.setStudyName(studyDto.getStudyName());
        study.setStudyDescription(studyDto.getStudyDescription());
        study.setPatient(patient);

        return studyRepository.save(study);
    }

    private Patient getPatient(StudyDto studyDto) {
        Optional<Patient> patientOptional = patientRepository.findById(studyDto.getPatientId());
        if (!patientOptional.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return patientOptional.get();
    }
}
