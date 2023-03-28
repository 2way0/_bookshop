package com.study.springboot.controller;

import com.study.springboot.controller.form.LoginForm;
import com.study.springboot.controller.form.MemberForm;
import com.study.springboot.domain.Address;
import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        Long session_id = (Long) session.getAttribute("member_id");
        log.info("홈session==============" + session_id);
        model.addAttribute("session_id", session_id);
        return "home";
    }

    /**
     * 회원 가입
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());  // 빈 화면이니까 아무것도 없을 수도 있는데 Validation 같은 걸 해줘서.
        return "members/joinForm";
    }
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {   // @Valid -> MemberForm에 있는 @NotEmpty 어노테이션 인식( 밸리데이션 ). ,/ BindingResult -> @Valid 뒤에 있으면 튕기지 않고 오류가 담겨서 코드가 실행됨.

        if (result.hasErrors()) {
            return "members/joinForm";  // Form데이터도 가져간다.
        }
        Address address = new Address(form.getCity(), form.getDetailed_addr(), form.getZipcode());
        Member member = new Member(form.getMember_email(), form.getMember_pw(), form.getMember_name(),address);
        log.info(member.getAddress().toString());

        memberService.join(member);
        return "redirect:/";
    }


    /**
     * 로그인
     */
    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());  // 빈 화면이니까 아무것도 없을 수도 있는데 Validation 같은 걸 해줘서.
        return "members/loginPopup";
    }

    @PostMapping("/members/login")
    @ResponseBody
    public String login(LoginForm loginForm, Model model, HttpSession session) {
        log.info(loginForm.toString());
        Long memberId = memberService.login(loginForm);
        String message;
        String mem = String.valueOf(memberId);
        log.info(mem);
        if (memberId == 0L) {
            message = "로그인 정보를 확인하세요";
        } else {
            session.setAttribute("member_id", memberId);
            String session_id = String.valueOf(session.getAttribute("member_id"));
            log.info("로그인session==============" + session_id);
            message = "로그인 성공";
        }
        log.info("message===================================="+message);
        return message;
    }







    /*@PostMapping("/members/login")
        public String login(@Valid LoginForm loginForm, Model model, HttpSession session) {
        Long memberId = memberService.login(loginForm);
        if(memberId == 0L){
            return "redirect:/";
        }*/

//    @PostMapping("/members/login")
//    public Map<String, Object> login(@Valid LoginForm loginForm, Model model, HttpSession session) {
//        Long memberId = memberService.login(loginForm);
//        String message;
//        Boolean success;
//        if(memberId == 0L){
//            success = false;
//            message = "로그인 정보를 확인하세요";
//            model.addAttribute("message",message);
//        } else {
//            success = true;
//            session.setAttribute("member_id", memberId);
//            String session_id = String.valueOf(session.getAttribute("member_id"));
//            log.info("로그인session==============" + session_id);
//        }
//        Map<String, Object> response = new HashMap<>();
//        response.put("success", success);
//        return response;
//
///*        if(memberId == 0L){
//            result = "로그인 정보를 확인하세요";
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        } else {
//            session.setAttribute("member_id", memberId);
//            String session_id = String.valueOf(session.getAttribute("member_id"));
//            log.info("로그인session==============" + session_id);
//            result = "성공";
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }*/
//
//    }

    // 이메일 중복확인
    @GetMapping("/members/emailCheck")
    public String duplicateMember(String member_id, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.info("================================에러다");
            return "member/joinform";
        }
        memberService.validationDuplicateMember(member_id);
        model.addAttribute("okMessage", "사용가능한 아이디입니다.");
        log.info("================================에러 아니다");
        return "member/joinform";


    }




    /**
     * 내 정보
     */
    @GetMapping("/members/myInfo")
    public String myInfo(Model model, HttpSession session) {
        Long session_id = (Long) session.getAttribute("member_id");
        log.info("내정보session==============" + session_id);
        Member member = memberService.myInfoData(session_id);
        model.addAttribute("member", member);
        return "members/myInfo";
    }

    /**
     * 로그아웃
     */
    @GetMapping("/members/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("member_id");
        return "redirect:/";
    }


}
