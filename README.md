# frete

-----------------------------------------------------------------------

#Deploy 

Para efetuar o deploy da aplicação é necessário inserir o jar do primefaces poseidon 1.0.0, o jar encontra-se no projeto. No pacote: br.com.frete.docs.

Deve-se alterar o arquivo persistence.xml para inserir os configuraçõs do banco de dados.

Após essa configuração pode-se gerar o arquivo .war da aplicação.

Por fim fazer o deploy em um servidor tomcat 8.

A versão do Java 1.8

------------------------------------------------------------------------

#Modo de usar

O sistema Conta com dois tipos de usuários, o Participante e o usuário Transportadora.

O Usuário Transportadora deve cadastrar uma planilha e a mesma poderá ser usuada para calcular  o frete.

O Usuário Participante não tem nenhume função especifica no sistema podendo unicamente fazer login e visualizar seus dados básicos.

O usuário pode acessar o sistema de forma anônima, ou seja sem login. Podendo com isso calcular o preço de um ou mais fretes.


------------------------------------------------------------------------

#Informações

O sistema consome services dos correios para confirmação de endereço e do google para calcular a distância entre dois pontos.
