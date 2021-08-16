package spring;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsersDAO {

    private static Users list = new Users();

    static
    {
        list.getUsersList().add(new User("user", "password"));
        list.getUsersList().add(new User("admin", "qwerty"));
        list.getUsersList().add(new User("dog", "123"));
    }

    public Users getAllUsers()
    {
        return list;
    }

    public User findByLogin(String login) {
        Optional<User> user = list.getUsersList().stream().filter(login::equals).findFirst();
        return user.orElse(null);
    }
}
