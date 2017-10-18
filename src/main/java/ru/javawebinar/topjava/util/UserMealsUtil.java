package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 13, 0), "Обед", 520),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(14, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        // Map для подсчета каллорий за день
        Map<LocalDate, Integer> caloriesCount = new HashMap<>();

        // Выходной лист
        List<UserMealWithExceed> exceedList = new ArrayList<>();

        //Считаем каллории
        for (UserMeal iter : mealList) {
            if (caloriesCount.containsKey(iter.getDateTime().toLocalDate())) {
                caloriesCount.put(iter.getDateTime().toLocalDate(), caloriesCount.get(iter.getDateTime().toLocalDate()) + iter.getCalories());
            } else
                caloriesCount.put(iter.getDateTime().toLocalDate(), iter.getCalories());
        }

        //Пишем в выходной лист все что входит во временной промежуток, и устанавливаем флаг превышения калорий за день
        for (UserMeal iter : mealList) {
            boolean exceed;
            exceed = caloriesCount.get(iter.getDateTime().toLocalDate()) > caloriesPerDay ? true : false;
            if (TimeUtil.isBetween(iter.getDateTime().toLocalTime(), startTime, endTime))
                exceedList.add(new UserMealWithExceed(iter.getDateTime(), iter.getDescription(), iter.getCalories(), exceed));
        }

        return exceedList;
    }
}
