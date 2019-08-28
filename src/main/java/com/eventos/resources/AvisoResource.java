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

import com.eventos.models.Aviso;
import com.eventos.repository.AvisoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST Aviso")
@RestController
@RequestMapping("/aviso")
public class AvisoResource {

    @Autowired
    private AvisoRepository er;

    @ApiOperation(value="Retorna uma lista de Avisos")
    @GetMapping(produces="application/json")
    public @ResponseBody  ArrayList<Aviso> listaAvisos(){
        Iterable<Aviso> listaAvisos = er.findAll();
        ArrayList<Aviso> avisos = new ArrayList<Aviso>();
        for(Aviso aviso : listaAvisos){
            long codigo = aviso.getCodigo();
            aviso.add(linkTo(methodOn(AvisoResource.class).aviso(codigo)).withSelfRel());
            avisos.add(aviso);
        }
        return avisos;
    }

    @ApiOperation(value="Retorna um aviso específico")
    @GetMapping(value="/{codigo}", produces="application/json")
    public @ResponseBody Aviso aviso(@PathVariable(value="codigo") long codigo){
        Aviso aviso = er.findByCodigo(codigo);
        aviso.add(linkTo(methodOn(AvisoResource.class).listaAvisos()).withRel("Lista de Avisos"));
        return aviso;
    }

    @ApiOperation(value="Salva um aviso")
    @PostMapping()
    public Aviso cadastraAviso(@RequestBody @Valid Aviso aviso){
        er.save(aviso);
        long codigo = aviso.getCodigo();
        aviso.add(linkTo(methodOn(AvisoResource.class).aviso(codigo)).withSelfRel());
        return aviso;
    }

    @ApiOperation(value="Deleta um aviso")
    @DeleteMapping()
    public Aviso deletaAviso(@RequestBody Aviso aviso){
        er.delete(aviso);
        return aviso;
    }


}
