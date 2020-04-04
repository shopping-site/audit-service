package com.shopping.auditservice.service;

import com.shopping.auditservice.controller.dto.RecoveryPasswordResponseDTO;

public interface AuditService {

    void sendWelcomeEmail(String username, String email);

    RecoveryPasswordResponseDTO recoveryPassword(String username, String email);
}
