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
    public class Aluno extends ResourceSupport {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long matricula;

        @NotBlank
        private String nome;

        @NotBlank
        private String email;

        private String telefone;

        @NotBlank
        private String statusMatricula;


        public long getMatricula() {
            return matricula;
        }

        public void setMatricula(long matricula) {
            this.matricula = matricula;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getStatusMatricula() {
            return statusMatricula;
        }

        public void setStatusMatricula(String statusMatricula) {
            this.statusMatricula = statusMatricula;
        }
    }
