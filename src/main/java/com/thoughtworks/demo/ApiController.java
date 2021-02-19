package com.thoughtworks.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ApiController {

    private final List<User> users = new ArrayList<>() ;
    private final List<List<Education>> educations = new ArrayList<>();

    public ApiController() {
        users.add(new User(1, "KAMIL", 24,
                "https://inews.gtimg.com/newsapp_match/0/3581582328/0",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut fuga aliquam ad asperiores voluptatem dolorum! Quasi."));

        ArrayList<Education> education1 = new ArrayList<Education>(){{
            add(new Education(1, "1990", "I was born in Katowice", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente, exercitationem, totam, dolores iste dolore est aut modi."));
            add(new Education(1, "2005", "Secondary school specializing in artistic", "Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus."));
            add(new Education(1, "2009", "First level graduation in Graphic Design", "Aspernatur, mollitia, quos maxime eius suscipit sed beatae ducimus quaerat quibusdam perferendis? Iusto, quibusdam asperiores unde repellat."));
        }};
        educations.add(education1);
    }

    @GetMapping("/users/{id:[0-9]+}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable int id) {
        if(id <= 0 || id > users.size()) {
            throw new UserNotExistException();
        }
        return users.get(id-1);
    }

    @GetMapping("/users/{id:[0-9]+}/educations")
    @ResponseStatus(HttpStatus.OK)
    public List<Education> getUserEducation(@PathVariable int id) {
        if(id <= 0 || id > users.size()) {
            throw new UserNotExistException();
        }
        return educations.get(id-1);
    }

    public String ExceptionHandler(UserNotExistException exception) {
        return "id not exists.";
    }

}

