package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class MealServiceImpl implements MealService {
    private static final Logger log = LoggerFactory.getLogger(MealServiceImpl.class);

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Meal get(Integer userId, int id) {
        return repository.get(userId, id);
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);
    }

    @Override
    public List<MealWithExceed> getAll(Integer userId) {
        List<Meal> mealList = repository.getAll().stream()
                .filter(iter -> iter.getUserId().equals(userId))
                .collect(Collectors.toList());
        List<MealWithExceed> mealWithExceeds = MealsUtil.getWithExceeded(mealList, AuthorizedUser.getCaloriesPerDay());

        Collections.sort(mealWithExceeds, (o1, o2) ->
                o1.getDateTime().compareTo(o1.getDateTime()));

        for (MealWithExceed iter : mealWithExceeds)
            log.info("getAll {}", iter);
        return mealWithExceeds;

    }
}