## CursoAPIRestSpringBoot - UDEMY
> Repository responsible for storing my studies on Spring boot and its technologies.


# Statefull (Com estado)
> Mantém o estado do cliente no servidor. Por exemplo:um aplicativo de carrinho de compras online mantém um estado de sessão para cada cliente, rastreando os itens no carrinho, o histórico de compras etc.

# Steteless (Sem estado)
> Não mantém o estado. O protocolo HTTP é considerado statless, pois cada solicitação étradada de forma independete

# Json Web Token(JWT) é o mais utilizado.
> Header: cabeçalho especifica o algoritmo usado para assinar o token
> Payload: O payload contém as informações que são codificadas no token, como dados do usuário, data de criação e expiração do token, permissões de acesso, etc.
> Signature: A assinatura é gerada usando uma chave secreta, de escolha do desenvolvedor, com o objetivo de verificar se o token é válido. Essa é a única parte do token que não poderá ser visualizada pelo cliente, justamente por ser a parte criptografada.
> O JWT é contido, ou seja, todas as informações necessárias para verificar a autenticação está no próprio token.

