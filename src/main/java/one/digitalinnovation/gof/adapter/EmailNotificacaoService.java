package one.digitalinnovation.gof.adapter;

import org.springframework.stereotype.Service;
import one.digitalinnovation.gof.factory.NotificacaoFactory;
import one.digitalinnovation.gof.model.Notificacao;

/**
 * Adapter Pattern - Implementação para serviço de email
 */
@Service
public class EmailNotificacaoService implements NotificacaoService {

    @Override
    public boolean enviar(Notificacao notificacao) {
        if (!suportaTipo(notificacao.getTipo())) {
            return false;
        }
        

        System.out.println("=== ENVIANDO EMAIL ===");
        System.out.println("Para: " + notificacao.getCliente().getNome());
        System.out.println("Assunto: Notificação do Sistema");
        System.out.println("Mensagem: " + notificacao.getMensagem());
        System.out.println("======================");
        
        return true;
    }

    @Override
    public boolean suportaTipo(String tipo) {
        return NotificacaoFactory.TIPO_EMAIL.equals(tipo);
    }
}
