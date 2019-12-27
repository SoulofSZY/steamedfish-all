package com.szy.lamda.lambda;

import com.szy.lamda.lambda.bean.Person;
import com.szy.lamda.lambda.inner.ForMula;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 〈一句话功能简述〉<br>
 * 〈\〉
 *
 * @author sunzhengyu
 * @create 2019/7/10
 * @since 1.0.0
 */
public class TestLambda {

    @Test
    public void testDefaultMethod() {
        ForMula forMula = new ForMula() {
            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };

        System.out.printf("--->calculate:%.2f%n", forMula.calculate(100));
        System.out.printf("--->calculate:%.2f%n", forMula.sqrt(25));
    }

    @Test
    public void testMethodReference() {
        Supplier<Person> supplier = Person::new;
        Person person = supplier.get();
        person.setName("szy");
        person.setAge(18);
        System.out.println(person.toString());
    }

    @Test
    public void testMethodReference2() {
        Consumer<Person> consumer = System.out::println;
        Person person = new Person();
        person.setName("szy");
        person.setAge(18);
        consumer.accept(person);
        consumer = consumer.andThen(Person::printName);
        consumer.accept(person);
    }


}