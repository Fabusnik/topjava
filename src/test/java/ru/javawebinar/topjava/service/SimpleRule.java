package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Date;

public class SimpleRule implements TestRule{
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("****************Before*****************");
                long startTime = new Date().getTime();
                base.evaluate();
                System.out.println("****************After******************");
                long endTime = new Date().getTime()-startTime;
                System.out.println(endTime + " ms");
            }
        };
    }
}
