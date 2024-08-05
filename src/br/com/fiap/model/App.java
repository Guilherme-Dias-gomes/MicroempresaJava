package br.com.fiap.model;

import br.com.fiap.model.Categoria;
import br.com.fiap.model.Produto;
import Repository.CategoriaColletionRepository;
import Repository.ProdutoColletionRepository;
import View.CategoriaView;
import View.Opcao;
import View.OpcaoView;
import View.ProdutoView;

import javax.swing.*;
import java.util.List;

public class App {
    public static void main(String[] args) {

        List<Categoria> categorias = CategoriaColletionRepository.findAll();

        Opcao opcao = null;

        do {
            opcao = OpcaoView.select();
            switch (opcao) {
                case CADASTRAR_CATEGORIA -> cadastrarCategoria();
                case CADASTRAR_PRODUTO -> cadastrarproduto();
                case ALTERAR_PRODUTO -> alterarproduto();
                case CONSULTAR_PRODUTO_POR_ID -> consultarprodutoporid();
                case CONSULTAR_PRODUTO_POR_CATEGORIA -> consultarprodutoporcategoria();
            }
        } while (opcao != Opcao.ENCERRAR_SISTEMA);
    }

    private static void consultarprodutoporcategoria() {
        Categoria categoria = CategoriaView.select(null);
        List<Produto> produtos = ProdutoColletionRepository.findByCategoria(categoria);
        if (produtos.size() == 0)
            JOptionPane.showMessageDialog(null, "Não encontramos produtos cadastrados para a categoria " + categoria.getNome());
        produtos.forEach(System.out::println);
        produtos.forEach(ProdutoView::show);
    }

    private static void consultarprodutoporid() {
        Long id = 0l;
        do {
            try {
                id = Long.parseLong(JOptionPane.showInputDialog("Informe o id do produto"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Id inválido!");
            }
        } while (id <= 0);

        Produto p = ProdutoColletionRepository.findById(id);
        if (p != null) {
            ProdutoView.show(p);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }
    }

    private static void alterarproduto() {

        Produto produto = ProdutoView.select();
        ProdutoView.update(produto);
    }

    private static void cadastrarproduto() {

        Produto produto = ProdutoView.form();
        ProdutoColletionRepository.save(produto);
        ProdutoView.sucesso(produto);
    }

    public static void cadastrarCategoria() {
        CategoriaView view = new CategoriaView();
        Categoria categoria = view.form();
        CategoriaColletionRepository.save(categoria);
        view.sucesso(categoria);
    }

}