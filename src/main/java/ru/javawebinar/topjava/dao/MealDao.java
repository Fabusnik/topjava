package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDao {

    private static final Logger log = getLogger(MealDao.class);
    private static final MealDao mealDao = null;

    public static List<Meal> meals = new ArrayList<>();

    static {
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 29, 10, 0), "Завтрак", 1200));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 29, 13, 0), "Обед", 550));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 510));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 28, 10, 0), "Завтрак", 900));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 28, 13, 0), "Обед", 450));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 310));
    }


    public void addMeal(Meal meal) {
        meals.add(meal);
        log.debug("Meal added. Detail: " + meal);
    }

    public void deleteMeal(int mealId) {
        int index;
        for (Meal iter : meals) {
            if (iter.getId() == mealId) {
                index = meals.indexOf(iter);
                meals.remove(index);
                log.debug("Meal removed by id: " + mealId);
                return;
            }
        }
    }

    public void updateMeal(Meal meal) {
        int index = meal.getId()-1;

        Meal oldMeal = meals.get(index);
        oldMeal.setDateTime(meal.getDateTime());
        oldMeal.setDescription(meal.getDescription());
        oldMeal.setCalories(meal.getCalories());

        log.debug("Meal is updated. Detail: " + meal);
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public static MealDao getInstance() {
        if (mealDao == null)
            return new MealDao();
        else return mealDao;
    }
}
