package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

/**
 * Created by r22fab21051979 on 13.12.2017.
 */

@Controller
public class MealController {

    @Autowired
    private MealService service;

    @GetMapping("/meals")
    public String mealList(Model model){
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @RequestMapping(value = "/filtered", method = RequestMethod.POST)
    public String filteredMealList(HttpServletRequest request, Model model)throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

        int userId = AuthorizedUser.id();

        List<Meal> mealsDateFiltered = service.getBetweenDates(
                startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId);



        model.addAttribute("meals", MealsUtil.getFilteredWithExceeded(mealsDateFiltered,
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @RequestMapping(value = "/meals/create", method = RequestMethod.GET)
    public String createMeal(Model model){
        Meal meal = new Meal();
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("meals/add")
    public String addMeal(HttpServletRequest request) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (!request.getParameter("id").isEmpty()) {
            meal.setId(Integer.parseInt(request.getParameter("id")));
        }

        if (meal.getId() == null){
            this.service.create(meal, AuthorizedUser.id());
        } else {
            this.service.update(meal, AuthorizedUser.id());
        }
        return "redirect:/meals";
    }

    @RequestMapping("/remove/{id}")
    public String removeMeal(@PathVariable("id") int id){
        this.service.delete(id, AuthorizedUser.id());
        return "redirect:/meals";
    }

    @RequestMapping("edit/{id}")
    public String editMeal(@PathVariable("id") int id, Model model){
        model.addAttribute("meal", this.service.get(id, AuthorizedUser.id()));
//        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "mealForm";
    }

}
