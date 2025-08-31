package one.digitalinnovation.gof.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.factory.NotificacaoFactory;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Notificacao;
import one.digitalinnovation.gof.model.NotificacaoRepository;

@Component
public class NotificacaoObserver implements ClienteObserver {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Override
    public void onClienteCriado(Cliente cliente) {
        Notificacao notificacao = NotificacaoFactory.criarNotificacaoBoasVindas(cliente);
        notificacaoRepository.save(notificacao);
        System.out.println("Notificação de boas-vindas criada para: " + cliente.getNome());
    }

    @Override
    public void onClienteAtualizado(Cliente cliente) {
        Notificacao notificacao = NotificacaoFactory.criarNotificacaoAtualizacao(cliente);
        notificacaoRepository.save(notificacao);
        System.out.println("Notificação de atualização criada para: " + cliente.getNome());
    }

    @Override
    public void onClienteDeletado(Cliente cliente) {
        Notificacao notificacao = NotificacaoFactory.criarNotificacaoExclusao(cliente);
        notificacaoRepository.save(notificacao);
        System.out.println("Notificação de exclusão criada para: " + cliente.getNome());
    }
}
