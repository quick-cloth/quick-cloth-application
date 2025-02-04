package org.example.quickclothapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.quickclothapp.exception.DataServiceException;
import org.example.quickclothapp.model.TypeDocument;
import org.example.quickclothapp.model.User;
import org.example.quickclothapp.payload.request.UserRequest;
import org.example.quickclothapp.payload.response.*;
import org.example.quickclothapp.service.intf.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/application/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "#TODO: 26 de septiembre -> Guardar cliente", description = "Guarda un nuevo cliente")
    @ApiResponse(responseCode = "200", description = "El valor mensaje retorna el mensaje de exito", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @PostMapping("/save/client")
    public ResponseEntity<?> getUser(@RequestBody UserRequest user) {
        try {
            return ResponseEntity.ok(userService.saveUserClient(user));
        } catch (DataServiceException e) {
            if (e.getStatusCode() == 409){
                return new ResponseEntity<>(new MessageResponse(e.getMessage(), e.getStatusCode(), null), HttpStatus.CONFLICT);
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @Operation(summary = "#TODO: 26 de septiembre -> Guardar donante", description = "Guarda un nuevo donante")
    @ApiResponse(responseCode = "200", description = "El valor mensaje retorna el mensaje de exito", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @PostMapping("/save/donor")
    public ResponseEntity<?> saveUserDonor(@RequestBody UserRequest user) {
        try {
            return ResponseEntity.ok(userService.saveUserDonor(user));
        } catch (DataServiceException e) {
            if (e.getStatusCode() == 409){
                return new ResponseEntity<>(new MessageResponse(e.getMessage(), e.getStatusCode(), null), HttpStatus.CONFLICT);
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @Operation(summary = "#TODO: 26 de septiembre -> Guardar empleado de fundacion", description = "Guarda un nuevo empleado de fundacion")
    @ApiResponse(responseCode = "200", description = "El valor mensaje retorna el mensaje de exito", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @PostMapping("/save/foundation")
    public ResponseEntity<?> saveUserFoundation(@RequestBody UserRequest user, @RequestParam UUID foundationUuid) {
        try {
            return ResponseEntity.ok(userService.saveUserFoundation(user, foundationUuid));
        } catch (DataServiceException e) {
            if (e.getStatusCode() == 409){
                return new ResponseEntity<>(new MessageResponse(e.getMessage(), e.getStatusCode(), null), HttpStatus.CONFLICT);
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @Operation(summary = "#TODO: 26 de septiembre -> Guardar empleado de banco", description = "Guarda un nuevo empleado de banco")
    @ApiResponse(responseCode = "200", description = "El valor mensaje retorna el mensaje de exito", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})

    @PostMapping("/save/bank")
    public ResponseEntity<?> saveUserBank(@RequestBody UserRequest user, @RequestParam UUID clotheBankUuid) {
        try {
            return ResponseEntity.ok(userService.saveUserBank(user, clotheBankUuid));
        } catch (DataServiceException e) {
            if (e.getStatusCode() == 409){
                return new ResponseEntity<>(new MessageResponse(e.getMessage(), e.getStatusCode(), null), HttpStatus.CONFLICT);
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @Operation(summary = "#TODO: 26 de septiembre -> Guardar empleado de ropero", description = "Guarda un nuevo empleado de ropero")
    @ApiResponse(responseCode = "200", description = "El valor mensaje retorna el mensaje de exito", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @PostMapping("/save/wardrope")
    public ResponseEntity<?> saveUserWardrope(@RequestBody UserRequest user, @RequestParam UUID wardRopeUuid) {
        try {
            return ResponseEntity.ok(userService.saveUserWardrope(user, wardRopeUuid));
        } catch (DataServiceException e) {
            if (e.getStatusCode() == 409){
                return new ResponseEntity<>(new MessageResponse(e.getMessage(), e.getStatusCode(), null), HttpStatus.CONFLICT);
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @Operation(summary = "#TODO: 26 de septiembre -> Obtener un cliente por numero de identificacion")
    @ApiResponse(responseCode = "200", description = "Entidad del usuario con el uuid del cliente", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @GetMapping("/get/identification")
    public ResponseEntity<?> getUser(@RequestParam String identification) {
        try {
            return ResponseEntity.ok(userService.findUserByDocumentNumber(identification));
        } catch (DataServiceException e) {
            if (e.getStatusCode() == 404) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @Operation(summary = "#TODO: 22 de septiembre -> Obtener empleado de ropero", description = "Obtiene un empleado de ropero dado el nombre de usuario")
    @ApiResponse(responseCode = "200", description = "Entidad del usuario con el uuid del ropero al que pertenece", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @GetMapping("/get/wardrobe_employee")
    public ResponseEntity<?> getUserWardRobeByUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok(userService.UserWardRobeByUsername(username));
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), null, null));
        }
    }

    @Operation(summary = "#TODO: 22 de septiembre -> Obtener Empleado de Banco", description = "Obtiene un empleado de banco dado el nombre de usuario")
    @ApiResponse(responseCode = "200", description = "Entidad del usuario con el uuid del banco al que pertenece", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @GetMapping("/get/bank_employee")
    public ResponseEntity<?> getBankEmployeeByUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok(userService.findBankEmployeeByUsername(username));
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), null, null));
        }
    }

    @Operation(summary = "#TODO: 26 de septiembre -> Obtener todos los tipos de documento")
    @ApiResponse(responseCode = "200", description = "La lista de tipos de documento", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TypeDocument.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @GetMapping("/get_all/type_document")
    public ResponseEntity<?> getAllTypeDocument() {
        try {
            return ResponseEntity.ok(userService.findAllTypeDocument());
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), null, null));
        }
    }

    @Operation(summary = "Obtener las ventas de un usuario", description = "Obtiene las ventas de un usuario dado su uuid")
    @ApiResponse(responseCode = "200", description = "Las compras que ha hecho el usuario", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SalesByUserWithPointsResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @GetMapping("/sales/get_all")
    public ResponseEntity<?> findSalesByUser(@RequestParam UUID uuid, @RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate) {
        try {
        return ResponseEntity.ok(userService.findSalesByUser(uuid, startDate, endDate));
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), null, null));
        }
    }

    @Operation(summary = "Obtener un usuario dado su uuid", description = "Obtiene un usuario dado su uuid")
    @ApiResponse(responseCode = "200", description = "Usuario de la aplicación", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerWithPoints.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @GetMapping("/get")
    public ResponseEntity<?> getUserById(@RequestParam UUID uuid) {
        try {
            
            User user = userService.findUserByUuid(uuid);
            
            CustomerWithPoints customerWithPoints = new CustomerWithPoints(
                    user.getUuid(),
                    user.getName(),
                    user.getLast_name(),
                    user.getUser_name(),
                    user.getEmail(),
                    user.getDocument(),
                    user.getDocument(),
                    user.getPoints()
            );
            
            return ResponseEntity.ok(customerWithPoints);
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), null, null));
        }
    }
}
