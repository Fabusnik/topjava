package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.List;

public interface MealService {

    Meal create(Meal meal);

    void delete(int id);

    Meal get(int id);

    void update(Meal meal);

    List<Meal> getAll(Integer userId);

}