package one.digitalinnovation.gof.template;

import one.digitalinnovation.gof.model.Cliente;

public abstract class ProcessamentoClienteTemplate {

    public final void processarCliente(Cliente cliente) {
        System.out.println("=== INICIANDO PROCESSAMENTO DO CLIENTE ===");
        
        if (!validarCliente(cliente)) {
            System.out.println("Cliente inválido. Processamento interrompido.");
            return;
        }
        
        processarClienteEspecifico(cliente);
        
        enviarNotificacoes(cliente);
        
        gerarLogs(cliente);
        
        System.out.println("=== PROCESSAMENTO CONCLUÍDO ===");
    }

    protected boolean validarCliente(Cliente cliente) {
        return cliente != null && cliente.getNome() != null && !cliente.getNome().trim().isEmpty();
    }

    protected void enviarNotificacoes(Cliente cliente) {
        System.out.println("Enviando notificações padrão para: " + cliente.getNome());
    }

    protected void gerarLogs(Cliente cliente) {
        if (cliente != null) {
            System.out.println("Gerando logs para: " + cliente.getNome());
        }
    }

    protected abstract void processarClienteEspecifico(Cliente cliente);
}
