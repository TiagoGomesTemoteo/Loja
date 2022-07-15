package com.dev.Loja.Controller;

import com.dev.Loja.Modelos.Funcionario;
import com.dev.Loja.Repositorios.CidadeRepositorio;
import com.dev.Loja.Repositorios.FuncionarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;
    @Autowired
    private CidadeRepositorio cidadeRepositorio;


    @GetMapping("/administrativo")
    public String acessarPrincipal(){
        return "administrativo/home";
    }
    @GetMapping("/administrativo/funcionarios/cadastrar")
    public ModelAndView cadastrar(Funcionario funcionario){
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
        mv.addObject("funcionario", funcionario);
        mv.addObject("listaCidades",cidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/administrativo/funcionarios/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
        mv.addObject("listaFuncionarios",funcionarioRepositorio.findAll());
        return mv;
    }

    @PostMapping("/administrativo/funcionarios/salvar")
    public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(funcionario);
        }
        funcionarioRepositorio.saveAndFlush(funcionario);
        return cadastrar(new Funcionario());
    }

    @GetMapping("/administrativo/funcionarios/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
        return cadastrar(funcionario.get());
    }

    @GetMapping("/administrativo/funcionarios/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
        funcionarioRepositorio.delete(funcionario.get());
        return listar();
    }
}
