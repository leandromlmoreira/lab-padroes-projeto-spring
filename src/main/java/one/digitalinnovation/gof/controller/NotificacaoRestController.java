package one.digitalinnovation.gof.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.adapter.NotificacaoAdapter;
import one.digitalinnovation.gof.factory.NotificacaoFactory;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Notificacao;
import one.digitalinnovation.gof.model.NotificacaoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.template.ProcessamentoClientePremium;

/**
 * Controller para demonstrar os novos padrões de projeto implementados
 */
@RestController
@RequestMapping("notificacoes")
public class NotificacaoRestController {

    @Autowired
    private NotificacaoRepository notificacaoRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private NotificacaoAdapter notificacaoAdapter;
    
    @Autowired
    private ProcessamentoClientePremium processamentoPremium;

    /**
     * Lista todas as notificações
     */
    @GetMapping
    public ResponseEntity<Iterable<Notificacao>> buscarTodas() {
        return ResponseEntity.ok(notificacaoRepository.findAll());
    }

    /**
     * Busca notificações por cliente
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Notificacao>> buscarPorCliente(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.buscarPorId(clienteId);
        List<Notificacao> notificacoes = (List<Notificacao>) notificacaoRepository.findAll();
        notificacoes.removeIf(n -> !n.getCliente().getId().equals(clienteId));
        return ResponseEntity.ok(notificacoes);
    }

    /**
     * Cria uma notificação personalizada usando Factory Method
     */
    @PostMapping("/criar")
    public ResponseEntity<Notificacao> criarNotificacao(
            @RequestParam String tipo,
            @RequestParam String mensagem,
            @RequestParam Long clienteId) {
        
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Notificacao notificacao = NotificacaoFactory.criarNotificacao(tipo, mensagem, cliente);
        notificacaoRepository.save(notificacao);
        
        return ResponseEntity.ok(notificacao);
    }

    /**
     * Envia uma notificação usando Adapter Pattern
     */
    @PostMapping("/enviar/{notificacaoId}")
    public ResponseEntity<String> enviarNotificacao(@PathVariable Long notificacaoId) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId).orElse(null);
        if (notificacao == null) {
            return ResponseEntity.notFound().build();
        }
        
        boolean enviada = notificacaoAdapter.enviarNotificacao(notificacao);
        if (enviada) {
            notificacao.setEnviada(true);
            notificacaoRepository.save(notificacao);
            return ResponseEntity.ok("Notificação enviada com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Falha ao enviar notificação");
        }
    }

    /**
     * Processa um cliente como premium usando Template Method
     */
    @PostMapping("/processar-premium/{clienteId}")
    public ResponseEntity<String> processarClientePremium(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.buscarPorId(clienteId);
        processamentoPremium.processarCliente(cliente);
        return ResponseEntity.ok("Cliente processado como premium com sucesso!");
    }

    /**
     * Demonstra o Builder Pattern criando um cliente
     */
    @PostMapping("/criar-cliente-builder")
    public ResponseEntity<Cliente> criarClienteComBuilder(
            @RequestParam String nome,
            @RequestParam String cep,
            @RequestParam String logradouro,
            @RequestParam String bairro,
            @RequestParam String cidade,
            @RequestParam String uf) {
        

        Cliente cliente = one.digitalinnovation.gof.builder.ClienteBuilder.novoCliente()
            .comNome(nome)
            .comCep(cep)
            .comLogradouro(logradouro)
            .comBairro(bairro)
            .comCidade(cidade)
            .comUf(uf)
            .build();
        
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }
}
