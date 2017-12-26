package ru.javawebinar.topjava.web.meal;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MealTestData.contentJson(MEAL1));
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get(REST_URL+"/all"))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6));
    }

    @Test
    public void getBetween() throws Exception {

        mockMvc.perform(get(REST_URL+"/filter?startDate=2015-05-31T12:00:59&endDate=2015-05-31T21:00:00"))
                .andDo(print())
                .andExpect(contentJson(MEAL5, MEAL6));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(REST_URL+"/"+MEAL1.getId().toString()))
                .andDo(print());
        assertMatch(mealService.getAll(AuthorizedUser.id()), MEAL6,MEAL5,MEAL4,MEAL3,MEAL2);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("New");
        updated.setCalories(300);

        mockMvc.perform(put(REST_URL+"/"+MEAL1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        assertMatch(mealService.get(MEAL1.getId(), AuthorizedUser.id()), updated);
    }

    @Test
    public void createWithLocation() throws Exception {

        Meal expected = new Meal(null, of(2015, Month.MAY, 29, 10, 0), "NewMeal", 1000);

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Meal returned = TestUtil.readFromJson(action, Meal.class);

        expected.setId(returned.getId());

        assertMatch(returned,expected);
        assertMatch(mealService.getAll(USER.getId()), MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1,expected);

    }

}