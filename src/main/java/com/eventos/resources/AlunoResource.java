package com.eventos.resources;

import java.util.ArrayList;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.eventos.models.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventos.models.Aluno;
import com.eventos.repository.AlunoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="API REST Aluno")
@RestController
@RequestMapping("/aluno")
public class AlunoResource {

    @Autowired
    private AlunoRepository er;

    @ApiOperation(value="Retorna uma lista de Aluno")
    @GetMapping(produces="application/json")
    public @ResponseBody ArrayList<Aluno> listaAlunos(){
        Iterable<Aluno> listaAlunos = er.findAll();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        for(Aluno aluno : listaAlunos){
            long matricula = aluno.getMatricula();
            aluno.add(linkTo(methodOn(AlunoResource.class).aluno(matricula)).withSelfRel());
            alunos.add(aluno);
        }
        return alunos;
    }

    @ApiOperation(value="Retorna um aluno específico")
    @GetMapping(value="/{matricula}", produces="application/json")
    public @ResponseBody
    Aluno aluno(@PathVariable(value="matricula") long matricula){
        Aluno aluno = er.findByMatricula(matricula);
        aluno.add(linkTo(methodOn(AlunoResource.class).listaAlunos()).withRel("Lista de Alunos"));
        return aluno;
    }

    @ApiOperation(value="Salva uma Aluno")
    @PostMapping()
    public Aluno cadastraAluno(@RequestBody @Valid Aluno aluno){
        er.save(aluno);
        long matricula = aluno.getMatricula();
        aluno.add(linkTo(methodOn(AlunoResource.class).aluno(matricula)).withSelfRel());
        return aluno;
    }

    @ApiOperation(value="Deleta uma Aluno")
    @DeleteMapping()
    public Aluno deletaAluno(@RequestBody Aluno aluno){
        er.delete(aluno);
        return aluno;
    }
}

