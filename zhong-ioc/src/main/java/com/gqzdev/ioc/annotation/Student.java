package com.gqzdev.ioc.annotation;

import org.springframework.stereotype.Service;

/**
 *
 */
@Service("student01")
public class Student implements HomeWork {
    /**
     * 写家庭作业
     */
    @Override
    public void doHomeWork() {
        System.out.println("我是学生，我要写家庭作业");
    }
}
