package com.tayaba.simplestudyviewer.web.api.service;

import com.tayaba.simplestudyviewer.dto.StudyDto;
import com.tayaba.simplestudyviewer.models.Patient;
import com.tayaba.simplestudyviewer.models.Study;
import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import com.tayaba.simplestudyviewer.web.api.repositories.StudyRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudyServiceTest {

    @InjectMocks
    StudyService studyService;

    @Mock
    PatientRepository patientRepository;

    @Mock
    StudyRepository studyRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldThrowExceptionWhenPatientIdNotInDBWhileSaving() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        StudyDto dto = new StudyDto();

        dto.setPatientId(1L);

        studyService.saveStudy(dto);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldThrowExceptionWhenPatientIdNotInDBWhileUpdating() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        StudyDto dto = new StudyDto();

        dto.setPatientId(1L);

        studyService.updateStudy(dto);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldThrowExceptionWhenStudyIdNotInDBWhileUpdating() {
        StudyDto dto = new StudyDto();

        dto.setId(1L);

        studyService.updateStudy(dto);
    }

    @Test
    public void shouldCallStudyRepositorySaveMethodOnlyOnceWhenUpdating() {
        when(studyRepository.findById(1L)).thenReturn(Optional.of(new Study()));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(new Patient()));

        StudyDto dto = new StudyDto();
        dto.setId(1L);
        dto.setPatientId(1L);

        studyService.updateStudy(dto);

        verify(studyRepository, atMost(1)).save(any());
    }

    @Test
    public void shouldCallStudyRepositorySaveMethodOnlyOnceWhenSaving() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(new Patient()));

        StudyDto dto = new StudyDto();
        dto.setId(1L);
        dto.setPatientId(1L);

        studyService.saveStudy(dto);

        verify(studyRepository, atMost(1)).save(any());
    }

}
