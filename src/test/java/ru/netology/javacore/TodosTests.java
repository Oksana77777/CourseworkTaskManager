package ru.netology.javacore;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodosTests {

    @BeforeAll
    public static void initSet() {
        System.out.println("Тесты для методов класса \"Todos\"");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Начинаем новый тест");
    }

    @AfterEach
    public void finalTest() {
        System.out.println("Тест завершен!");
    }

    @AfterAll
    public static void completeSet() {
        System.out.println("Все тесты завершены! ");
    }


    @Test
    @DisplayName("Тест \"addTask()\" метод")
    public void addTaskTest(TestInfo addTaskTestInfo) {

        Todos todo = new Todos();
        todo.addTask("TASK");
        boolean actual = todo.getTasks().contains("TASK");

        Assertions.assertTrue(actual, addTaskTestInfo.getDisplayName() + " Не сделано!");
        System.out.println(addTaskTestInfo.getDisplayName() + " сделано!");
    }

    @Test
    @DisplayName("Тест \"removeTask()\" метод")
    public void removeTaskTest(TestInfo removeTaskTestInfo) {

        Todos todo = new Todos();
        todo.addTask("ЗАДАЧА");
        todo.removeTask("ЗАДАЧА");
        boolean actual = todo.getTasks().contains("ЗАДАЧА");

        Assertions.assertFalse(actual, removeTaskTestInfo.getDisplayName() + " Не сделано!");
        System.out.println(removeTaskTestInfo.getDisplayName() + " сделано!");
    }


    @Test
    @DisplayName("Тест \"getAllTask()\" метод")
    public void getAllTasksTest(TestInfo getAllTasksTestInfo) {

        Todos todo = new Todos();
        String expected = "First task" +
                " " +
                "Second task" +
                " " +
                "Third task" +
                " ";
        todo.addTask("First task");
        todo.addTask("Second task");
        todo.addTask("Third task");

        Assertions.assertEquals(expected, todo.getTasks(), getAllTasksTestInfo.getDisplayName() + " Не сделано!");
        System.out.println(getAllTasksTestInfo.getDisplayName() + " сделано!");
    }

    @Test
    @DisplayName("Тест \"getAllTasksAsList()\" метод")
    public void getAllTasksAsListTest(TestInfo getAllTasksAsListTestInfo) {

        Todos todo = new Todos();
        todo.addTask("First task");
        todo.addTask("Second task");
        todo.addTask("Third task");
        List<String> expected = new ArrayList<>(Arrays.asList("First task", "Second task", "Third task"));
        List<String> actual = todo.getAllTasksAsList();

        Assertions.assertEquals(expected, actual, getAllTasksAsListTestInfo.getDisplayName() + " Не сделано!");
        System.out.println(getAllTasksAsListTestInfo.getDisplayName() + " сделано!");
    }
}
