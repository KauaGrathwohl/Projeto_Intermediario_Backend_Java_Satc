# Projeto intermediário Back End - SATC

O projeto "Mostra Tempo" tem como objetivo fornecer dados climáticos em tempo real para qualquer cidade especificada. Para realizar consultas, não é necessário possuir uma conta. No entanto, usuários registrados podem adicionar suas cidades favoritas e acessar facilmente as condições meteorológicas dessas localidades sempre que desejarem.

A API do OpenWeatherMap é utilizada para obter as informações meteorológicas. Os dados são armazenados temporariamente em um banco de dados H2 em memória do Spring. Para gerenciar as exceções que possam surgir tanto na aplicação quanto na API, foi implementado um interceptador de exceções. Esse interceptador trata todas as exceções e fornece um retorno apropriado ao usuário.

Configurações essenciais, como a URL e a chave da API, estão armazenadas no arquivo application.properties. Adicionalmente, na pasta resources, há um arquivo JSON disponível para ser importado no Postman. Esse arquivo permite que os usuários visualizem e testem todas as requisições de maneira prática e eficiente.

Rotas:

Usuário:
api/usuarios/criar - para criar uma conta
api/usuarios/{usuarioId}/favoritar/{cidadeId} - para favoritar uma cidade

Cidade:
api/cidades - buscar todas cidades

Consulta tempo:
api/consultas-tempo/consultar - para consultar o tempo (parametro 'usuarioId' é opcional)
api/consultas-tempo/consultar-favoritos-por-usuario/{usuarioId} - consulta o tempo de todas cidades favoritas
api/consultas-tempo/usuario/{usuarioId} - para visualizar todas consultas realizadas por determinado usuario

Ajuda:
api/ajuda - mostra o estudante e nome do projeto
