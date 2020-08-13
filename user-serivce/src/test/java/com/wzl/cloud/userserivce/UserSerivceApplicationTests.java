package com.wzl.cloud.userserivce;

import domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class UserSerivceApplicationTests {

    @Test
    void contextLoads() throws NoSuchFieldException, IllegalAccessException {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // 运行结果： true   2019
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t" + atomicInteger.get());
        // 运行结果： false  2019
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t" + atomicInteger.get());

        // 此方法可以解决多线程环境下i++问题，底层使用的是Unsafe类CAS和自旋锁
        atomicInteger.getAndIncrement();
        Unsafe.getUnsafe();
        String a = new String("ss");




    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnSafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnSafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnSafe.get(null);
        User user = new User();
        Field username = User.class.getDeclaredField("username");
        long filedOffset = unsafe.objectFieldOffset(username);
        String[] strings = new String[]{"1", "2", "3"};
        long i = unsafe.arrayBaseOffset(String[].class);
        long address = unsafe.allocateMemory(8L);
    }

}
