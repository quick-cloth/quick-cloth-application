package org.example.quickclothapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.quickclothapp.exception.DataServiceException;
import org.example.quickclothapp.model.TypeMeetUs;
import org.example.quickclothapp.payload.request.FoundationRequest;
import org.example.quickclothapp.payload.response.FoundationResponse;
import org.example.quickclothapp.payload.response.MessageResponse;
import org.example.quickclothapp.service.intf.IFoundationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/application/foundation")
public class FoundationController {

    private final IFoundationService foundationService;

    public FoundationController(IFoundationService foundationService) {
        this.foundationService = foundationService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveFoundation(@RequestBody FoundationRequest foundation) {
        try {
            return ResponseEntity.ok(foundationService.saveFoundation(foundation));
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateFoundation(@RequestBody FoundationRequest foundation) {
        try {
            return ResponseEntity.ok(foundationService.updateFoundation(foundation));
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @Operation(summary = "#TODO: 10 de octubre  -> Obtener fundaciones por banco de ropa")
    @ApiResponse(responseCode = "200", description = "Lista de fundaciones", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FoundationResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @GetMapping("/get_all/clothe_bank")
    public ResponseEntity<?> getAllFoundationByClotheBank(@RequestParam UUID clotheBankUuid) {
        try {
            return ResponseEntity.ok(foundationService.findAllFoundationByClotheBank(clotheBankUuid));
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @Operation(summary = "#TODO: 10 de octubre  -> Obtener una fundacion por uuid")
    @ApiResponse(responseCode = "200", description = "Fundacion", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FoundationResponse.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @GetMapping("/get")
    public ResponseEntity<?> getFoundation(@RequestParam UUID foundationUuid) {
        try {
            return ResponseEntity.ok(foundationService.findFoundationResponseByUuid(foundationUuid));
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }

    @Operation(summary = "#TODO: 22 de septiembre -> Obtener todos lo tipos de como nos conocieron", description = "Obtener todos los tipos de como nos conocieron")
    @ApiResponse(responseCode = "200", description = "Tipos de como nos conocieron", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TypeMeetUs.class))})
    @ApiResponse(responseCode = "400", description = "El valor mensaje retorna el mensaje de error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))})
    @GetMapping("/type_meet_us/get_all")
    public ResponseEntity<?> getAllTypeMeetUs() {
        try {
            return ResponseEntity.ok(foundationService.getAllTypeMeetUs());
        } catch (DataServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), e.getStatusCode(), null));
        }
    }
}
