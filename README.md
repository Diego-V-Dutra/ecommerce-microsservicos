# E-commerce com Microsserviços

Mini e-commerce construído com arquitetura de microsserviços, como projeto de estudo e portfólio. Cada serviço tem seu próprio banco de dados e roda em container.

> 🚧 Projeto em desenvolvimento.

## Stack

- **Backend:** Java + Spring Boot (Maven)
- **Frontend:** React *(a ser desenvolvido)*
- **Banco de dados:** MySQL (um banco por serviço)
- **Infra:** Docker e Docker Compose
- **Ambiente de desenvolvimento:** WSL2 (Ubuntu)

## Arquitetura

| Serviço | Porta | Banco | Status |
|---------|-------|-------|--------|
| catalog-service | 8081 | catalog_db | ✅ Pronto |
| order-service | — | orders_db | 🔜 Em andamento |

## Estrutura do repositório

```
.
├── catalog-service/     # Serviço de catálogo de produtos (Spring Boot)
├── docker/
│   └── mysql-init/      # Scripts de criação dos bancos
└── docker-compose.yml   # Sobe a infraestrutura (MySQL)
```

## Como rodar

Pré-requisitos: Docker, Docker Compose, Java 21 e Maven.

**1. Clone o repositório**

```bash
git clone https://github.com/Diego-V-Dutra/ecommerce-microsservicos.git
cd ecommerce-microsservicos
```

**2. Suba a infraestrutura (MySQL)**

```bash
docker compose up -d
```

**3. Rode o catalog-service**

```bash
cd catalog-service
./mvnw spring-boot:run
```

**4. Rode o order-service**

```bash
cd order-service
./mvnw spring-boot:run
```

## Roadmap

- [x] catalog-service
- [ ] order-service
- [ ] Comunicação entre serviços
- [ ] Frontend em React
