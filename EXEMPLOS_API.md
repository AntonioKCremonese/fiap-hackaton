# Exemplos de Requisições - API de Agendamentos

## 🏥 Pacientes (Patients)

### Criar Paciente
```bash
POST http://localhost:8080/patients
Content-Type: application/json

{
  "name": "João Silva",
  "cpf": "12345678901",
  "email": "joao.silva@email.com",
  "phone": "(11) 99999-9999",
  "birthDate": "1990-01-15",
  "address": "Rua das Flores, 123 - São Paulo, SP"
}
```

### Atualizar Paciente
```bash
PUT http://localhost:8080/patients/{id}
Content-Type: application/json

{
  "name": "João Silva Santos",
  "email": "joao.santos@email.com",
  "phone": "(11) 98888-8888",
  "address": "Av. Paulista, 1000 - São Paulo, SP"
}
```

### Buscar Paciente
```bash
GET http://localhost:8080/patients/{id}
```

### Listar Todos os Pacientes
```bash
GET http://localhost:8080/patients
```

### Deletar Paciente
```bash
DELETE http://localhost:8080/patients/{id}
```

---

## 🩺 Especialidades (Specialties)

### Criar Especialidade
```bash
POST http://localhost:8080/specialties
Content-Type: application/json

{
  "name": "Cardiologia",
  "description": "Especialidade médica dedicada ao diagnóstico e tratamento de doenças do coração"
}
```

### Outras Especialidades de Exemplo
```json
{
  "name": "Ortopedia",
  "description": "Especialidade que cuida do sistema musculoesquelético"
}

{
  "name": "Neurologia",
  "description": "Especialidade que trata do sistema nervoso"
}

{
  "name": "Pediatria",
  "description": "Especialidade focada na saúde de crianças e adolescentes"
}
```

---

## 👨‍⚕️ Médicos (Doctors)

### Criar Médico
```bash
POST http://localhost:8080/doctors
Content-Type: application/json

{
  "name": "Dr. Carlos Alberto",
  "crm": "CRM/SP 123456",
  "email": "carlos.alberto@hospital.com",
  "phone": "(11) 3333-4444",
  "specialty": {
    "specialtyId": "uuid-da-especialidade-aqui"
  }
}
```

### Listar Médicos por Especialidade
```bash
GET http://localhost:8080/doctors?specialtyId={specialtyId}
```

---

## 🔬 Tipos de Exames (Exam Types)

### Criar Tipo de Exame
```bash
POST http://localhost:8080/exam-types
Content-Type: application/json

{
  "name": "Hemograma Completo",
  "description": "Exame de sangue que avalia células sanguíneas",
  "preparation": "Jejum de 8 horas"
}
```

### Outros Exames de Exemplo
```json
{
  "name": "Raio-X de Tórax",
  "description": "Exame de imagem para avaliar pulmões e coração",
  "preparation": "Não requer preparo especial"
}

{
  "name": "Ultrassom Abdominal",
  "description": "Exame de imagem para avaliar órgãos abdominais",
  "preparation": "Jejum de 6 horas e beber 4 copos de água 1 hora antes"
}

{
  "name": "Ressonância Magnética",
  "description": "Exame detalhado de imagem",
  "preparation": "Retirar objetos metálicos"
}
```

---

## 📅 Agendamentos (Appointments)

### Criar Agendamento de CONSULTA
```bash
POST http://localhost:8080/appointments
Content-Type: application/json

{
  "patientId": "uuid-do-paciente",
  "doctorId": "uuid-do-medico",
  "appointmentType": "CONSULTATION",
  "appointmentDate": "2026-02-15T14:30:00",
  "notes": "Consulta de rotina - Cardiologia"
}
```

### Criar Agendamento de EXAME
```bash
POST http://localhost:8080/appointments
Content-Type: application/json

{
  "patientId": "uuid-do-paciente",
  "examTypeId": "uuid-do-tipo-de-exame",
  "appointmentType": "EXAM",
  "appointmentDate": "2026-02-20T09:00:00",
  "notes": "Exame de sangue - jejum realizado"
}
```

