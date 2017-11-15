package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface MealService {

    Meal create(Meal meal);

    void delete(int id) throws NotFoundException;

    Meal get(Integer userId, int id) throws NotFoundException;

    void update(Meal meal) throws NotFoundException;

    List<MealWithExceed> getAll(Integer userId);

}