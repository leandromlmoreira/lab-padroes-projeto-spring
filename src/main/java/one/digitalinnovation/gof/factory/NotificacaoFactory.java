package one.digitalinnovation.gof.factory;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Notificacao;

public class NotificacaoFactory {

    public static final String TIPO_EMAIL = "EMAIL";
    public static final String TIPO_SMS = "SMS";
    public static final String TIPO_PUSH = "PUSH";
    public static final String TIPO_WHATSAPP = "WHATSAPP";
    public static final String TIPO_PREMIUM = "PREMIUM";

    public static Notificacao criarNotificacao(String tipo, String mensagem, Cliente cliente) {
        switch (tipo.toUpperCase()) {
            case TIPO_EMAIL:
                return new Notificacao(mensagem, TIPO_EMAIL, cliente);
            case TIPO_SMS:
                return new Notificacao(mensagem, TIPO_SMS, cliente);
            case TIPO_PUSH:
                return new Notificacao(mensagem, TIPO_PUSH, cliente);
            case TIPO_WHATSAPP:
                return new Notificacao(mensagem, TIPO_WHATSAPP, cliente);
            case TIPO_PREMIUM:
                return new Notificacao(mensagem, TIPO_PREMIUM, cliente);
            default:
                throw new IllegalArgumentException("Tipo de notificação não suportado: " + tipo);
        }
    }

    public static Notificacao criarNotificacaoBoasVindas(Cliente cliente) {
        String mensagem = String.format("Bem-vindo(a) %s! Seu cadastro foi realizado com sucesso.", cliente.getNome());
        return criarNotificacao(TIPO_EMAIL, mensagem, cliente);
    }

    public static Notificacao criarNotificacaoAtualizacao(Cliente cliente) {
        String mensagem = String.format("Olá %s! Seus dados foram atualizados com sucesso.", cliente.getNome());
        return criarNotificacao(TIPO_SMS, mensagem, cliente);
    }

    public static Notificacao criarNotificacaoExclusao(Cliente cliente) {
        String mensagem = String.format("Olá %s! Seu cadastro foi removido de nossa base.", cliente.getNome());
        return criarNotificacao(TIPO_PUSH, mensagem, cliente);
    }
}
