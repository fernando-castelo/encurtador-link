package com.example.encurtador_link.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String original_url;

    private String shortened_url;

    @CreationTimestamp
    private Date createdAt;

    public Url(Long id, String original_url, String shortened_url, Date createdAt) {
        this.id = id;
        this.original_url = original_url;
        this.shortened_url = shortened_url;
        this.createdAt = createdAt;
    }

    public Url() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }

    public String getShortened_url() {
        return shortened_url;
    }

    public void setShortened_url(String shortened_url) {
        this.shortened_url = shortened_url;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
