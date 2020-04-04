package com.shopping.auditservice.controller;

import com.shopping.auditservice.controller.dto.RecoveryPasswordResponseDTO;
import com.shopping.auditservice.utils.ResponseMessage;
import com.shopping.auditservice.service.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController {

    private AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @PostMapping("/welcome")
    public ResponseEntity sendWelcomeEmail(@RequestHeader("username") String username, @RequestHeader("email") String email) {
        auditService.sendWelcomeEmail(username, email);
        return ResponseEntity.ok().body(new ResponseMessage("Email sent successfully", HttpStatus.OK));
    }

    @PostMapping("/recovery/password")
    public ResponseEntity recoveryPassword(@RequestHeader("username") String username, @RequestHeader("email") String email) {
        RecoveryPasswordResponseDTO recoveryPasswordResponseDTO = auditService.recoveryPassword(username, email);
        return ResponseEntity.ok().body(recoveryPasswordResponseDTO);
    }

}
