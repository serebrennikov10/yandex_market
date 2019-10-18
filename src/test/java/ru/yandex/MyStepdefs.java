package ru.yandex;

import cucumber.api.PendingException;
import cucumber.api.java.ru.*;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class MyStepdefs {



    @Дано("^просто (.*)$")
    public void просто(String arg1) throws Throwable {
        out(arg1);
    }


    @Когда("^делаю вывод (.*)$")
    public void делаюВывод(String b) throws Throwable {
        out(b);
    }

    @Тогда("^вывожу все (.*)$")
    public void вывожуВсе(String a) throws Throwable {
        out(a);
    }

    public void out(String a){
        System.out.println(a);
    }

    @Пусть("^перехожу на страницу (.*)$")
    public void перехожуНаСтраницу(String url) throws IOException {
    }




}