package maco.habit_backend.controllers;

import lombok.AllArgsConstructor;
import maco.habit_backend.dtos.HabitDTO;
import maco.habit_backend.dtos.UserDTO;
import maco.habit_backend.dtos.UserHabitDTO;
import maco.habit_backend.entities.Habit;
import maco.habit_backend.entities.User;
import maco.habit_backend.mapper.HabitMapper;
import maco.habit_backend.mapper.UserMapper;
import maco.habit_backend.models.UserHabit;
import maco.habit_backend.services.HabitService;
import maco.habit_backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private UserMapper userMapper;

    private final UserService userService;
    private final HabitService habitService;

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }



    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        User user = userMapper.mapFrom(userDTO);
        User savedUser = userService.save(user);
        return userMapper.mapTo(savedUser);
    }

    @GetMapping("/all")
    public List<UserDTO> getAll(){
        return userService
                .getAll()
                .stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}/habits")
    public List<UserHabit> getHabitsByUserId(@PathVariable Long userId){
        return userService
                .findHabitsByUserId(userId)
                .stream()
                .map(
                        userHabitDTO -> new UserHabit(
                                userHabitDTO.getUsername(),
                                userHabitDTO.getUserId(),
                                userHabitDTO.getHabitId(),
                                userHabitDTO.getHabitName(),
                                userHabitDTO.isHabitCompleted(),
                                userHabitDTO.getCurrentStreak()
                        )
                )
                .collect(Collectors.toList());
    }
}
