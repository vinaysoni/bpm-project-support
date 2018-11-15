package com.privan.bpmps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.privan.bpmps.domain.enumeration.Status;

/**
 * A Delegation.
 */
@Entity
@Table(name = "delegation")
public class Delegation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("delegations")
    private File file;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public Delegation note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Status getStatus() {
        return status;
    }

    public Delegation status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public File getFile() {
        return file;
    }

    public Delegation file(File file) {
        this.file = file;
        return this;
    }

    public void setFile(File file) {
        this.file = file;
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
        Delegation delegation = (Delegation) o;
        if (delegation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), delegation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Delegation{" +
            "id=" + getId() +
            ", note='" + getNote() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
