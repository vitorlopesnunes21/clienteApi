package com.br.etec.vitao.provaapi.resources;

import com.br.etec.vitao.provaapi.model.Cliente;
import com.br.etec.vitao.provaapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteResources {
    @Autowired
    public ClienteRepository clienteRepository;

    @RequestMapping("/todos")
    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

}
