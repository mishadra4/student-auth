package com.md.sa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_pass_in_orders")
public class StudentPassInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    public Integer getId() {
        return id;
    }

    public StudentPassInOrder setId(Integer id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public StudentPassInOrder setUser(User user) {
        this.user = user;
        return this;
    }

    public Lab getLab() {
        return lab;
    }

    public StudentPassInOrder setLab(Lab lab) {
        this.lab = lab;
        return this;
    }
}
