package com.work.bench;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
class WorkBenchJavaApplicationTests {

    @Test
    void contextLoads() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate now = LocalDate.now();
        System.out.println(now.plusYears(1).withMonth(1).withDayOfMonth(1));
    }

}
