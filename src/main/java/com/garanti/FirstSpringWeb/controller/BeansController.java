package com.garanti.FirstSpringWeb.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class BeansController
{
    public ApplicationContext applicationContext;

    public BeansController(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    @GetMapping(path = "beans")
    public void setApplicationContext()
    {
        // localhost:9090/FirstSpringWeb/beans
        String[] names = applicationContext.getBeanDefinitionNames();
        Arrays.sort(names);
        System.err.println("----------------------------------------------------");
        System.err.println("---- " + names.length + " ----");
        for (String name : names)
        {
            System.err.println(name + " ---> " + applicationContext.getBean(name).getClass().getName());
        }
        System.err.println("----------------------------------------------------");
    }
}
