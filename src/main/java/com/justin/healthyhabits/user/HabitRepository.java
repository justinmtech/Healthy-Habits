package com.justin.healthyhabits.user;

import org.springframework.data.repository.CrudRepository;

public interface HabitRepository extends CrudRepository<Habits, String> {
}
