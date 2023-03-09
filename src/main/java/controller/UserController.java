package controller;

import dto.JoinDto;
import dto.LoginDto;
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

    @PostMapping("/member/join")
    public ResponseEntity<String> join(JoinDto joinDto) {
        User user = new User();
        user.setEmail(joinDto.getEmail());
        user.setPassword(joinDto.getPassword());
        user.setNickname(joinDto.getNickname());

        userService.joinUser(user);
        return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
    }

    @PostMapping("/join/emailDuplicateCheck")
    public ResponseEntity<String> emailDuplicate(String email) {
        if (userService.getUserEntity(email) == null) {
            return new ResponseEntity<>("가입 가능한 이메일입니다", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("중복된 이메일입니다", HttpStatus.OK);
        }
    }

    @PostMapping("/join/nicknameDuplicateCheck")
    public ResponseEntity<String> nicknameDuplicate(String nickname) {
        if (userService.isNicknameAvailable(nickname)) {
            return new ResponseEntity<>("가입 가능한 닉네임입니다", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("중복 닉네임입니다", HttpStatus.OK);
        }
    }

    @PostMapping("login")
    public ResponseEntity<String> login(LoginDto loginDto) {
        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword(loginDto.getPassword());
        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }
}



