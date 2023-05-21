package com.hospital.animal.cl.services.firebase.interfaces;

import com.hospital.animal.cl.dto.User;

public interface AuthenticationProvider {
    User loginWithToken(String token);
    User loginWithUsernameAndPassword(String username, String password);
}
