package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);

            adminUserController.create(new User(null, "Коля", "alex", "password", Role.ROLE_ADMIN));
            adminUserController.create(new User(null, "Петя", "petya", "password1", Role.ROLE_USER));
            adminUserController.create(new User(null, "Аня", "anna", "password1", Role.ROLE_USER));
            adminUserController.create(new User(null, "Вася", "vasya", "password1", Role.ROLE_USER));
            adminUserController.getAll();

            adminUserController.getByMail("alex");
            mealRestController.get(1);
            mealRestController.getAll();

        }
    }
}
