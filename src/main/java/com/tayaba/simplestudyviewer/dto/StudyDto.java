package com.tayaba.simplestudyviewer.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class StudyDto {

    private long id;

    @NotEmpty
    private String studyName;

    @Size(max = 200)
    private String studyDescription;

    private long patientId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public String getStudyDescription() {
        return studyDescription;
    }

    public void setStudyDescription(String studyDescription) {
        this.studyDescription = studyDescription;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
