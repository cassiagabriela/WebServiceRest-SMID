package com.eventos.resources;

import java.util.ArrayList;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventos.models.Disciplina;
import com.eventos.repository.DisciplinaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="API REST Disciplina")
@RestController
@RequestMapping("/disciplina")
public class DisciplinaResource {

    @Autowired
    private DisciplinaRepository er;

    @ApiOperation(value="Retorna uma lista de Disciplinas")
    @GetMapping(produces="application/json")
    public @ResponseBody ArrayList<Disciplina> listaDisciplinas(){
        Iterable<Disciplina> listaDisciplinas = er.findAll();
        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
        for(Disciplina disciplina : listaDisciplinas){
            long codigo = disciplina.getCodigo();
            disciplina.add(linkTo(methodOn(DisciplinaResource.class).disciplina(codigo)).withSelfRel());
            disciplinas.add(disciplina);
        }
        return disciplinas;
    }

    @ApiOperation(value="Retorna um evento específico")
    @GetMapping(value="/{codigo}", produces="application/json")
    public @ResponseBody Disciplina disciplina(@PathVariable(value="codigo") long codigo){
            Disciplina disciplina = er.findByCodigo(codigo);
            disciplina.add(linkTo(methodOn(DisciplinaResource.class).listaDisciplinas()).withRel("Lista de Disciplinas"));
            return disciplina;
    }

    @ApiOperation(value="Salva uma Disciplina")
    @PostMapping()
    public Disciplina cadastraDisciplina(@RequestBody @Valid Disciplina disciplina){
        er.save(disciplina);
        long codigo = disciplina.getCodigo();
        disciplina.add(linkTo(methodOn(DisciplinaResource.class).disciplina(codigo)).withSelfRel());
        return disciplina;
    }

    @ApiOperation(value="Deleta uma Disciplina")
    @DeleteMapping()
    public Disciplina deletaDisciplina(@RequestBody Disciplina disciplina){
        er.delete(disciplina);
        return disciplina;
    }
}

