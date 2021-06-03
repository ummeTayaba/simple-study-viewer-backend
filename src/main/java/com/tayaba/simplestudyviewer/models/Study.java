package com.tayaba.simplestudyviewer.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Study {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String studyName;

    @Size(max = 200)
    private String studyDescription;

    @CreationTimestamp
    private Date studyCreationTime;

    @UpdateTimestamp
    private Date studyUpdateTime;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

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

    public Date getStudyCreationTime() {
        return studyCreationTime;
    }

    public void setStudyCreationTime(Date studyCreationTime) {
        this.studyCreationTime = studyCreationTime;
    }

    public Date getStudyUpdateTime() {
        return studyUpdateTime;
    }

    public void setStudyUpdateTime(Date studyUpdateTime) {
        this.studyUpdateTime = studyUpdateTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
