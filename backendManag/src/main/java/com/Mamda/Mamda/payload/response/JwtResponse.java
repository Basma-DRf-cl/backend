package com.Mamda.Mamda.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class JwtResponse {
  private String token;
  private int id;
  private String username;
  private String email;
}
