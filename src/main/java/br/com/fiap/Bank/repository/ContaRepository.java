package br.com.fiap.Bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Bank.model.Conta;

public interface ContaRepository extends JpaRepository <Conta, Long> {

    Optional<Conta> findByCpf(String cpf);
    
}
