package com.example.encurtador_link.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "url", cascade = CascadeType.ALL)
    private List<DailyAccess> dailyAccessList;

    public Url(Long id, String original_url, String shortened_url, Date createdAt, List<DailyAccess> dailyAccessList) {
        this.id = id;
        this.original_url = original_url;
        this.shortened_url = shortened_url;
        this.createdAt = createdAt;
        this.dailyAccessList = dailyAccessList;
    }

    public Url(String original_url) {
        this.original_url = original_url;
    }

    public Url() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<DailyAccess> getDailyAccessList() {
        return dailyAccessList;
    }

    public void addDailyAcess(DailyAccess dailyAccess) {
        this.dailyAccessList.add(dailyAccess);
    }
}
