/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

@NamedQueries({
    @NamedQuery(name= "Autor.findAll", query="SELECT a FROM Autor a"),
    @NamedQuery(name="Autor.findFilter", query="SELECT a FROM Autor a WHERE a.nome LIKE :filtro")
})
@Entity
public class Autor implements Serializable {

    @ManyToMany(mappedBy = "autores")
    private List<Livro> livross;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String nacionalidade;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datanasc;
    private String foto;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Livro> getLivross() {
        return livross;
    }

    public void setLivross(List<Livro> livross) {
        this.livross = livross;
    }
    
    
}
