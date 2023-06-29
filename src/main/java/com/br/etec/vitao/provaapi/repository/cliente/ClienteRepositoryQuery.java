package com.br.etec.vitao.provaapi.repository.cliente;

import com.br.etec.vitao.provaapi.model.Cliente;
import com.br.etec.vitao.provaapi.repository.filter.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteRepositoryQuery {
    public Page<Cliente> filter(ClienteFilter clienteFilter, Pageable pageable);
}
