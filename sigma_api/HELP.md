# Configurando o ambiente

Para poder iniciar o projeto, é necessário a instalação do Intellij IDEA e do docker.

## Scripts

- Para criar um container MySql utilizando o docker:
    - ``` bash
      docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_USER=sigma_dba -e MYSQL_PASSWORD=sigma_dba123 -e MYSQL_DATABASE=sigma_dev mysql:8
      ```
    - Nesse caso, o docker irá criar um container com o mysql 8. A senha do usuário root será "password", também será
      criado um usuário chamado "sigma_dba" e sua senha será "sigma_dba123". O "sigma_dba" vai ter acesso ao banco
      "sigma_dev" que será criado com esse container.
  