package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dto.LoginResponseDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.dto.UserLoginDto;
import pe.edu.upc.service.IGuardianService;
import pe.edu.upc.service.ISpecialistService;
import pe.edu.upc.service.IUserLoginService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.JWTGenerator;

@CrossOrigin
@Api(tags="User")
@RestController
@RequestMapping(path = "/auth")
public class UserLoginController {

    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private IGuardianService guardianService;

    @Autowired
    private ISpecialistService specialistService;

    @PostMapping(path = "/login/guardians", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> loginGuardian(@RequestBody UserLoginDto userLogin) {
        int result = userLoginService.loginGuardian(userLogin);
        ResponseDto response = new ResponseDto();
        response.setIdResponse(result);
        if (result == Constants.ERROR_PASSWORD) {
            response.setMessage("Contraseña incorrecta");
            return ResponseEntity.ok(response);
        } else if (result == Constants.ERROR_EXIST) {
            response.setMessage("El nombre de usuario no existe");
            return ResponseEntity.ok(response);
        } else {
            String token = JWTGenerator.getJWTToken(userLogin.getUsername(), userLogin.getPassword());
            LoginResponseDto loginResponse = new LoginResponseDto(1, token, guardianService.listByIdGuardian(result));
            return ResponseEntity.ok(loginResponse);
        }
    }

    @PutMapping(path = "/password-reset", produces = "application/json")
    public ResponseEntity<?> restorePassword(@RequestBody String email) {
        int result = userLoginService.restorePassword(email);
        ResponseDto response = new ResponseDto();
        response.setIdResponse(result);
        if (result == Constants.ERROR_EXIST) {
            response.setMessage("Email no registrado");
        } else if (result == Constants.ERROR_BD) {
            response.setMessage("Error al restaurar contraseña");
        } else if (result == Constants.ERROR_EMAIL) {
            response.setMessage("Error al enviar correo");
        } else {
            response.setMessage("Correo enviado");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login/specialists", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLogin) {
        int result = userLoginService.loginSpecialist(userLogin);
        ResponseDto response = new ResponseDto();
        response.setIdResponse(result);
        if (result == Constants.ERROR_PASSWORD) {
            response.setMessage("Contraseña incorrecta");
            return ResponseEntity.ok(response);
        } else if (result == Constants.ERROR_EXIST) {
            response.setMessage("El nombre de usuario no existe");
            return ResponseEntity.ok(response);
        } else {
            String token = JWTGenerator.getJWTToken(userLogin.getUsername(), userLogin.getPassword());
            response.setToken(token);
            response.setSpecialist(specialistService.listByIdSpecialist(result));
            response.setIdResponse(1);
            return ResponseEntity.ok(response);
        }
    }
}
