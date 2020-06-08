package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.javamentor.model.User;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Value("${central.server.url}")
    private String CENTRAL_SERVER_URL;

    RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> listOfUsers() {
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(CENTRAL_SERVER_URL + "/admin/users/", List.class);
        return (List<User>) responseEntity.getBody();
    }

    @Override
    public boolean addUser(User user) {
        HttpEntity<User> httpEntity = new HttpEntity<>(user);
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(
                CENTRAL_SERVER_URL + "/admin/users", httpEntity, User.class);
        return responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Override
    public void deleteUser(long id) {
        restTemplate.delete(CENTRAL_SERVER_URL + "/admin/users/" + id);
    }

    @Override
    public void updateUser(User user) {
        restTemplate.put(CENTRAL_SERVER_URL + "/admin/users", user);
    }

    @Override
    public User getById(long id) {
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(CENTRAL_SERVER_URL + "/admin/users/" + id, User.class);
        return restTemplate.getForObject(CENTRAL_SERVER_URL + "/admin/users/" + id, User.class);
    }

    @Override
    public User loadUserByUsername(String username) {
        return restTemplate.getForObject(CENTRAL_SERVER_URL + "/admin/users/ByUsername/" + username, User.class);
    }
}
