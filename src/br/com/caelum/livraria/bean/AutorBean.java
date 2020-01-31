package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Integer autorId;

	private Autor autor = new Autor();

	public String formLivro() {
		System.out.println("Chamanda do formulário do Livro");
		return "livro?faces-redirect=true";
	}

	public String formAutor() {
		System.out.println("Chamada do formulario do Autor");
		return "autor?faces-redirect=true";
	}

	public void carregarAutorPeloId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		this.autor = new Autor();

		return "autor?faces-redirect=true";
	}

	public void remover(Autor autor) {
		System.out.println("Removendo o autor " + autor.getNome());
		System.out.println(autor.getLivros().isEmpty());
		System.out.println();
		if (autor.getLivros().isEmpty()) {
			new DAO<Autor>(Autor.class).remove(autor);
			System.out.println("Autor " + autor.getNome() + " removido");
		} else {
			System.out.println("Não foi possível remover o autor " + autor.getNome());
		}
	}

	public void carregar(Autor autor) {
		System.out.println("Carregando o autor " + autor.getNome() + " no formulario");
		this.autor = autor;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public Autor getAutor() {
		return autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

}
