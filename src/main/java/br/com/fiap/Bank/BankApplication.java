package br.com.fiap.Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@RequestMapping
	@ResponseBody
	public String Login(){
		return "Projeto Bank - Matheus O. A. C. Silva e Gustavo Godoi da Silva ";
	}

}
