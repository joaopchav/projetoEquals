# Projeto Equals 

# Porta Padrão: 8081 | Banco: H2 em memória (jdbc:h2:mem:equalsdb)

Esta solução lê o arquivo posicional fornecido pelo meio de pagamento, interpreta
cada venda, persiste no banco e expõe relatórios via API REST. 
O design favorece fácil evolução para novas bandeiras e novos campos.

# Requisitos de execução

Java JDK: 17
Maven: 3.8


# Clonagem / download do projeto

1. Escolha ou crie uma pasta: 
cd ~/workspace

2. Clone sua fork ou baixe o ZIP git clone <seu‑repo>: 

cd projetoEquals   # (raiz onde está o pom.xml)

# Build & execução

1. Compilar e rodar testes: 
mvn clean install

2. Iniciar aplicação Spring Boot: 
mvn spring-boot:run


# Importando Vendas

curl -F "file=@Arquivo_Estagio_Desenvolvimento.txt" ^

http://localhost:8081/vendas/upload

# Endpoints principais

GET /: Health‑check simples

POST /vendas/upload: Upload de arquivo TXT

GET /vendas: Lista todas as vendas

GET /vendas/bandeira?bandeira=VISA: Filtra por bandeira

GET /vendas/parcelas?qtde=03: Filtra por quantidade de parcelas

GET /vendas/periodo?inicio=2025‑01‑01&fim=2025‑06‑30: Filtra por dataEvento

GET /vendas/{id}: Busca venda específica


# Testes automatizados

mvn test

# O que é testado?

VendaRepositoryTest — persiste e consulta por bandeira.

LeitorArquivoServiceTest — importa o TXT de exemplo.

VendaControllerTest — garante status e JSON da rota /vendas.

-------------------------------------------------------------------------------

# João Paulo Chaves |  Desafio Técnico Equals  | Spring Boot + Maven