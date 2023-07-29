package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dto.LoginResponseDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.dto.UserDto;
import pe.edu.upc.model.Category;
import pe.edu.upc.service.IGuardianService;
import pe.edu.upc.service.ISpecialistService;
import pe.edu.upc.service.IUserService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.JWTGenerator;

@CrossOrigin
@Api(tags="Users")
@RestController
@RequestMapping(path = "/auth")
public class UserController {

    @Autowired
    private IUserService userLoginService;

    @Autowired
    private IGuardianService guardianService;

    @Autowired
    private ISpecialistService specialistService;

    @PostMapping(path = "/login/guardians", consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = LoginResponseDto.class)
    })
    public ResponseEntity<?> loginGuardian(@RequestBody UserDto user) {
        int result = userLoginService.loginGuardian(user);
        ResponseDto response = new ResponseDto();
        response.setId(result);
        if (result == Constants.ERROR_PASSWORD) {
            response.setMessage("Contraseña incorrecta");
            return ResponseEntity.ok(response);
        } else if (result == Constants.ERROR_EXIST) {
            response.setMessage("El nombre de usuario no existe");
            return ResponseEntity.ok(response);
        } else {
            String token = JWTGenerator.getJWTToken(user.getUsername(), user.getPassword());
            LoginResponseDto loginResponse = new LoginResponseDto(1, token, guardianService.listByIdGuardian(result));
            return ResponseEntity.ok(loginResponse);
        }
    }

    @PutMapping(path = "/password-reset", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
    })
    public ResponseEntity<?> restorePassword(@RequestBody String email) {
        int result = userLoginService.restorePassword(email);
        ResponseDto response = new ResponseDto();
        response.setId(result);
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
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
    })
    public ResponseEntity<?> login(@RequestBody UserDto user) {
        int result = userLoginService.loginSpecialist(user);
        ResponseDto response = new ResponseDto();
        response.setId(result);
        if (result == Constants.ERROR_PASSWORD) {
            response.setMessage("Contraseña incorrecta");
            return ResponseEntity.ok(response);
        } else if (result == Constants.ERROR_EXIST) {
            response.setMessage("El nombre de usuario no existe");
            return ResponseEntity.ok(response);
        } else {
            String token = JWTGenerator.getJWTToken(user.getUsername(), user.getPassword());
            response.setToken(token);
            response.setSpecialist(specialistService.listByIdSpecialist(result));
            response.setId(1);
            return ResponseEntity.ok(response);
        }
    }
}
