package com.example.check.security;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade  {

    Authentication getAuthentication();
}
