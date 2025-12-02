# 🎫 HelpDesk - Sistema de Gerenciamento de Chamados

![Angular](https://img.shields.io/badge/Angular-12-red?style=for-the-badge&logo=angular)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.5-green?style=for-the-badge&logo=spring)
![Java](https://img.shields.io/badge/Java-11-orange?style=for-the-badge&logo=java)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![TypeScript](https://img.shields.io/badge/TypeScript-4.3-blue?style=for-the-badge&logo=typescript)

Sistema completo de gerenciamento de chamados técnicos (HelpDesk) desenvolvido como projeto de Estágio Supervisionado 2 do curso de Sistemas de Informação da Unicatólica.

## 📋 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura](#-arquitetura)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [API Endpoints](#-api-endpoints)
- [Autores](#-autores)
- [Licença](#-licença)

## 🎯 Sobre o Projeto

O **HelpDesk** é um sistema web completo para gerenciamento de chamados técnicos, permitindo que clientes abram solicitações de suporte e técnicos realizem o atendimento de forma organizada e eficiente.

### Principais Características:

✅ Sistema de autenticação com JWT
✅ Controle de acesso baseado em perfis (ADMIN, TÉCNICO, CLIENTE)
✅ CRUD completo de Técnicos, Clientes e Chamados
✅ Interface moderna e responsiva com Angular Material
✅ API RESTful documentada
✅ Validações de formulários e tratamento de erros
✅ Sistema de notificações Toast
✅ Filtros e paginação de dados

## 🚀 Funcionalidades

### Para Administradores:
- ✅ Gerenciar técnicos (criar, editar, listar, deletar)
- ✅ Gerenciar clientes (criar, editar, listar, deletar)
- ✅ Gerenciar chamados (criar, editar, visualizar)
- ✅ Acesso total ao sistema

### Para Técnicos:
- ✅ Gerenciar clientes (criar, editar, listar, deletar)
- ✅ Gerenciar chamados (criar, editar, visualizar)
- ✅ Visualizar todos os chamados

### Para Clientes:
- ✅ Criar chamados
- ✅ Visualizar seus chamados
- ✅ Acompanhar status dos chamados

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 11**
- **Spring Boot 2.5**
- **Spring Security** (JWT)
- **Spring Data JPA**
- **MySQL 8.0**
- **H2 Database** (Testes)
- **Maven**
- **Hibernate**
- **Bean Validation**

### Frontend
- **Angular 12**
- **TypeScript 4.3**
- **Angular Material**
- **RxJS**
- **NgxToastr**
- **Auth0 Angular JWT**
- **Ngx-Mask**

## 🏗️ Arquitetura

O projeto segue uma arquitetura **MVC (Model-View-Controller)** no backend e **Component-Based** no frontend:

```
helpdeskapp/
├── backend/              # API Spring Boot
│   ├── config/          # Configurações (Security, CORS)
│   ├── domain/          # Entidades JPA
│   ├── repositories/    # Repositórios Spring Data
│   ├── services/        # Lógica de negócio
│   ├── resources/       # Controllers REST
│   ├── security/        # JWT, UserDetails
│   └── dtos/           # Data Transfer Objects
│
└── frontend/            # App Angular
    ├── components/     # Componentes da UI
    ├── services/       # Serviços HTTP
    ├── models/         # Interfaces TypeScript
    ├── guards/         # Route Guards
    ├── interceptors/   # HTTP Interceptors
    └── auth/          # Autenticação
```

## 📦 Pré-requisitos

Antes de começar, você precisará ter instalado:

- **Java JDK 11+**
- **Node.js 14+** e **npm**
- **MySQL 8.0+**
- **Angular CLI 12** (`npm install -g @angular/cli@12`)
- **Maven** (ou use o wrapper incluído)

## 🔧 Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/helpdeskapp.git
cd helpdeskapp
```

### 2. Configurar o Backend

#### 2.1. Configure o banco de dados MySQL

Crie um banco de dados:

```sql
CREATE DATABASE helpdesk;
```

#### 2.2. Configure as credenciais

Edite o arquivo `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/helpdesk
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

#### 2.3. Execute o backend

```bash
cd backend
./mvnw spring-boot:run
```

O backend estará rodando em: `http://localhost:8080`

### 3. Configurar o Frontend

#### 3.1. Instale as dependências

```bash
cd frontend/helpdeskprojectfrontend
npm install
```

#### 3.2. Configure a API URL

Edite o arquivo `frontend/helpdeskprojectfrontend/src/app/config/api.config.ts`:

```typescript
export const API_CONFIG = {
  baseUrl: 'http://localhost:8080'
}
```

#### 3.3. Execute o frontend

```bash
npm start
```

O frontend estará rodando em: `http://localhost:4200`

## 📁 Estrutura do Projeto

### Backend (Spring Boot)

```
backend/src/main/java/com/queirozruan/helpdesk/
├── config/
│   ├── SecurityConfig.java
│   ├── StartupBanner.java
│   ├── DevConfig.java
│   └── TestConfig.java
├── domain/
│   ├── Pessoa.java
│   ├── Tecnico.java
│   ├── Cliente.java
│   ├── Chamado.java
│   └── enums/
    └── dtos/
│      ├── TecnicoDTO.java
│      ├── ClienteDTO.java
│      └── ChamadoDTO.java
├── repositories/
│   ├── PessoaRepository.java
│   ├── TecnicoRepository.java
│   ├── ClienteRepository.java
│   └── ChamadoRepository.java
├── services/
│   ├── TecnicoService.java
│   ├── ClienteService.java
│   ├── ChamadoService.java
│   └── DBService.java
├── controllers/
│   ├── TecnicoController.java
│   ├── ClienteController.java
│   └── ChamadoController.java
├── security/
│   ├── JWTUtil.java
│   ├── JWTAuthenticationFilter.java
│   ├── JWTAuthorizationFilter.java
│   └── UserSS.java
```

### Frontend (Angular)

```
frontend/helpdeskprojectfrontend/src/app/
├── components/
│   ├── nav/
│   ├── home/
│   ├── login/
│   ├── header/
│   ├── tecnico/
│   │   ├── tecnico-list/
│   │   ├── tecnico-create/
│   │   ├── tecnico-update/
│   │   └── tecnico-delete/
│   ├── cliente/
│   │   ├── cliente-list/
│   │   ├── cliente-create/
│   │   ├── cliente-update/
│   │   └── cliente-delete/
│   └── chamado/
│       ├── chamado-list/
│       ├── chamado-create/
│       ├── chamado-update/
│       └── chamado-read/
├── services/
│   ├── auth.service.ts
│   ├── tecnico.service.ts
│   ├── cliente.service.ts
│   └── chamado.service.ts
├── models/
│   ├── tecnico.ts
│   ├── cliente.ts
│   ├── chamado.ts
│   └── credenciais.ts
├── auth/
│   ├── auth.guard.ts
├── interceptors/
│   └── auth.interceptor.ts
```

## 📡 API Endpoints

### Autenticação

```http
POST /login
Content-Type: application/json

{
  "email": "bill@mail.com",
  "senha": "123"
}
```

### Técnicos

```http
GET    /tecnicos          # Listar todos
GET    /tecnicos/{id}     # Buscar por ID
POST   /tecnicos          # Criar técnico
PUT    /tecnicos/{id}     # Atualizar técnico
DELETE /tecnicos/{id}     # Deletar técnico
```

### Clientes

```http
GET    /clientes          # Listar todos
GET    /clientes/{id}     # Buscar por ID
POST   /clientes          # Criar cliente
PUT    /clientes/{id}     # Atualizar cliente
DELETE /clientes/{id}     # Deletar cliente
```

### Chamados

```http
GET    /chamados          # Listar todos
GET    /chamados/{id}     # Buscar por ID
POST   /chamados          # Criar chamado
PUT    /chamados/{id}     # Atualizar chamado
```

## 🔑 Credenciais de Teste

O sistema vem com usuários pré-cadastrados para teste:

| Email | Senha | Perfil |
|-------|-------|--------|
| bill@mail.com | 123 | ADMIN | TECNICO |
| linus@mail.com | 123 | CLIENTE |

## 🧪 Executando Testes

### Backend

```bash
cd backend
./mvnw test
```

### Frontend

```bash
cd frontend/helpdeskprojectfrontend
ng test
```

## 📦 Build para Produção

### Backend

```bash
cd backend
./mvnw clean package
java -jar target/helpdesk-0.0.1-SNAPSHOT.jar
```

### Frontend

```bash
cd frontend/helpdeskprojectfrontend
ng build --prod
```

Os arquivos estarão em `frontend/helpdeskprojectfrontend/`


## 👨‍💻 Autores

- **Ruan Queiroz** - [GitHub](https://github.com/queirozzruan)

Desenvolvido como projeto de Estágio Supervisionado 2 - Unicatólica Quixadá

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 📞 Contato

Ruan Queiroz - [LinkedIn](https://linkedin.com/in/queirozzruan)

---

⭐ Se este projeto te ajudou, considere dar uma estrela!

**Desenvolvido com ❤️ por Ruan Queiroz - Unicatólica de Quixadá**
