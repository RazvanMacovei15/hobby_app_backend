package maco.habit_backend.services;

import maco.habit_backend.entities.Habit;

import java.util.List;

public interface HabitService {
    Habit save(Habit habit);
    List<Habit> getAll();
    Habit getById(int id);
    void deleteById(int id);
    Habit updateHabitStatus(int habitId);

}
