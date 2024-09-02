package com.squad11.locadora.controllers.carro;

import com.squad11.locadora.dtos.carro.CarroDTO;
import com.squad11.locadora.dtos.carro.request.CreateCarroDTO;
import com.squad11.locadora.dtos.carro.request.ListCarroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carros", description = "API para gerenciamento de carros")
@RequestMapping("/api/carros")
public interface CarroController {

    @Operation(
            summary = "Obter imagem do carro",
            description = "Este endpoint permite recuperar uma imagem do carro armazenada no servidor. A imagem é identificada pelo nome fornecido na URL.",
            parameters = {
                    @Parameter(
                            name = "imageNome",
                            description = "Nome da imagem a ser recuperada, incluindo a extensão do arquivo (por exemplo, 'carro123.jpg').",
                            required = true,
                            example = "carro123.png"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Imagem recuperada com sucesso",
                            content = @Content(
                                    mediaType = "image/png",  // Ajuste conforme o tipo de imagem, pode ser "image/png" ou outro
                                    schema = @Schema(type = "string", format = "binary")
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Imagem não encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @GetMapping("/imagem/{imageNome}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageNome);

    @Operation(
            summary = "Listar carros disponíveis",
            description = "Retorna uma lista de carros disponíveis com suporte a paginação, ordenação, categorias e acessórios.",
            parameters = {
                    @Parameter(name = "page", description = "Número da página para paginação", example = "0"),
                    @Parameter(name = "size", description = "Número de itens por página", example = "10"),
                    @Parameter(name = "orderBy", description = "Ordenar por ascendente ou descendente", example = "asc"),
                    @Parameter(
                            name = "categorias",
                            description = "Lista de categorias para filtrar os carros",
                            example = "[\"SEDAN_MEDIO\", \"MINIVAN\"]"
                    ),
                    @Parameter(
                            name = "acessorios",
                            description = "Lista de acessórios para filtrar os carros",
                            example = "[\"Ar condicionado\", \"Airbag\"]"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de carros disponíveis recuperada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ListCarroDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content)
            }
    )
    @GetMapping("/disponiveis")
    ResponseEntity<ListCarroDTO> getCarrosDisponiveis(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
            @RequestParam(name = "categorias",required = false) List<String> categorias,
            @RequestParam(name = "acessorios", required = false) List<String> acessorios
    );

    @Operation(
            summary = "Obter detalhes de um carro",
            description = "Retorna as informações detalhadas de um carro com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do carro recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = CarroDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Carro não encontrado", content = @Content)
            }
    )
    @GetMapping("/disponiveis/{id}")
    ResponseEntity<CarroDTO> show(
            @Parameter(description = "ID do carro", required = true) @PathVariable Long id
    );

    @Operation(
            summary = "Criar um novo carro",
            description = "Este endpoint permite cadastrar um novo carro, incluindo o upload de uma imagem representativa do veículo.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Carro cadastrado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = CarroDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<CarroDTO> create(
            @ModelAttribute @Validated CreateCarroDTO createCarroDTO
    );
}
