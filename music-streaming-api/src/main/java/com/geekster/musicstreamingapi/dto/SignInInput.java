package com.geekster.musicstreamingapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInInput {

    @Column(nullable = false)
    @NotEmpty
    private String userEmail;

    @NotEmpty
    private String userPassword;

}
