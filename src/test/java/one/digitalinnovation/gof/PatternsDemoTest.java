package one.digitalinnovation.gof;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import one.digitalinnovation.gof.adapter.NotificacaoAdapter;
import one.digitalinnovation.gof.builder.ClienteBuilder;
import one.digitalinnovation.gof.factory.NotificacaoFactory;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Notificacao;
import one.digitalinnovation.gof.template.ProcessamentoClientePremium;

/**
 * Teste para demonstrar todos os padrões de projeto implementados
 */
@SpringBootTest
class PatternsDemoTest {

    @Test
    void demonstrarFactoryMethod() {
        System.out.println("\n=== DEMONSTRANDO FACTORY METHOD ===");
        

        Cliente cliente = ClienteBuilder.novoCliente()
            .comNome("João Silva")
            .comCep("01001-000")
            .build();
        

        Notificacao notificacaoEmail = NotificacaoFactory.criarNotificacaoBoasVindas(cliente);
        Notificacao notificacaoSms = NotificacaoFactory.criarNotificacaoAtualizacao(cliente);
        Notificacao notificacaoPush = NotificacaoFactory.criarNotificacaoExclusao(cliente);
        
        System.out.println("Notificação Email criada: " + notificacaoEmail.getTipo());
        System.out.println("Notificação SMS criada: " + notificacaoSms.getTipo());
        System.out.println("Notificação Push criada: " + notificacaoPush.getTipo());
    }

    @Test
    void demonstrarBuilder() {
        System.out.println("\n=== DEMONSTRANDO BUILDER ===");
        

        Cliente cliente = ClienteBuilder.novoCliente()
            .comNome("Maria Santos")
            .comCep("20040-007")
            .comLogradouro("Rua do Ouvidor")
            .comBairro("Centro")
            .comCidade("Rio de Janeiro")
            .comUf("RJ")
            .comComplemento("Apto 101")
            .build();
        
        System.out.println("Cliente criado com Builder: " + cliente.getNome());
        System.out.println("Endereço: " + cliente.getEndereco().getLogradouro() + 
                          ", " + cliente.getEndereco().getBairro() + 
                          ", " + cliente.getEndereco().getLocalidade() + "/" + 
                          cliente.getEndereco().getUf());
    }

    @Test
    void demonstrarTemplateMethod() {
        System.out.println("\n=== DEMONSTRANDO TEMPLATE METHOD ===");
        

        Cliente cliente = ClienteBuilder.novoCliente()
            .comNome("Pedro Oliveira")
            .comCep("40026-010")
            .build();
        

        ProcessamentoClientePremium processamento = new ProcessamentoClientePremium();
        processamento.processarCliente(cliente);
    }

    @Test
    void demonstrarAdapter() {
        System.out.println("\n=== DEMONSTRANDO ADAPTER ===");
        

        Cliente cliente = ClienteBuilder.novoCliente()
            .comNome("Ana Costa")
            .comCep("01001-000")
            .build();
        
        Notificacao notificacaoEmail = NotificacaoFactory.criarNotificacao(
            NotificacaoFactory.TIPO_EMAIL, 
            "Teste de email via Adapter", 
            cliente
        );
        
        Notificacao notificacaoSms = NotificacaoFactory.criarNotificacao(
            NotificacaoFactory.TIPO_SMS, 
            "Teste de SMS via Adapter", 
            cliente
        );
        

        System.out.println("Notificações criadas para demonstração do Adapter Pattern:");
        System.out.println("- Email: " + notificacaoEmail.getTipo());
        System.out.println("- SMS: " + notificacaoSms.getTipo());
    }

    @Test
    void demonstrarObserver() {
        System.out.println("\n=== DEMONSTRANDO OBSERVER ===");
        

        Cliente cliente = ClienteBuilder.novoCliente()
            .comNome("Carlos Lima")
            .comCep("01001-000")
            .build();
        
        System.out.println("Cliente criado: " + cliente.getNome());
        System.out.println("O padrão Observer será ativado automaticamente quando o cliente for persistido no banco");
        System.out.println("e irá criar notificações automáticas para diferentes eventos (criação, atualização, exclusão)");
    }

    @Test
    void demonstrarSingleton() {
        System.out.println("\n=== DEMONSTRANDO SINGLETON ===");
        
        System.out.println("O padrão Singleton é implementado automaticamente pelo Spring Framework");
        System.out.println("através das anotações @Service, @Repository e @Component");
        System.out.println("Cada bean gerenciado pelo Spring é uma instância única (Singleton)");
        System.out.println("Exemplos: ClienteService, ClienteRepository, NotificacaoAdapter");
    }

    @Test
    void demonstrarStrategy() {
        System.out.println("\n=== DEMONSTRANDO STRATEGY ===");
        
        System.out.println("O padrão Strategy é implementado através da interface ClienteService");
        System.out.println("e sua implementação ClienteServiceImpl");
        System.out.println("Permite trocar facilmente a implementação do serviço sem afetar o código cliente");
        System.out.println("Interface: ClienteService");
        System.out.println("Implementação: ClienteServiceImpl");
    }

    @Test
    void demonstrarFacade() {
        System.out.println("\n=== DEMONSTRANDO FACADE ===");
        
        System.out.println("O padrão Facade é implementado através dos Controllers REST");
        System.out.println("ClienteRestController: Facade para operações de cliente");
        System.out.println("NotificacaoRestController: Facade para operações de notificação");
        System.out.println("Eles abstraem a complexidade dos serviços e fornecem uma interface simples (API REST)");
    }
}
