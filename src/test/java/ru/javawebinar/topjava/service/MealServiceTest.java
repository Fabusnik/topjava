package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(100002, 100000);
        Assert.assertEquals(MealTestData.MEALS.get(0), meal);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(100002,100000);
        service.get(100002,100000);
    }

    @Test
    public void getBetweenDates() throws Exception {

    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        List<Meal> meal = service.getAll(100001);
        Assert.assertEquals(2, meal.size());

    }

    @Test
    public void update() throws Exception {
        Meal meal = MealTestData.MEALS.get(1);
        meal.setDescription("новый Обед");
        service.update(meal,100000);
        Assert.assertEquals(meal, service.get(100003,100000));
    }

    @Test
    public void create() throws Exception {

    }

}