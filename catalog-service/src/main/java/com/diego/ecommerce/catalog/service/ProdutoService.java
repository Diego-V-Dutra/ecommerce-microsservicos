package com.diego.ecommerce.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.diego.ecommerce.catalog.domain.Categoria;
import com.diego.ecommerce.catalog.domain.Produto;
import com.diego.ecommerce.catalog.dto.ProdutoRequest;
import com.diego.ecommerce.catalog.dto.ProdutoResponse;
import com.diego.ecommerce.catalog.repository.CategoriaRepository;
import com.diego.ecommerce.catalog.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

/*  @Service é uma anotação do Spring que indica que a classe é um serviço, ou seja, uma classe que contém a lógica de negócio da aplicação.
*   @RequiredArgsConstructor é uma anotação do Lombok que gera um construtor com argumentos para todos os campos finais (final) da classe. Isso é útil para injeção de dependências, pois permite que as dependências sejam passadas como argumentos do construtor.
*
*/
@Service
@RequiredArgsConstructor 
public class ProdutoService {
    private final ProdutoRepository produtos;
    private final CategoriaRepository categorias;
    
    /*  Método listar é responsável por listar os produtos cadastrados no sistema, podendo filtrar por categoria. Ele recebe como parâmetros o ID da categoria (categoriaId) e um objeto 
    *  @Transactional é uma anotação do Spring que indica que o método deve ser executado dentro de uma transação. O atributo readOnly = true indica que a transação é apenas de leitura, ou seja, não irá modificar o banco de dados. Isso pode melhorar a performance e evitar bloqueios desnecessários no banco de dados.  
    *  pagina.map(ProdutoResponse::de) é uma expressão que utiliza o método map da interface Page para transformar cada objeto Produto da página em um objeto ProdutoResponse. O operador :: é uma referência a método, que permite passar o método de como argumento para o map. Isso significa que para cada produto na página, o método de será chamado para criar um produtoResponse correnspondente. O resultado é uma nova página contendo objetos ProdutoResponse, que será retornada pelo método listar.
    */
    @Transactional(readOnly = true)
    public Page<ProdutoResponse> listar(Long categoriaId, Pageable pageable){
        Page<Produto> pagina = (categoriaId == null)
            ? produtos.findByAtivoTrue(pageable)
            : produtos.findByAtivoTrueAndCategoriaId(categoriaId, pageable);
            return pagina.map(ProdutoResponse::de);
    }

    /* Método buscarPorId é responsável por buscar um produto pelo seu ID. Ele recebe como parâmetro o ID do produto(id) e retorna um objeto ProdutoResponse correspondente ao produto encontrado. 
        Se o produto não for encontrado, ele lança uma exceção ResponseStatusException com o status HTTP 404(NOT_FOUND) e uma mensagem de erro "Produto não encontrado". 
        O método utiliza o método privado obter para buscar o produto no banco de dados, e em seguida converte o produto em um ProdutoResponse utilizando o método de da classe ProdutoResponse.
     */
    @Transactional(readOnly = true)
    public ProdutoResponse buscarPorId(Long id){
        return ProdutoResponse.de(obter(id));
    }

    /* Método criar é responsável por criar um novo produto no sistema. Ele recebe como parâmetro um objeto ProdutoRequest(req) que contém os dados do produto a ser criado, e retorna um objeto ProdutoResponse correspondente ao produto criado.
        O método cria um novo objeto Produto, preenche seus campos com os dados do ProdutoRequest utilizando o método privado preencher, e em seguida salva o produto no banco de dados utilizando o repositório produtos. Por fim, ele converte o produto salvo em um ProdutoResponse utilizando o método "de" da classe ProdutoResponse e retorna o resultado.
     */
    @Transactional
    public ProdutoResponse criar(ProdutoRequest req){
        Produto produto = new Produto();
        preencher(produto,req);
        return ProdutoResponse.de(produtos.save(produto));
    }

    /* Método desativar é responsável por desativar um produto no sistema. Ele recebe como parâmetro o ID do produto(id) e não retorna nenhum valor.
        O método utiliza o método privado obter para buscar o produto no banco de dados, e em seguida altera o campo ativo do produto para false, indicando que o produto está desativado.
        @Transactional é uma anotação do Spring que indica que o método deve ser executado dentro de uma transação. Isso garante que todos os passos do método sejam executados de forma atômica, ou seja, ou todos são concluídos com sucesso ou nenhum é aplicado. Isso é importante para manter a consistência dos dados no banco de dados.
    */
    @Transactional 
    public void desativar(Long id){
        obter(id).setAtivo(false);
    }

    /* Método obter é um método privado que busca um produto no banco de dados pelo seu ID. Ele recebe como parâmetro o ID do produto(id) e retorna um objeto Produto correspondente ao produto encontrado. 
    *  O método utiliza o repositório produtos para buscar o produto no banco de dados utilizando o método findById. se o produto não for encontrado, ele lança uma exceção ResponseStatusException com o status HTTP 404(NOT_FOUND) e uma mensagem de erro "Produto não encontrado".
    */
    private Produto obter(Long id){
        return produtos.findById(id).orElseThrow(() 
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    /* Método preencher é um método privado que preenche os campos de um objeto Produto com os dados de um objeto ProdutoRequest. Ele recebe como parâmetros o objeto Produto(produto) e o objeto ProdutoRequest(req).
        O método busca a categoria correspondente ao ID da categoria presente no ProdutoRequest utilizando o repositório categorias. Se a categoria não for encontrada, ele lança uma exceção ResponseStatusException com o status HTTP 400(BAD_REQUEST) e uma mensagem de erro "Categoria não encontrada".
        Em seguida, o método preenche os campos do objeto Produto com os valores do produtoRequest, incluindo o nome, descrição, preço, estoque e a categoria encontrada. Isso permite que o objeto Produto seja criado ou atualizado com os dados fornecidos pelo usuário.
    */
    private void preencher(Produto produto, ProdutoRequest req){
        Categoria categoria = categorias.findById(req.categoriaId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada"));
        produto.setNome(req.nome());
        produto.setDescricao(req.descricao());
        produto.setPreco(req.preco());
        produto.setEstoque(req.estoque());
        produto.setCategoria(categoria);
    }
    
}