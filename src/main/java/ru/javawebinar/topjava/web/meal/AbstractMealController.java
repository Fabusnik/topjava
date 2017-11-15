package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.List;

abstract public class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll(){
        log.info("getAll");
        return service.getAll(AuthorizedUser.id());
    }

    public Meal get(Integer userId, int id){
        log.info("get {}", id);
        return service.get(userId, id);
    }

    public void delete(int id){
        log.info("delete {}", id);
        service.delete(id);
    }

    public Meal create(Meal meal){
        log.info("create {}", meal);
        return service.create(meal);
    }

    public void update(Meal meal){
        log.info("update {}", meal);
        service.update(meal);
    }
}
