package tn.esprit.consomitounsi.modal;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table (name="produits")
public class Produit {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long idProduit;
    
    @ManyToOne
    @JoinColumn(name="idCategory",referencedColumnName = "idCategory", updatable = false)
    private Category categoryProduit;
    
    private Double note;
    private Double prixProduit;
    private String nomProduit;
    @Pattern(regexp="[6][1][9]\\d\\d\\d\\d\\d", 
    	    message="Le digicode doit commencer par 619 et contient 8 chiffre")
    private  String codeabarraProduit ;
    
    @OneToOne(mappedBy = "produit")
    private UploadFile image ;
    
    @CreationTimestamp
    private Date dateCreation;
    
 
	private  int quantity;
    
    public Produit() {
		super();
		
	}
   


	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", categoryProduit=" + categoryProduit + ", prixProduit="
				+ prixProduit + ", nomProduit=" + nomProduit + ", codeabarraProduit=" + codeabarraProduit + ", image="
				+ image + ", dateCreation=" + dateCreation + "]";
	}


	public Long getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}
	public Category getCategoryProduit() {
		return categoryProduit;
	}
	public void setCategoryProduit(Category categoryProduit) {
		this.categoryProduit = categoryProduit;
	}
	public Double getPrixProduit() {
		return prixProduit;
	}
	public void setPrixProduit(Double prixProduit) {
		this.prixProduit = prixProduit;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getCodeabarraProduit() {
		return codeabarraProduit;
	}
	public void setCodeabarraProduit(String codeabarraProduit) {
		this.codeabarraProduit = codeabarraProduit;
	}

	public UploadFile getImage() {
		return image;
	}

	public void setImage(UploadFile image) {
		this.image = image;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public Double getNote() {
		return note;
	}



	public void setNote(Double note) {
		this.note = note;
	}
	
	
    
	}