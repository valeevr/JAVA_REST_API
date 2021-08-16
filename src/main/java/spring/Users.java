package spring;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private List<User> users;

    public List<User> getUsersList() {
        if(users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsersList(List<User> users) {
        this.users = users;
    }
}
