package com.justin.healthyhabits.services;

import com.justin.healthyhabits.user.HabitRepository;
import com.justin.healthyhabits.user.Habits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    @Autowired
    HabitRepository habitRepository;

    public List<Habits> getAllHabits() {
        List<Habits> allHabits = new ArrayList<>();
        habitRepository.findAll().forEach(allHabits::add);
        return allHabits;
    }

    public void addHabit(Habits habit) {
        habitRepository.save(habit);
    }

    public Optional<Habits> getHabit(int id) {
        return habitRepository.findById(id);
    }

    public void saveHabit(Habits habit) {
        habitRepository.save(habit);
    }

    public void deleteHabit(Habits habit) {
        habitRepository.delete(habit);
    }
}
