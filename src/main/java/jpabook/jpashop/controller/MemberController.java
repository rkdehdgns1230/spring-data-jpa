package jpabook.jpashop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.MemberForm;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model){
        // 빈 객체여도 validation이 가능하기 때문에, 전달한다.
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    /**
     *
     * @param form
     * @param result: 검증 결과를 담고 있는 객체
     * @return
     */
    @PostMapping("/new")
    @Operation(summary = "회원 등록", description = "회원을 등록합니다.")
    public String create(@Valid MemberForm form, BindingResult result){
        // Model 객체에 실려 error를 thymeleaf에 전달해 표현해줄 수 있다.
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/"; // redirection to main page
    }

    @GetMapping
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
