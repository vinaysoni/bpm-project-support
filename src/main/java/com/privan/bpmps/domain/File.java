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
 * A File.
 */
@Entity
@Table(name = "file")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "path")
    private String path;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "last_updated")
    private Instant lastUpdated;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @ManyToOne
    @JsonIgnoreProperties("files")
    private Stage stage;

    @ManyToOne
    @JsonIgnoreProperties("files")
    private Worker worker;

    @OneToMany(mappedBy = "file")
    private Set<Delegation> delegations = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public File fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public File path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public File creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public File lastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreateBy() {
        return createBy;
    }

    public File createBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public File modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Stage getStage() {
        return stage;
    }

    public File stage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Worker getWorker() {
        return worker;
    }

    public File worker(Worker worker) {
        this.worker = worker;
        return this;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Set<Delegation> getDelegations() {
        return delegations;
    }

    public File delegations(Set<Delegation> delegations) {
        this.delegations = delegations;
        return this;
    }

    public File addDelegation(Delegation delegation) {
        this.delegations.add(delegation);
        delegation.setFile(this);
        return this;
    }

    public File removeDelegation(Delegation delegation) {
        this.delegations.remove(delegation);
        delegation.setFile(null);
        return this;
    }

    public void setDelegations(Set<Delegation> delegations) {
        this.delegations = delegations;
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
        File file = (File) o;
        if (file.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), file.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "File{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", path='" + getPath() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", lastUpdated='" + getLastUpdated() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            "}";
    }
}
