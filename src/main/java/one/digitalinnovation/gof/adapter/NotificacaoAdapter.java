package one.digitalinnovation.gof.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Notificacao;

/**
 * Adapter Pattern - Adaptador principal que gerencia todos os serviços de notificação
 */
@Service
public class NotificacaoAdapter {

    @Autowired
    private List<NotificacaoService> notificacaoServices;

    /**
     * Envia uma notificação usando o serviço apropriado
     */
    public boolean enviarNotificacao(Notificacao notificacao) {
        for (NotificacaoService service : notificacaoServices) {
            if (service.suportaTipo(notificacao.getTipo())) {
                boolean enviado = service.enviar(notificacao);
                if (enviado) {
                    System.out.println("Notificação enviada com sucesso via " + notificacao.getTipo());
                    return true;
                }
            }
        }
        
        System.out.println("Nenhum serviço disponível para o tipo: " + notificacao.getTipo());
        return false;
    }

    /**
     * Envia notificações em lote
     */
    public void enviarNotificacoesEmLote(List<Notificacao> notificacoes) {
        System.out.println("Iniciando envio de " + notificacoes.size() + " notificações...");
        
        for (Notificacao notificacao : notificacoes) {
            enviarNotificacao(notificacao);
        }
        
        System.out.println("Envio em lote concluído!");
    }
}
