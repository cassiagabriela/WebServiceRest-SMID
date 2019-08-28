package com.eventos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Entity
    public class Disciplina extends ResourceSupport {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long codigo;

        @NotBlank
        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getQntAlunos() {
            return qntAlunos;
        }

        public void setQntAlunos(int qntAlunos) {
            this.qntAlunos = qntAlunos;
        }

        @NotBlank
        private int qntAlunos;

        public long getCodigo() {
            return codigo;
        }

        public void setCodigo(long codigo) {
            this.codigo = codigo;
        }
    }
