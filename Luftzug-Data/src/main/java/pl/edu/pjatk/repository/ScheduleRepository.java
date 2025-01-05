package pl.edu.pjatk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