### Atualizar Agendamento
```bash
PUT http://localhost:8080/appointments/{id}
Content-Type: application/json

{
  "doctorId": "uuid-do-novo-medico",
  "appointmentDate": "2026-02-16T15:00:00",
  "notes": "Reagendado a pedido do paciente"
}
```

### Atualizar Status do Agendamento
```bash
PUT http://localhost:8080/appointments/{id}/status
Content-Type: application/json

{
  "status": "CONFIRMED"
}
```

**Status Disponíveis:**
- `SCHEDULED` - Agendado
- `CONFIRMED` - Confirmado
- `CANCELLED` - Cancelado
- `COMPLETED` - Concluído

### Buscar Agendamentos por Paciente
```bash
GET http://localhost:8080/appointments?patientId={patientId}
```

### Buscar Agendamentos por Médico
```bash
GET http://localhost:8080/appointments?doctorId={doctorId}
```

### Buscar Agendamentos por Status
```bash
GET http://localhost:8080/appointments?status=SCHEDULED
GET http://localhost:8080/appointments?status=CONFIRMED
GET http://localhost:8080/appointments?status=COMPLETED
```

### Deletar Agendamento
```bash
DELETE http://localhost:8080/appointments/{id}
```

---

## ⚠️ Exemplos de Validações e Erros

### Erro: CPF Duplicado
```json
{
  "timestamp": "2026-01-28T11:00:00",
  "status": 409,
  "message": "Já existe um paciente cadastrado com este CPF"
}
```

### Erro: Agendamento Inválido
```json
{
  "timestamp": "2026-01-28T11:00:00",
  "status": 400,
  "message": "Tipo de agendamento inválido. Médico é obrigatório para consultas e tipo de exame é obrigatório para exames"
}
```

### Erro: Validação de Campos
```json
{
  "name": "O nome é obrigatório",
  "email": "O email deve ser válido",
  "cpf": "O CPF deve ter entre 11 e 14 caracteres"
}
```

---

## 🔄 Fluxo Completo de Uso

### 1️⃣ Cadastrar Especialidade
```bash
POST /specialties
{ "name": "Cardiologia", "description": "..." }
# Resposta: { "id": "spec-uuid-123", ... }
```

### 2️⃣ Cadastrar Médico
```bash
POST /doctors
{ "name": "Dr. Carlos", "crm": "...", "specialty": { "specialtyId": "spec-uuid-123" } }
# Resposta: { "id": "doc-uuid-456", ... }
```

### 3️⃣ Cadastrar Paciente
```bash
POST /patients
{ "name": "João Silva", "cpf": "...", ... }
# Resposta: { "id": "pat-uuid-789", ... }
```

### 4️⃣ Criar Agendamento
```bash
POST /appointments
{ 
  "patientId": "pat-uuid-789",
  "doctorId": "doc-uuid-456",
  "appointmentType": "CONSULTATION",
  "appointmentDate": "2026-02-15T14:30:00"
}
# Resposta: { "id": "app-uuid-012", "status": "SCHEDULED", ... }
```

### 5️⃣ Confirmar Agendamento
```bash
PUT /appointments/app-uuid-012/status
{ "status": "CONFIRMED" }
```

### 6️⃣ Concluir Agendamento
```bash
PUT /appointments/app-uuid-012/status
{ "status": "COMPLETED" }
```

---

## 📝 Notas Importantes

1. **IDs UUID**: Todos os IDs são gerados automaticamente no formato UUID
2. **Datas**: Use formato ISO 8601: `YYYY-MM-DDTHH:mm:ss`
3. **Status**: Agendamentos concluídos não podem ser alterados ou deletados
4. **Tipo de Agendamento**: 
   - CONSULTATION requer `doctorId`
   - EXAM requer `examTypeId`