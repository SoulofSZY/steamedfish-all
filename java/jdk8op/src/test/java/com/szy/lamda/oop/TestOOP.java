package com.szy.lamda.oop;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/8/15
 * @since 1.0.0
 */
public class TestOOP {

    interface User {
        UserType getType();
    }

    enum UserType {
        SlumDog, Real
    }

    class SlumDogVIP implements User {
        UserType type = UserType.SlumDog;

        @Override
        public UserType getType() {
            return type;
        }
    }

    class RealVIP implements User {
        UserType type = UserType.Real;

        @Override
        public UserType getType() {
            return type;
        }

    }


    /**
     * 未遵循 开闭原则的实现
     */
    class VIPCenterOld {

        <T extends User> void serviceVIP(T user) {
            if (user instanceof SlumDogVIP) {
                System.out.println("给点甜头");
            } else if (user instanceof RealVIP) {
                System.out.println("大佬上座");
            }
        }
    }

    static class VIPCenterNew {
        private static Map<UserType, ServiceProvider> providers;

        static {
            providers = new HashMap<>(4);
            providers.put(UserType.SlumDog, new SlumDogVIPServiceProvider());
            providers.put(UserType.Real, new RealVIPServiceProvider());
        }

        void serviceVIP(User user) {
            providers.get(user.getType()).service(user);
        }

    }

    interface ServiceProvider {
        void service(User user);
    }

    static class SlumDogVIPServiceProvider implements ServiceProvider {
        @Override
        public void service(User user) {
            System.out.println("给点甜头");
        }
    }

    static class RealVIPServiceProvider implements ServiceProvider {

        @Override
        public void service(User user) {
            System.out.println("大佬上座");
        }
    }
}