package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private MealDao mealDao = MealDao.getInstance();
    private static final Logger log = getLogger(UserServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meal");
        //  String forward="";
        String action = request.getParameter("action");

        if (action == null) {
            List<MealWithExceed> mealsWithExceeded =
                    MealsUtil.getFilteredWithExceeded(MealDao.meals, 2000);
            request.setAttribute("list", mealsWithExceeded);
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealDao.deleteMeal(mealId);
            List<MealWithExceed> mealsWithExceeded =
                    MealsUtil.getFilteredWithExceeded(MealDao.meals, 2000);

            request.setAttribute("list", mealsWithExceeded);
        } else if (action.equalsIgnoreCase("edit")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            for (Meal iter : MealDao.meals){
                if (iter.getId() == mealId) {
                    iter.setCalories(1);
                    mealDao.updateMeal(iter);
                    log.debug("Edit meal. detail: "+iter);
                    break;
                }
            }

            List<MealWithExceed> mealsWithExceeded =
                    MealsUtil.getFilteredWithExceeded(MealDao.meals, 2000);
            request.setAttribute("list", mealsWithExceeded);
        }

        //response.sendRedirect("meals");
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal();
        meal.setDateTime(LocalDateTime.parse(request.getParameter("data")));
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));

        MealDao.meals.add(meal);

        List<MealWithExceed> mealsWithExceeded =
                MealsUtil.getFilteredWithExceeded(MealDao.meals, 2000);

        request.setAttribute("list", mealsWithExceeded);
        request.getRequestDispatcher("meals.jsp").forward(request,response);
    }
}
