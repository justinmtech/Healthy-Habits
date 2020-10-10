package com.justin.healthyhabits.repositories;

import com.justin.healthyhabits.user.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Integer> {
}
