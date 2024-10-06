package com.riwi.project.application.controller.impl;

import com.riwi.project.application.controller.interfaces.IModelUser;
import com.riwi.project.application.dto.response.TaskCreateDTO;
import com.riwi.project.application.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements IModelUser {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/getAllTasksByUser")
    public ResponseEntity<List<TaskCreateDTO>> getAllTasksByUser() {
        List<TaskCreateDTO> tasks = userService.getAllTasksByUser();
        return ResponseEntity.ok(tasks);    }
}
