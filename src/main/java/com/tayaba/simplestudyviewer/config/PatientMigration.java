package com.tayaba.simplestudyviewer.config;


import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Profile(value = "dev")
public class PatientMigration implements ApplicationRunner {

    @Autowired
    private PatientRepository patientRepository;

    public void run(ApplicationArguments args) throws ParseException {
        Date d1 = getDateFromString("1995-04-13T10:00:00");
        Date d2 = getDateFromString("1996-04-13T10:00:00");
        Date d3 = getDateFromString("1997-04-13T10:00:00");

        Patient p1 = createMockPatient("P-001", "Kylo", "Ren", d1);
        Patient p2 = createMockPatient("P-002", "Jar", "Jar Binks", d2);
        Patient p3 = createMockPatient("P-003", "Han", "Solo", d3);

        patientRepository.save(p1);
        patientRepository.save(p2);
        patientRepository.save(p3);
    }

    private Patient createMockPatient(String personCode, String firstName, String lastName, Date dob) {
        Patient patient = new Patient();

        patient.setPersonCode(personCode);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDateOfBirth(dob);

        return patient;
    }

    private Date getDateFromString(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        return simpleDateFormat.parse(dateStr);
    }
}

