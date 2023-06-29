package com.br.etec.vitao.provaapi.resources;

import com.br.etec.vitao.provaapi.model.ContasReceber;
import com.br.etec.vitao.provaapi.repository.ContasReceberRepository;
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
@RequestMapping("contasreceber")
public class ContasReceberResources {
    @Autowired
    public ContasReceberRepository contasReceberRepository;

    @RequestMapping("/todos")
    public List<ContasReceber> listarTodasContasReceber(){
        return contasReceberRepository.findAll();
    }

    @GetMapping()
    public Page<ContasReceberDTO> filtrarContasReceber(ContasReceberFilter contasReceberFilter, Pageable pageable){
        return contasReceberRepository.filter(contasReceberFilter, pageable);
    }

}
