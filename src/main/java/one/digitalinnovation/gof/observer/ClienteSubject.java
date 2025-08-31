package one.digitalinnovation.gof.observer;

/**
 * Observer Pattern - Interface para o sujeito que será observado
 */
public interface ClienteSubject {
    
    /**
     * Adiciona um observador
     */
    void adicionarObserver(ClienteObserver observer);
    
    /**
     * Remove um observador
     */
    void removerObserver(ClienteObserver observer);
    
    /**
     * Notifica todos os observadores sobre mudanças
     */
    void notificarObservers();
}
