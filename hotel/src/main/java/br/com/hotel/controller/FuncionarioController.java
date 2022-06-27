package br.com.hotel.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Cargo;
import br.com.hotel.model.Funcionario;
import br.com.hotel.repositorio.CargoRepositorio;
import br.com.hotel.repositorio.FuncionarioRepositorio;

@Service
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	@Autowired
	FuncionarioRepositorio funcionarioRepositorio;
	@Autowired
	CargoRepositorio cargoRepositorio;
	
	@GetMapping
	public Funcionario pegarFuncionario(@RequestParam String cpf){
		return funcionarioRepositorio.findByCpf(cpf);
	}
	@PostMapping
	public void SalvarFuncionario(HttpServletResponse response,Funcionario funcionario,@RequestParam long idCargo) throws IOException{
		Optional<Cargo> cargo = cargoRepositorio.findById(idCargo);
		if (cargo.isPresent()) {
			funcionario.setCargo(cargo.get());
			funcionarioRepositorio.save(funcionario);
		}
		response.sendRedirect("/cadastroFuncionario");
	}
	@DeleteMapping
	public void DeletarFuncionario(@RequestParam long idFuncionario){
		Optional<Funcionario> funcionario = funcionarioRepositorio.findById(idFuncionario);
		if (funcionario.isPresent()) {
			funcionarioRepositorio.deleteById(idFuncionario);
		}
	}
	
}
