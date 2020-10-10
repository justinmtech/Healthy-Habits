package com.justin.healthyhabits.services;

import com.justin.healthyhabits.repositories.HabitRepository;
import com.justin.healthyhabits.user.Habit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    @Autowired
    HabitRepository habitRepository;

    public List<Habit> getAllHabits() {
        List<Habit> allHabits = new ArrayList<>();
        habitRepository.findAll().forEach(allHabits::add);
        return allHabits;
    }

    public void addHabit(Habit habit) {
        habitRepository.save(habit);
    }

    public Optional<Habit> getHabit(int id) {
        return habitRepository.findById(id);
    }

    public void saveHabit(Habit habit) {
        habitRepository.save(habit);
    }

    public void deleteHabit(Habit habit) {
        habitRepository.delete(habit);
    }
}
