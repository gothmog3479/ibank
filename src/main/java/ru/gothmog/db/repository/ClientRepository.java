package ru.gothmog.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gothmog.db.entity.Client;

/**
 * @author d.grushetskiy
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
