package one.digitalinnovation.gof.adapter;

import one.digitalinnovation.gof.model.Notificacao;

/**
 * Adapter Pattern - Interface para serviços de notificação
 */
public interface NotificacaoService {
    
    /**
     * Envia uma notificação
     */
    boolean enviar(Notificacao notificacao);
    
    /**
     * Verifica se o serviço suporta o tipo de notificação
     */
    boolean suportaTipo(String tipo);
}
