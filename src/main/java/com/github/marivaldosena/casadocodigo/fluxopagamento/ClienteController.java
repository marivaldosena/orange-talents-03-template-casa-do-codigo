package com.github.marivaldosena.casadocodigo.fluxopagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(ClienteController.CAMINHO_DO_RECURSO)
public class ClienteController {
    final static String CAMINHO_DO_RECURSO = "/api/clientes";

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteForm> cadastrarCliente(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.ok(form);
    }
}
