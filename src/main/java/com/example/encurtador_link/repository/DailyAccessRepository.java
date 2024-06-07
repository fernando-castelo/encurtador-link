package com.example.encurtador_link.repository;

import com.example.encurtador_link.entity.DailyAccess;
import com.example.encurtador_link.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyAccessRepository extends JpaRepository<DailyAccess, Long> {

    Optional<DailyAccess> findByDate(LocalDate date);

    Optional<DailyAccess> findByDateAndUrl(LocalDate date, Url url);

    Optional<DailyAccess> findByUrl(Url url);

    List<DailyAccess> findAllByUrl(Url url);

}
