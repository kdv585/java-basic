package kr.kimdavid.api.user.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.kimdavid.api.user.service.UserService;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.user.domain.UserDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public String save(UserDTO user, Model model) {
        Messenger messenger = userService.save(user);
        model.addAttribute("messenger", messenger);
        return "users/save";
    }

    @PostMapping("/all")
    public String saveAll(List<UserDTO> users, Model model) {
        Messenger messenger = userService.saveAll(users);
        model.addAttribute("messenger", messenger);
        return "users/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, UserDTO user, Model model) {
        Messenger messenger = userService.update(user);
        model.addAttribute("messenger", messenger);
        return "users/list";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id, UserDTO user, Model model) {
        Messenger messenger = userService.delete(user);
        model.addAttribute("messenger", messenger);
        return "users/detail";
    }

    @GetMapping("/id/{id}")
    public String findById(@PathVariable String id, UserDTO user, Model model) {
        Messenger messenger = userService.findById(user);
        model.addAttribute("messenger", messenger);
        return "users/list";
    }

    @GetMapping("/all")
    public String findAll(Model model) {
        Messenger messenger = userService.findAll();
        model.addAttribute("messenger", messenger);
        return "users/list";
    }
}
