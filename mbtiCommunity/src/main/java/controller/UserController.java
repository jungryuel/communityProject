package controller;

import dto.JoinMember;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/join")
    public ResponseEntity<String> join(JoinMember joinMember){
        User user = new User();
        user.setEmail(joinMember.getEmail());
        user.setPassword(joinMember.getPassword());
        user.setNickname(joinMember.getNickname());

        userService.join(user);
        return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
    }
    @PostMapping("/join/emailDuplicatedCheck")
    public ResponseEntity<String> emailDuplicated(String email){
        if(userService.getUserEntity(email) == null){
            return new ResponseEntity<>("가입 가능한 이메일입니다", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("이미 가입된 이메일입니다", HttpStatus.OK);
        }
    }

}
