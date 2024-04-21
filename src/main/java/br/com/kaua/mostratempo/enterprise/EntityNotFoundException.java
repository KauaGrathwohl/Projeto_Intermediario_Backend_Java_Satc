package br.com.kaua.mostratempo.enterprise;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("O id " + id + " não foi encontrado!");
    }
}
