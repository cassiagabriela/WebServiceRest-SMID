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

import com.eventos.models.Frequencia;
import com.eventos.repository.FrequenciaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST Frequencias")
@RestController
@RequestMapping("/frequencia")
public class FrequenciaResource {

    @Autowired
    private FrequenciaRepository er;

    @ApiOperation(value="Retorna uma lista de Frequencias")
    @GetMapping(produces="application/json")
    public @ResponseBody  ArrayList<Frequencia> listaFrequencias(){
        Iterable<Frequencia> listaFrequencias = er.findAll();
        ArrayList<Frequencia> frequencias = new ArrayList<Frequencia>();
        for(Frequencia frequencia : listaFrequencias){
            long codigo = frequencia.getCodigo();
            frequencia.add(linkTo(methodOn(FrequenciaResource.class).frequencia(codigo)).withSelfRel());
            frequencias.add(frequencia);
        }
        return frequencias;
    }

    @ApiOperation(value="Retorna um frequencia específico")
    @GetMapping(value="/{codigo}", produces="application/json")
    public @ResponseBody Frequencia frequencia(@PathVariable(value="codigo") long codigo){
        Frequencia frequencia = er.findByCodigo(codigo);
        frequencia.add(linkTo(methodOn(FrequenciaResource.class).listaFrequencias()).withRel("Lista de Frequencias"));
        return frequencia;
    }

    @ApiOperation(value="Salva um frequencia")
    @PostMapping()
    public Frequencia cadastraFrequencia(@RequestBody @Valid Frequencia frequencia){
        er.save(frequencia);
        long codigo = frequencia.getCodigo();
        frequencia.add(linkTo(methodOn(FrequenciaResource.class).frequencia(codigo)).withSelfRel());
        return frequencia;
    }

    @ApiOperation(value="Deleta um frequencia")
    @DeleteMapping()
    public Frequencia deletaFrequencia(@RequestBody Frequencia frequencia){
        er.delete(frequencia);
        return frequencia;
    }
}
