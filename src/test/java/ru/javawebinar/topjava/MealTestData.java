package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealTestData {
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(100002, LocalDateTime.of(2017, Month.NOVEMBER, 15, 10, 0), "Завтрак", 500),
            new Meal(100003, LocalDateTime.of(2017, Month.NOVEMBER, 15, 13, 0), "Обед", 1000),
            new Meal(100004, LocalDateTime.of(2017, Month.NOVEMBER, 15, 20, 0), "Ужин", 100),
            new Meal(100005, LocalDateTime.of(2017, Month.NOVEMBER, 16, 10, 0), "Завтрак", 400),
            new Meal(100006, LocalDateTime.of(2017, Month.NOVEMBER, 16, 13, 0), "Обед", 1300),
            new Meal(100007, LocalDateTime.of(2017, Month.NOVEMBER, 16, 20, 0), "Ужин", 400),
            new Meal(100008, LocalDateTime.of(2017, Month.NOVEMBER, 15, 10, 0), "АдминЗавтрак", 500),
            new Meal(100009, LocalDateTime.of(2017, Month.NOVEMBER, 15, 13, 0), "АдминОбед", 2500)
    );
}
