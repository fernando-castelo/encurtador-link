package com.example.encurtador_link.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class DailyAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    private int accessCount;

    @ManyToOne
    @JoinColumn(name = "url_id")
    private Url url;


    public DailyAccess() {
        this.date = LocalDate.now().plusDays(0);
        this.accessCount = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    public void incrementAccessCount() {
        this.accessCount++;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}
