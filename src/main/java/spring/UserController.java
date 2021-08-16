package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "")
public class UserController {
    @Autowired
    private UsersDAO usersDAO;

    @PostMapping(path= "/login", produces = "application/json")
    public ResponseEntity<Object> login()
    {
        String response = "You successfully logged in!";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path= "/logout", produces = "application/json")
    public ResponseEntity<Object> logout()
    {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}