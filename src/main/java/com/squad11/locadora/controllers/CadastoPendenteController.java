package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.ResponseCreateMotoristaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Cadastro Pendente", description = "API para gerenciamento de confirmação de cadastro de motoristas")
@RequestMapping("/api/v1/confirmar-cadastro")
public interface CadastoPendenteController {

    @Operation(
            summary = "Validar token de confirmação",
            description = "Valida um token de confirmação de cadastro e retorna um status de sucesso ou erro.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Token validado com sucesso",
                            content = @Content(
                                    schema = @Schema(type = "string")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Token inválido ou expirado", content = @Content)
            }
    )
    @GetMapping("{token}")
    ResponseEntity<String> validTokenConfirmation(
            @Parameter(description = "Token de confirmação", required = true) @PathVariable String token
    );

    @Operation(
            summary = "Criar link de confirmação",
            description = "Gera um link de confirmação de cadastro para um motorista específico.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Link de confirmação gerado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseCreateMotoristaDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Motorista não encontrado", content = @Content)
            }
    )
    @PostMapping("{motoristaId}")
    ResponseEntity<ResponseCreateMotoristaDTO> createLinkConfirmation(
            @Parameter(description = "ID do motorista para o qual o link de confirmação será criado", required = true)
            @PathVariable Long motoristaId
    );
}
