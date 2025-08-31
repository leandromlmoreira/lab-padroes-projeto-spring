package one.digitalinnovation.gof.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.model.NotificacaoRepository;
import one.digitalinnovation.gof.observer.ClienteObserver;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	@Autowired
	private NotificacaoRepository notificacaoRepository;
	
	@Autowired
	private List<ClienteObserver> observers;
	
	@PostConstruct
	public void verificarObservadores() {
		System.out.println("=== VERIFICAÇÃO DE OBSERVADORES ===");
		System.out.println("Observers injetados: " + (observers != null ? observers.size() : "NULL"));
		if (observers != null) {
			for (int i = 0; i < observers.size(); i++) {
				System.out.println("Observador " + i + ": " + observers.get(i).getClass().getSimpleName());
			}
		}
		System.out.println("=== FIM VERIFICAÇÃO ===");
	}

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
		notificarObservadores(cliente, "CRIADO");
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if (clienteBd.isPresent()) {
			salvarClienteComCep(cliente);
			notificarObservadores(cliente, "ATUALIZADO");
		}
	}

	@Override
	@Transactional
	public void deletar(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			notificarObservadores(cliente.get(), "DELETADO");
			
			if (notificacaoRepository != null) {
				notificacaoRepository.deleteByClienteId(id);
			}
		}
		clienteRepository.deleteById(id);
	}

	private void salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			novoEndereco.setCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		
		Endereco enderecoUsuario = cliente.getEndereco();
		if (enderecoUsuario.getLogradouro() != null && !enderecoUsuario.getLogradouro().trim().isEmpty()) {
			endereco.setLogradouro(enderecoUsuario.getLogradouro());
		}
		if (enderecoUsuario.getComplemento() != null && !enderecoUsuario.getComplemento().trim().isEmpty()) {
			endereco.setComplemento(enderecoUsuario.getComplemento());
		}
		if (enderecoUsuario.getBairro() != null && !enderecoUsuario.getBairro().trim().isEmpty()) {
			endereco.setBairro(enderecoUsuario.getBairro());
		}
		if (enderecoUsuario.getLocalidade() != null && !enderecoUsuario.getLocalidade().trim().isEmpty()) {
			endereco.setLocalidade(enderecoUsuario.getLocalidade());
		}
		if (enderecoUsuario.getUf() != null && !enderecoUsuario.getUf().trim().isEmpty()) {
			endereco.setUf(enderecoUsuario.getUf());
		}
		
		enderecoRepository.save(endereco);
		
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}
	
	private void notificarObservadores(Cliente cliente, String acao) {
		System.out.println("=== DEBUG OBSERVADORES ===");
		System.out.println("Notificando observadores sobre cliente " + acao + ": " + cliente.getNome());
		System.out.println("Total de observadores: " + (observers != null ? observers.size() : "NULL"));
		
		if (observers == null || observers.isEmpty()) {
			System.err.println("ERRO: Lista de observadores está vazia ou nula!");
			return;
		}
		
		for (ClienteObserver observer : observers) {
			try {
				System.out.println("Notificando observador: " + observer.getClass().getSimpleName());
				switch (acao) {
					case "CRIADO":
						observer.onClienteCriado(cliente);
						break;
					case "ATUALIZADO":
						observer.onClienteAtualizado(cliente);
						break;
					case "DELETADO":
						observer.onClienteDeletado(cliente);
						break;
				}
			} catch (Exception e) {
				System.err.println("Erro ao notificar observador: " + e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println("=== FIM DEBUG OBSERVADORES ===");
	}

}
