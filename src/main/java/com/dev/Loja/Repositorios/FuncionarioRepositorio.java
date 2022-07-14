package com.dev.Loja.Repositorios;

import com.dev.Loja.Modelos.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

}
