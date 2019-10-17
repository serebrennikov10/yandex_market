package ru.yandex;

import cucumber.api.java.ru.*;



public class MyStepdefs {

    @Дано("^просто вывод (\\d+)$")
    public void простоВывод(int arg1) {
        System.out.println(arg1);
    }
}