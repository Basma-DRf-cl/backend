package com.Mamda.Mamda.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {
    @NotBlank
	private String email;
	@NotBlank
	private String password;
}
