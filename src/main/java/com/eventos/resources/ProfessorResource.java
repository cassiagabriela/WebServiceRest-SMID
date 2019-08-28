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

import com.eventos.models.Professor;
import com.eventos.repository.ProfessorRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST Professores")
@RestController
@RequestMapping("/professor")
public class ProfessorResource {

    @Autowired
    private ProfessorRepository er;

    @ApiOperation(value="Retorna uma lista de Professores")
    @GetMapping(produces="application/json")
    public @ResponseBody  ArrayList<Professor> listaProfessores(){
        Iterable<Professor> listaProfessores = er.findAll();
        ArrayList<Professor> professores = new ArrayList<Professor>();
        for(Professor professor : listaProfessores){
            long codigo = professor.getCodigo();
            professor.add(linkTo(methodOn(ProfessorResource.class).professor(codigo)).withSelfRel());
            professores.add(professor);
        }
        return professores;
    }

    @ApiOperation(value="Retorna um professor específico")
    @GetMapping(value="/{codigo}", produces="application/json")
    public @ResponseBody Professor professor(@PathVariable(value="codigo") long codigo){
        Professor professor = er.findByCodigo(codigo);
        professor.add(linkTo(methodOn(ProfessorResource.class).listaProfessores()).withRel("Lista de Professores"));
        return professor;
    }

    @ApiOperation(value="Salva um professor")
    @PostMapping()
    public Professor cadastraProfessor(@RequestBody @Valid Professor professor){
        er.save(professor);
        long codigo = professor.getCodigo();
        professor.add(linkTo(methodOn(ProfessorResource.class).professor(codigo)).withSelfRel());
        return professor;
    }

    @ApiOperation(value="Deleta um professor")
    @DeleteMapping()
    public Professor deletaProfessor(@RequestBody Professor professor) {
        er.delete(professor);
        return professor;
    }
}
