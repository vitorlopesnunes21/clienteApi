package com.br.etec.vitao.provaapi.resources;

import com.br.etec.vitao.provaapi.model.Cliente;
import com.br.etec.vitao.provaapi.repository.ClienteRepository;
import com.br.etec.vitao.provaapi.repository.filter.ClienteFilter;
import com.br.etec.vitao.provaapi.repository.filter.ContasReceberFilter;
import com.br.etec.vitao.provaapi.repository.projections.ContasReceberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping()
    public Page<Cliente> filtrarCliente(ClienteFilter clienteFilter, Pageable pageable){
        return clienteRepository.filter(clienteFilter, pageable);
    }

}
