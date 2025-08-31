package one.digitalinnovation.gof.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.adapter.NotificacaoAdapter;
import one.digitalinnovation.gof.factory.NotificacaoFactory;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Notificacao;

/**
 * Template Method Pattern - Implementação para clientes premium
 */
@Service
public class ProcessamentoClientePremium extends ProcessamentoClienteTemplate {

    @Autowired
    private NotificacaoAdapter notificacaoAdapter;


    public ProcessamentoClientePremium() {

    }


    public ProcessamentoClientePremium(NotificacaoAdapter notificacaoAdapter) {
        this.notificacaoAdapter = notificacaoAdapter;
    }

    @Override
    protected void processarClienteEspecifico(Cliente cliente) {
        System.out.println("Processando cliente PREMIUM: " + cliente.getNome());
        System.out.println("Aplicando benefícios exclusivos...");
        System.out.println("Configurando atendimento prioritário...");
    }

    @Override
    protected void enviarNotificacoes(Cliente cliente) {
        System.out.println("Enviando notificações PREMIUM para: " + cliente.getNome());
        

        Notificacao notificacaoPremium = NotificacaoFactory.criarNotificacao(
            NotificacaoFactory.TIPO_PREMIUM, 
            "Parabéns! Você é um cliente PREMIUM e tem acesso a benefícios exclusivos.", 
            cliente
        );
        

        if (notificacaoAdapter != null) {
            notificacaoAdapter.enviarNotificacao(notificacaoPremium);
        } else {
            System.out.println("Notificação PREMIUM criada (adapter não disponível em teste)");
        }
    }

    @Override
    protected void gerarLogs(Cliente cliente) {
        System.out.println("Gerando logs PREMIUM detalhados para: " + cliente.getNome());
        System.out.println("Log: Cliente premium processado com sucesso");
    }
}
