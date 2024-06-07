package com.example.encurtador_link.dailyAccess;

import com.example.encurtador_link.controller.StatisticsDto;
import com.example.encurtador_link.entity.DailyAccess;
import com.example.encurtador_link.entity.Url;
import com.example.encurtador_link.repository.DailyAccessRepository;
import com.example.encurtador_link.service.DailyAccessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DailyAccessServiceTest {

    @Mock
    private DailyAccessRepository dailyAccessRepository;

    @InjectMocks
    private DailyAccessService dailyAccessService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetStatisticsDto() {
        List<DailyAccess> dailyAccessList = new ArrayList<>();
        dailyAccessList.add(new DailyAccess(1L, LocalDate.now(), 8, new Url()));
        dailyAccessList.add(new DailyAccess(2L, LocalDate.now(), 10, new Url()));
        dailyAccessList.add(new DailyAccess(3L, LocalDate.now().minusDays(1), 3, new Url()));

        when(dailyAccessRepository.findAllByUrl(any())).thenReturn(dailyAccessList);

        StatisticsDto statisticsDto = dailyAccessService.getStatisticsDto(new Url());

        assertEquals(21, statisticsDto.getAccessTotalCount());
        assertEquals(7, statisticsDto.getAcessDailyAverage());
    }

}
