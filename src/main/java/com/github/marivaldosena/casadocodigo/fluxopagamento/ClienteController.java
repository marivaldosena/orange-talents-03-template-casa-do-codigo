package com.github.marivaldosena.casadocodigo.fluxopagamento;

import com.github.marivaldosena.casadocodigo.paises.EstadoRepository;
import com.github.marivaldosena.casadocodigo.paises.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(ClienteController.CAMINHO_DO_RECURSO)
public class ClienteController {
    final static String CAMINHO_DO_RECURSO = "/api/clientes";

    private ClienteRepository clienteRepository;
    private EstadoRepository estadoRepository;
    private PaisRepository paisRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository, EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.clienteRepository = clienteRepository;
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        Cliente cliente = form.toEntity(paisRepository, estadoRepository);
        clienteRepository.save(cliente);
        URI uri = uriBuilder.path(CAMINHO_DO_RECURSO + "/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.ok().header("Location", uri.toString()).body(new ClienteDto(cliente));
    }
}
