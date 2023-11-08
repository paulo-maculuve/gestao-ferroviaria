package com.estacao.ferroviaria.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Column(nullable = false, length = 100)		
	   private String nome;
	    @Column(nullable = false, unique = true, length = 100)
	    private String email;
	    @Column(length = 15)
	    private String telefone;
	    @Column(nullable = false, length = 200)
	    private String password;
	     @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinTable(name = "users_roles",
	        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	    )
	     
	     private Set<Roles> roles;


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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Set<Roles> getRoles() {
			return roles;
		}

		public void setRoles(Set<Roles> roles) {
			this.roles = roles;
		}


}
