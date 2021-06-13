package com.tayaba.simplestudyviewer.web.api.repositories;

import com.tayaba.simplestudyviewer.models.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    boolean existsPatientByPersonCode(String personCode);
}
