package com.tech.kj.service.external.dto;


import lombok.*;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterUserDtoResponse {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String id;
    private boolean isPrimaryEmail;
    private String contact;
    private boolean isPrimaryContact;
    private Set<Roles> roles;

    public boolean getIsPrimaryEmail() {
        return isPrimaryEmail;
    }

    public void setIsPrimaryEmail(boolean isPrimaryEmail) {
        this.isPrimaryEmail = isPrimaryEmail;
    }

    public boolean getIsPrimaryContact() {
        return isPrimaryContact;
    }

    public void setIsPrimaryContact(boolean isPrimaryContact) {
        this.isPrimaryContact = isPrimaryContact;
    }

}
