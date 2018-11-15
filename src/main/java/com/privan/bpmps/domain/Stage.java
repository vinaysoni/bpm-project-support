package com.privan.bpmps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Stage.
 */
@Entity
@Table(name = "stage")
public class Stage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stage_name")
    private String stageName;

    @ManyToOne
    @JsonIgnoreProperties("stages")
    private Project project;

    @OneToMany(mappedBy = "stage")
    private Set<Worker> workers = new HashSet<>();
    @OneToMany(mappedBy = "stage")
    private Set<File> files = new HashSet<>();
    @OneToMany(mappedBy = "stage")
    private Set<Properties> properties = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public Stage stageName(String stageName) {
        this.stageName = stageName;
        return this;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Project getProject() {
        return project;
    }

    public Stage project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public Stage workers(Set<Worker> workers) {
        this.workers = workers;
        return this;
    }

    public Stage addWorker(Worker worker) {
        this.workers.add(worker);
        worker.setStage(this);
        return this;
    }

    public Stage removeWorker(Worker worker) {
        this.workers.remove(worker);
        worker.setStage(null);
        return this;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public Set<File> getFiles() {
        return files;
    }

    public Stage files(Set<File> files) {
        this.files = files;
        return this;
    }

    public Stage addFile(File file) {
        this.files.add(file);
        file.setStage(this);
        return this;
    }

    public Stage removeFile(File file) {
        this.files.remove(file);
        file.setStage(null);
        return this;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public Set<Properties> getProperties() {
        return properties;
    }

    public Stage properties(Set<Properties> properties) {
        this.properties = properties;
        return this;
    }

    public Stage addProperty(Properties properties) {
        this.properties.add(properties);
        properties.setStage(this);
        return this;
    }

    public Stage removeProperty(Properties properties) {
        this.properties.remove(properties);
        properties.setStage(null);
        return this;
    }

    public void setProperties(Set<Properties> properties) {
        this.properties = properties;
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
        Stage stage = (Stage) o;
        if (stage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Stage{" +
            "id=" + getId() +
            ", stageName='" + getStageName() + "'" +
            "}";
    }
}
