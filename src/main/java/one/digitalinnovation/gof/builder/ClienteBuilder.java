package one.digitalinnovation.gof.builder;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;

public class ClienteBuilder {

    private Cliente cliente;
    private Endereco endereco;

    public ClienteBuilder() {
        this.cliente = new Cliente();
        this.endereco = new Endereco();
    }

    public ClienteBuilder comNome(String nome) {
        this.cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comCep(String cep) {
        this.endereco.setCep(cep);
        return this;
    }

    public ClienteBuilder comLogradouro(String logradouro) {
        this.endereco.setLogradouro(logradouro);
        return this;
    }

    public ClienteBuilder comBairro(String bairro) {
        this.endereco.setBairro(bairro);
        return this;
    }

    public ClienteBuilder comCidade(String cidade) {
        this.endereco.setLocalidade(cidade);
        return this;
    }

    public ClienteBuilder comUf(String uf) {
        this.endereco.setUf(uf);
        return this;
    }

    public ClienteBuilder comComplemento(String complemento) {
        this.endereco.setComplemento(complemento);
        return this;
    }

    public ClienteBuilder comEnderecoCompleto(String cep, String logradouro, String bairro, 
                                            String cidade, String uf, String complemento) {
        this.endereco.setCep(cep);
        this.endereco.setLogradouro(logradouro);
        this.endereco.setBairro(bairro);
        this.endereco.setLocalidade(cidade);
        this.endereco.setUf(uf);
        this.endereco.setComplemento(complemento);
        return this;
    }

    public Cliente build() {
        this.cliente.setEndereco(this.endereco);
        return this.cliente;
    }

    public static ClienteBuilder novoCliente() {
        return new ClienteBuilder();
    }
}
