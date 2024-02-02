package com.tech.kj.service.external.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Roles {
    private long id;

    private String name;

    private String description;
}
