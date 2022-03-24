package guru.qa;

public class JunitCore {

    public static void main(String[] args) {

        // найти классы с аннотацией @Test
        // здесь мы будем находить только один класс SimpleTest

        // здесь мы получим объект clazz типа Class, который содержит информацию об этом классе
        // (методы, поля, конструкторы и аннотации) - это метаинформация

        Class clazz = SimpleTest.class;

        // запустить все методы с аннотацией @Test

        // вывести результат


    }
}
