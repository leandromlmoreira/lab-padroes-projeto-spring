package one.digitalinnovation.gof.observer;

import one.digitalinnovation.gof.model.Cliente;

public interface ClienteObserver {
    
    void onClienteCriado(Cliente cliente);
    
    void onClienteAtualizado(Cliente cliente);
    
    void onClienteDeletado(Cliente cliente);
}
