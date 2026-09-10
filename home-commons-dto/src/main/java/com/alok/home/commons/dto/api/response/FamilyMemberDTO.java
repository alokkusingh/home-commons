package com.alok.home.commons.dto.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamilyMemberDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
