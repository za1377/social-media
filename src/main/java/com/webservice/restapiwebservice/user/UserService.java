package com.webservice.restapiwebservice.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserService {

    private static List<User> users = new ArrayList<>();
    private static int countUser = 0;

    static {
        users.add(new User(++countUser,"zahra", LocalDate.now().minusYears(30)));
        users.add(new User(++countUser,"zahra", LocalDate.now().minusYears(25)));
        users.add(new User(++countUser,"zahra", LocalDate.now().minusYears(40)));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++countUser);
        users.add(user);
        return user;
    }

    public User findById(int id) {
        Predicate<User> predicate = user -> user.getId() == id;
        User user = users.stream().filter(predicate).findFirst().orElse(null);
        return user;
    }
}
