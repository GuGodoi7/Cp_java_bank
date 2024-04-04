package br.com.fiap.Bank.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.fiap.Bank.validation.TipoConta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
public class Conta {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero;

    private String agencia;
    
    @NotBlank
    private String nomeTitular;

    @CPF
    private String cpf;
    
    @PastOrPresent
    private LocalDate dataDeAbertura;

    @PositiveOrZero
    private double saldo;

    private boolean ativa = true;

    @TipoConta
    private String tipo;
}
