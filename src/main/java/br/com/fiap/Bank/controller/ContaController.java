package br.com.fiap.Bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import br.com.fiap.Bank.model.Conta;
import br.com.fiap.Bank.movimentacao.Movimentacao;
import br.com.fiap.Bank.repository.ContaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("conta")
public class ContaController {
    
    @Autowired
    ContaRepository repository;

    @GetMapping
    public List<Conta> buscarTodas(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Conta buscarPorId(@PathVariable Long numero){
        return repository
            .findById(numero)
            .orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Conta não encontrada")
            );
    }

    @GetMapping("{cpf}/cpf")
    public Conta buscarPorCpf(@PathVariable String cpf){
        return repository
            .findByCpf(cpf).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND,"Erro ao deletar. Verifique o id informado")
                        );
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public Conta adicionar(@RequestBody @Valid Conta conta){
        return repository.save(conta);
    }

    @PostMapping("{id}/depositar")
    public Conta depositar(@PathVariable Long id, @RequestBody Movimentacao movimentacao){
        var conta = buscarPorId(movimentacao.numero());
        if(movimentacao.valor() <= 0){
            throw new ResponseStatusException(BAD_REQUEST, "Valor inválido");
        }
        conta.setSaldo(conta.getSaldo() + movimentacao.valor());
        return repository.save(conta);
    }

    @PostMapping("{id}/saque")
    public Conta saque(@PathVariable Long id, @RequestBody Movimentacao movimentacao){
        var conta = buscarPorId(movimentacao.numero());
        if(movimentacao.valor() <= 0){
            throw new ResponseStatusException(BAD_REQUEST, "Valor inválido");
        }
        conta.setSaldo(conta.getSaldo() - movimentacao.valor());
        return repository.save(conta);
    }


    @DeleteMapping({"id"})
    @ResponseStatus(NO_CONTENT)
    public void encerrar(@PathVariable Long id){
        var conta = buscarPorId(id);
        conta.setAtiva(false);
        repository.save(conta);
    }

}
