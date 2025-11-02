package com.kume.kume.auth;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kume.kume.dto.ChangePasswordRequest;
import com.kume.kume.dto.GenericResponse;
import com.kume.kume.models.User;
import com.kume.kume.repositories.UserRepository;

public class AuthController {
    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;

    public AuthController(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<GenericResponse<String>> login(@RequestBody User credentials) {
        Optional<User> userOpt = userRepository.findByEmail(credentials.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>(false, "Correo no registrado", null));
        }
        User user = userOpt.get();
        if (!user.getPassword().equals(credentials.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>(false, "Contraseña incorrecta", null));
        }
        // token JWT
        String token = jwtTokenUtil.generateToken(user.getEmail(), user.getRole().getName());
        return ResponseEntity.ok(new GenericResponse<>(true, "Inicio de sesión exitoso", token));
    }

    // CAMBIAR CONTRASEÑA
    @PostMapping("/change-password/{id}")
    public ResponseEntity<GenericResponse<Void>> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>(false, "Usuario no encontrado", null));
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>(false, "La contraseña actual no coincide", null));
        }

        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.ok(new GenericResponse<>(true, "Contraseña actualizada correctamente", null));
    }
}
