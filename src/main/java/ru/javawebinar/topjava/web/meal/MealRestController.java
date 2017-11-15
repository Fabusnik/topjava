package ru.javawebinar.topjava.web.meal;


import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.List;


@Controller
public class MealRestController extends AbstractMealController {

    @Override
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    public Meal get(Integer userId, int id) {
        return super.get(userId, id);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }

    @Override
    public void update(Meal meal) {
        super.update(meal);
    }
}