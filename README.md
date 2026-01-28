# FIAP Hackaton - Medical Appointment System

MVP para sistema de agendamento de consultas e exames médicos, desenvolvido como parte do FIAP Hackaton.

## 📋 Descrição

Sistema que oferece aos pacientes mais facilidade e agilidade ao marcar consultas e exames médicos, reduzindo filas e tempo de espera.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- MySQL 8.0
- Flyway (Migrations)
- Maven
- Docker & Docker Compose
- Lombok
- ModelMapper

## 🏗️ Arquitetura

O projeto segue o padrão MVC (Model-View-Controller) com as seguintes camadas:

- **Model**: Entidades do banco de dados
- **Repository**: Interface de acesso aos dados (JPA)
- **Service**: Lógica de negócio
- **Controller**: Endpoints REST API
- **DTO**: Objetos de transferência de dados (Input/Output)
- **Exception**: Tratamento centralizado de exceções

## 📦 Estrutura do Banco de Dados

### Tabelas:
- **patient**: Cadastro de pacientes
- **specialty**: Especialidades médicas
- **doctor**: Cadastro de médicos
- **exam_type**: Tipos de exames disponíveis
- **appointment**: Agendamentos de consultas e exames

## 🔧 Instalação e Execução

### Pré-requisitos
- Java 17
- Maven 3.9+
- Docker e Docker Compose

### Executar com Docker Compose

```bash
docker-compose up --build
```

A aplicação estará disponível em: `http://localhost:8080`

### Executar localmente

1. Configure o banco de dados MySQL
2. Atualize `application.properties` com suas credenciais
3. Execute:

```bash
mvn clean install
mvn spring-boot:run
```

## 📚 Endpoints da API

### Patients (Pacientes)
- `GET /patients` - Listar todos os pacientes
- `GET /patients/{id}` - Buscar paciente por ID
- `POST /patients` - Criar novo paciente
- `PUT /patients/{id}` - Atualizar paciente
- `DELETE /patients/{id}` - Deletar paciente

### Specialties (Especialidades)
- `GET /specialties` - Listar todas as especialidades
- `GET /specialties/{id}` - Buscar especialidade por ID
- `POST /specialties` - Criar nova especialidade
- `PUT /specialties/{id}` - Atualizar especialidade
- `DELETE /specialties/{id}` - Deletar especialidade

### Doctors (Médicos)
- `GET /doctors` - Listar todos os médicos
- `GET /doctors?specialtyId={id}` - Listar médicos por especialidade
- `GET /doctors/{id}` - Buscar médico por ID
- `POST /doctors` - Criar novo médico
- `PUT /doctors/{id}` - Atualizar médico
- `DELETE /doctors/{id}` - Deletar médico

### Exam Types (Tipos de Exames)
- `GET /exam-types` - Listar todos os tipos de exames
- `GET /exam-types/{id}` - Buscar tipo de exame por ID
- `POST /exam-types` - Criar novo tipo de exame
- `PUT /exam-types/{id}` - Atualizar tipo de exame
- `DELETE /exam-types/{id}` - Deletar tipo de exame

### Appointments (Agendamentos)
- `GET /appointments` - Listar todos os agendamentos
- `GET /appointments?patientId={id}` - Listar agendamentos por paciente
- `GET /appointments?doctorId={id}` - Listar agendamentos por médico
- `GET /appointments?status={status}` - Listar agendamentos por status
- `GET /appointments/{id}` - Buscar agendamento por ID
- `POST /appointments` - Criar novo agendamento
- `PUT /appointments/{id}` - Atualizar agendamento
- `PUT /appointments/{id}/status` - Atualizar status do agendamento
- `DELETE /appointments/{id}` - Deletar agendamento

## 📝 Exemplos de Request

### Criar Paciente
```json
POST /patients
{
  "name": "João Silva",
  "cpf": "12345678901",
  "email": "joao@email.com",
  "phone": "11999999999",
  "birthDate": "1990-01-15",
  "address": "Rua Example, 123"
}
```

### Criar Especialidade
```json
POST /specialties
{
  "name": "Cardiologia",
  "description": "Especialidade médica focada no coração"
}
```

### Criar Médico
```json
POST /doctors
{
  "name": "Dr. Maria Santos",
  "crm": "CRM123456",
  "email": "maria@hospital.com",
  "phone": "11988888888",
  "specialty": {
    "specialtyId": "specialty-uuid-here"
  }
}
```

### Criar Tipo de Exame
```json
POST /exam-types
{
  "name": "Hemograma Completo",
  "description": "Exame de sangue completo",
  "preparation": "Jejum de 8 horas"
}
```

### Criar Agendamento (Consulta)
```json
POST /appointments
{
  "patientId": "patient-uuid-here",
  "doctorId": "doctor-uuid-here",
  "appointmentType": "CONSULTATION",
  "appointmentDate": "2026-02-15T14:30:00",
  "notes": "Consulta de rotina"
}
```

### Criar Agendamento (Exame)
```json
POST /appointments
{
  "patientId": "patient-uuid-here",
  "examTypeId": "exam-type-uuid-here",
  "appointmentType": "EXAM",
  "appointmentDate": "2026-02-20T09:00:00",
  "notes": "Exame de sangue"
}
```

### Atualizar Status do Agendamento
```json
PUT /appointments/{id}/status
{
  "status": "CONFIRMED"
}
```

**Status disponíveis:**
- `SCHEDULED` - Agendado
- `CONFIRMED` - Confirmado
- `CANCELLED` - Cancelado
- `COMPLETED` - Concluído

## 🎯 Funcionalidades

- ✅ Cadastro completo de pacientes
- ✅ Cadastro de especialidades médicas
- ✅ Cadastro de médicos vinculados a especialidades
- ✅ Cadastro de tipos de exames
- ✅ Agendamento de consultas médicas
- ✅ Agendamento de exames
- ✅ Controle de status dos agendamentos
- ✅ Filtros de busca (por paciente, médico, status)
- ✅ Validações de dados
- ✅ Tratamento de erros centralizado

## 👥 Equipe

Projeto desenvolvido para o FIAP Hackaton 2026

## 📄 Licença

Este projeto está sob a licença MIT.