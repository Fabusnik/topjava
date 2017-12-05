package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres", "jdbc"})
public class MealServiceJdbcTest extends AbstractServiceTest{

    @Test
    public void testDelete() throws Exception {
        super.testDelete();
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        super.testDeleteNotFound();
    }

    @Test
    public void testSave() throws Exception {
        super.testSave();
    }

    @Test
    public void testGet() throws Exception {
        super.testGet();
    }

    @Test
    public void testGetNotFound() throws Exception {
        super.testGetNotFound();
    }

    @Test
    public void testUpdate() throws Exception {
        super.testUpdate();
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        super.testUpdateNotFound();
    }

    @Test
    public void testGetAll() throws Exception {
        super.testGetAll();
    }

    @Test
    public void testGetBetween() throws Exception {
        super.testGetBetween();
    }
}
