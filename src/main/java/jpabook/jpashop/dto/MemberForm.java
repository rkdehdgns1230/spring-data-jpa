package jpabook.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수입니다.") // 자체 validation
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
