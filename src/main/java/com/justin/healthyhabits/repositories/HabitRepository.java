package com.justin.healthyhabits.repositories;

import com.justin.healthyhabits.user.Habit;
import org.springframework.data.repository.CrudRepository;

public interface HabitRepository extends CrudRepository<Habit, Integer> {
}
