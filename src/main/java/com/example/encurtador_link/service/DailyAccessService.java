package com.example.encurtador_link.service;

import com.example.encurtador_link.controller.StatisticsDto;
import com.example.encurtador_link.entity.DailyAccess;
import com.example.encurtador_link.entity.Url;
import com.example.encurtador_link.repository.DailyAccessRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
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

        Optional<DailyAccess> dailyAccessEntry = dailyAccessRepository.findByDateAndUrl(LocalDate.now().plusDays(0), url);
        //Incrementa dailyAcessCount em casos de acesso na mesma data
        if(dailyAccessEntry.isPresent()) {
            DailyAccess dailyAccess = dailyAccessEntry.get();
            dailyAccess.incrementAccessCount();
            dailyAccessRepository.save(dailyAccess);
            url.addDailyAcess(dailyAccess);
        } else {
            DailyAccess dailyAccess = new DailyAccess();
            DailyAccess createdEntry = createDailyAcessEntry(dailyAccess, url);
            url.addDailyAcess(createdEntry);
        }
    }

    public StatisticsDto getStatisticsDto(Url url) {
        List<DailyAccess> dailyAccessList = getDailyAccessListByUrl(url);
        int totalAccessCount = getTotalAccessCount(dailyAccessList);
        double dailyAccessAverage = getDailyAccessAverage(dailyAccessList, totalAccessCount);

        return new StatisticsDto(totalAccessCount, dailyAccessAverage);

    }

    public List<DailyAccess> getDailyAccessListByUrl(Url url) {
        return dailyAccessRepository.findAllByUrl(url);
    }

    private int getTotalAccessCount(List<DailyAccess> dailyAccessList) {
        int totalAccessCount = 0;

        for(DailyAccess dailyAccess : dailyAccessList) {
            totalAccessCount += dailyAccess.getAccessCount();
        }
        return totalAccessCount;
    }

    private double getDailyAccessAverage(List<DailyAccess> dailyAccessList, int totalAccessCount) {

        int numberOfDays = dailyAccessList.size();

        BigDecimal totalAccessCountBigDecimal = BigDecimal.valueOf(totalAccessCount);
        BigDecimal numberOfDaysBigDecimal = BigDecimal.valueOf(numberOfDays);
        BigDecimal averageBigDecimal = totalAccessCountBigDecimal.divide(numberOfDaysBigDecimal, 2, RoundingMode.HALF_UP);

        return averageBigDecimal.doubleValue();

   }
}
