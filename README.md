# 🚀 Lab Padrões de Projeto com Spring

> **Sistema completo de gerenciamento de clientes demonstrando 8 padrões de projeto em prática**

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-green.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Sobre o Projeto

Este projeto demonstra a implementação prática de **8 padrões de projeto fundamentais** usando Spring Framework, criando um sistema completo de gerenciamento de clientes com dashboard moderno, notificações automáticas e integração com APIs externas.

## 🎯 Padrões Implementados

| Padrão | Descrição | Implementação |
|--------|-----------|---------------|
| **Singleton** | Instância única gerenciada pelo Spring | `@Service`, `@Repository`, `@Component` |
| **Strategy** | Diferentes estratégias para operações | Interface `ClienteService` + implementações |
| **Facade** | Interface simplificada para subsistemas | Controllers REST |
| **Factory Method** | Criação de objetos sem especificar classes | `NotificacaoFactory` |
| **Observer** | Notificações automáticas de mudanças | Sistema de notificações em tempo real |
| **Builder** | Construção fluente de objetos | `ClienteBuilder` |
| **Adapter** | Interface unificada para serviços | `NotificacaoAdapter` (Email/SMS) |
| **Template Method** | Esqueleto de algoritmo reutilizável | `ProcessamentoClienteTemplate` |

## 🛠️ Tecnologias

- **Backend**: Java 11, Spring Boot 2.5.4, Spring Data JPA
- **Database**: H2 (desenvolvimento), suporte a MySQL/PostgreSQL
- **Frontend**: HTML5, CSS3, JavaScript ES6+, Chart.js
- **APIs**: ViaCEP (endereços), OpenAPI/Swagger
- **Build**: Maven, Spring Boot Maven Plugin

## 🚀 Como Executar

### Pré-requisitos
- Java 11+
- Maven 3.6+
- Git

### 1. Clone o Repositório
```bash
git clone https://github.com/leandromlmoreira/lab-padroes-projeto-spring
cd lab-padroes-projeto-spring
```

### 2. Execute o Projeto
```bash
./mvnw spring-boot:run
```

### 3. Acesse a Aplicação
- 🌐 **Dashboard**: [http://localhost:8080/dashboard.html](http://localhost:8080/dashboard.html)
- 📚 **API Docs**: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)
- 🗄️ **Database**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### Configuração H2 Database
```
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (deixe em branco)
```

## 📁 Estrutura do Projeto

```
src/main/java/one/digitalinnovation/gof/
├── 📂 controller/          # Controllers REST (Facade Pattern)
├── 📂 service/            # Interfaces e implementações (Strategy Pattern)
├── 📂 model/              # Entidades JPA
├── 📂 repository/         # Repositórios Spring Data
├── 📂 observer/           # Observer Pattern
├── 📂 factory/            # Factory Method Pattern
├── 📂 builder/            # Builder Pattern
├── 📂 adapter/            # Adapter Pattern
└── 📂 template/           # Template Method Pattern
```

## ✨ Funcionalidades

### 🏢 Sistema de Clientes
- ✅ Cadastro completo com validação
- ✅ Integração automática ViaCEP
- ✅ CRUD completo (Create, Read, Update, Delete)
- ✅ Preservação de dados do usuário
- ✅ Validação de campos obrigatórios

### 🔔 Sistema de Notificações
- ✅ Notificações automáticas para todas as operações
- ✅ Diferentes tipos (Boas-vindas, Atualização, Exclusão)
- ✅ Contador em tempo real
- ✅ Histórico completo

### 🎨 Dashboard Moderno
- ✅ Interface responsiva e intuitiva
- ✅ Estatísticas em tempo real
- ✅ Gráficos interativos (Chart.js)
- ✅ Navegação por abas
- ✅ Atualização automática

### 🔌 API REST
- ✅ Endpoints RESTful completos
- ✅ Documentação Swagger
- ✅ Tratamento de erros robusto
- ✅ Transações para operações críticas

## 📡 Endpoints da API

### Clientes
| Método | Endpoint | Descrição |
|--------|----------|------------|
| `GET` | `/clientes` | Listar todos os clientes |
| `GET` | `/clientes/{id}` | Buscar cliente por ID |
| `POST` | `/clientes` | Criar novo cliente |
| `PUT` | `/clientes/{id}` | Atualizar cliente |
| `DELETE` | `/clientes/{id}` | Deletar cliente |

### Notificações
| Método | Endpoint | Descrição |
|--------|----------|------------|
| `GET` | `/notificacoes` | Listar todas as notificações |

### Demonstração de Padrões
| Método | Endpoint | Padrão Demonstrado |
|--------|----------|-------------------|
| `GET` | `/notificacoes/factory` | Factory Method |
| `GET` | `/notificacoes/builder` | Builder Pattern |
| `GET` | `/notificacoes/adapter` | Adapter Pattern |
| `GET` | `/notificacoes/template` | Template Method |

## 🧪 Testes

Execute os testes unitários:
```bash
./mvnw test
```

## 📊 Exemplo de Uso

### 1. Cadastrar Cliente
```json
POST /clientes
{
  "nome": "João Silva",
  "endereco": {
    "cep": "28960-000",
    "logradouro": "Rua das Flores",
    "bairro": "Centro",
    "localidade": "Iguaba Grande",
    "uf": "RJ"
  }
}
```

### 2. Sistema Automático
- ✅ Endereço complementado via ViaCEP
- ✅ Notificação de boas-vindas criada
- ✅ Dashboard atualizado em tempo real
- ✅ Gráfico de evolução atualizado

## 🤝 Contribuição

1. 🍴 Faça um Fork do projeto
2. 🌿 Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. 💾 Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. 🚀 Push para a branch (`git push origin feature/AmazingFeature`)
5. 🔄 Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Leandro Macedo Leal Moreira**

- 🐙 GitHub: [@leandromlmoreira](https://github.com/leandromlmoreira)

## 🙏 Agradecimentos

- **Digital Innovation One** pela oportunidade de aprendizado
- **Comunidade Spring** pelo framework robusto e bem documentado
- **ViaCEP** pela API gratuita de endereços
- **Todos os padrões de projeto** que tornam o código mais limpo, manutenível e escalável

## 📈 Roadmap

- [ ] Suporte a múltiplos bancos de dados
- [ ] Sistema de autenticação e autorização
- [ ] Cache distribuído com Redis
- [ ] Logs estruturados com ELK Stack
- [ ] Testes de integração automatizados
- [ ] CI/CD com GitHub Actions
- [ ] Containerização com Docker
- [ ] Deploy em cloud (AWS/Azure/GCP)

---

⭐ **Se este projeto te ajudou, considere dar uma estrela!** ⭐
