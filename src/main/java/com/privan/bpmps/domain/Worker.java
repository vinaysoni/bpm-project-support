package com.privan.bpmps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Worker.
 */
@Entity
@Table(name = "worker")
public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @Column(name = "onleave")
    private Boolean onleave;

    @Column(name = "onleave_till")
    private Instant onleaveTill;

    @ManyToOne
    @JsonIgnoreProperties("workers")
    private Project project;

    @ManyToOne
    @JsonIgnoreProperties("workers")
    private Stage stage;

    @OneToMany(mappedBy = "worker")
    private Set<File> files = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public Worker userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean isIsPrimary() {
        return isPrimary;
    }

    public Worker isPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
        return this;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Boolean isOnleave() {
        return onleave;
    }

    public Worker onleave(Boolean onleave) {
        this.onleave = onleave;
        return this;
    }

    public void setOnleave(Boolean onleave) {
        this.onleave = onleave;
    }

    public Instant getOnleaveTill() {
        return onleaveTill;
    }

    public Worker onleaveTill(Instant onleaveTill) {
        this.onleaveTill = onleaveTill;
        return this;
    }

    public void setOnleaveTill(Instant onleaveTill) {
        this.onleaveTill = onleaveTill;
    }

    public Project getProject() {
        return project;
    }

    public Worker project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Stage getStage() {
        return stage;
    }

    public Worker stage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Set<File> getFiles() {
        return files;
    }

    public Worker files(Set<File> files) {
        this.files = files;
        return this;
    }

    public Worker addFile(File file) {
        this.files.add(file);
        file.setWorker(this);
        return this;
    }

    public Worker removeFile(File file) {
        this.files.remove(file);
        file.setWorker(null);
        return this;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        if (worker.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), worker.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Worker{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", isPrimary='" + isIsPrimary() + "'" +
            ", onleave='" + isOnleave() + "'" +
            ", onleaveTill='" + getOnleaveTill() + "'" +
            "}";
    }
}
