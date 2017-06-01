package ru.gothmog.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gothmog.db.entity.Worker;

/**
 * @author d.grushetskiy
 */
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
