package com.example.encurtador_link.service;

import com.example.encurtador_link.entity.DailyAccess;
import com.example.encurtador_link.entity.Url;
import com.example.encurtador_link.repository.DailyAccessRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DailyAccessService {

    private DailyAccessRepository dailyAccessRepository;

    public DailyAccessService(DailyAccessRepository dailyAccessRepository) {
        this.dailyAccessRepository = dailyAccessRepository;
    }

    public DailyAccess createDailyAcessEntry(DailyAccess dailyAccess, Url url) {
        dailyAccess.incrementAccessCount();
        dailyAccess.setUrl(url);
        return dailyAccessRepository.save(dailyAccess);
    }

    public void handleDailyAccessEntryCreation(Url url) {

        Optional<DailyAccess> dailyAccessEntry = dailyAccessRepository.findByDateAndUrl(LocalDate.now(), url);
        //Incrementa dailyAcessCount em casos de acesso na mesma data
        if(dailyAccessEntry.isPresent()) {
            DailyAccess dailyAccess = dailyAccessEntry.get();
            dailyAccess.incrementAccessCount();
            dailyAccessRepository.save(dailyAccess);
            url.addDailyAcess(dailyAccess);
        }

        DailyAccess dailyAccess = new DailyAccess();
        DailyAccess createdEntry = createDailyAcessEntry(dailyAccess, url);
        url.addDailyAcess(createdEntry);
    }
}
