package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.javamentor.model.Role;
import ru.javamentor.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Value("${central.server.url}")
    private String CENTRAL_SERVER_URL;

    RestTemplate restTemplate;

    @Autowired
    public RoleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Role> listOfRoles() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(CENTRAL_SERVER_URL + "/roles", List.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Role> getRolesByName(List<String> ids) {
        HttpEntity<List<String>> httpEntity = new HttpEntity<>(ids);
        ResponseEntity<List> responseEntity = restTemplate.postForEntity(CENTRAL_SERVER_URL + "/roles/RolesByName", httpEntity, List.class);
        return responseEntity.getBody();
    }


    @Override
    public List<String> getRolesNames(List<Role> roles){
        HttpEntity<List<Role>> httpEntity = new HttpEntity<>(roles);
        ResponseEntity<List> responseEntity = restTemplate.postForEntity(CENTRAL_SERVER_URL + "/roles/rolesNames", httpEntity, List.class);
       return responseEntity.getBody();
    }
}
