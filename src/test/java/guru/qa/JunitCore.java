package guru.qa;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JunitCore {

    public static void main(String[] args) throws Exception {

        // найти классы с аннотацией @Test
        // здесь мы будем находить только один класс SimpleTest

        // здесь мы получим объект clazz типа Class, который содержит информацию об этом классе
        // (методы, поля, конструкторы и аннотации) - это метаинформация

        // запустить все методы с аннотацией @Test
        Class clazz = SimpleTest.class;
        for (Method method : clazz.getDeclaredMethods()) {
            Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotation != null) {
                // запустить метод с аннотацией @Test
                try {
                    method.invoke(clazz.getConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("Test failed " + method.getName());
                        continue;
                    } else {
                        System.out.println("Test broken " + method.getName());
                        continue;
                    }
                }
                // вывести результат
                System.out.println("Test passed " + method.getName());
            }
        }
    }
}
