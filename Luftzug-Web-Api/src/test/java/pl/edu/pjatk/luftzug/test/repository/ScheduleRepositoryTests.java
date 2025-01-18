package pl.edu.pjatk.luftzug.test.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.edu.pjatk.luftzug.model.Schedule;
import pl.edu.pjatk.luftzug.repository.ScheduleRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ScheduleRepositoryTests {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveScheduleTest() {

        Schedule schedule = new Schedule();
        schedule.setFlightNumber("2137");

        // action
        scheduleRepository.save(schedule);

        // verify
        assertThat(schedule.getId()).isEqualTo(1L);
    }

    @Test
    @Order(2)
    public void getScheduleTest() {

        // action
        Schedule schedule = scheduleRepository.findById(1L).get();

        // verify
        assertThat(schedule.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getScheduleListTest() {

        // action
        List<Schedule> schedules = scheduleRepository.findAll();

        // verify
        assertThat(schedules.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateScheduleTest() {

        // action
        Schedule schedule = scheduleRepository.findById(1L).get();
        schedule.setFlightNumber("2138");
        Schedule updatedSchedule = scheduleRepository.save(schedule);

        // verify
        assertThat(schedule.getFlightNumber()).isEqualTo("2138");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteScheduleByIdTest(){

        // action
        scheduleRepository.deleteById(1L);
        Optional<Schedule> schedule = scheduleRepository.findById(1L);

        // verify
        assertThat(schedule).isEmpty();
    }
}