package com.privan.bpmps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "name")
    private String name;

    @Column(name = "owner")
    private String owner;

    @Column(name = "owner_email")
    private String ownerEmail;

    @OneToMany(mappedBy = "project")
    private Set<Stage> stages = new HashSet<>();
    @OneToMany(mappedBy = "project")
    private Set<Worker> workers = new HashSet<>();
    @OneToMany(mappedBy = "project")
    private Set<Properties> properties = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Project startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Project endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public Project name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public Project owner(String owner) {
        this.owner = owner;
        return this;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public Project ownerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
        return this;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public Project stages(Set<Stage> stages) {
        this.stages = stages;
        return this;
    }

    public Project addStage(Stage stage) {
        this.stages.add(stage);
        stage.setProject(this);
        return this;
    }

    public Project removeStage(Stage stage) {
        this.stages.remove(stage);
        stage.setProject(null);
        return this;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public Project workers(Set<Worker> workers) {
        this.workers = workers;
        return this;
    }

    public Project addWorker(Worker worker) {
        this.workers.add(worker);
        worker.setProject(this);
        return this;
    }

    public Project removeWorker(Worker worker) {
        this.workers.remove(worker);
        worker.setProject(null);
        return this;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public Set<Properties> getProperties() {
        return properties;
    }

    public Project properties(Set<Properties> properties) {
        this.properties = properties;
        return this;
    }

    public Project addProperty(Properties properties) {
        this.properties.add(properties);
        properties.setProject(this);
        return this;
    }

    public Project removeProperty(Properties properties) {
        this.properties.remove(properties);
        properties.setProject(null);
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
        Project project = (Project) o;
        if (project.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), project.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", name='" + getName() + "'" +
            ", owner='" + getOwner() + "'" +
            ", ownerEmail='" + getOwnerEmail() + "'" +
            "}";
    }
}
