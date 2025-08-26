package br.com.aweb.sistema_produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.aweb.sistema_produto.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
    @Query("select u from Product u where u.name = ?1")
    Product findByName(String emailAddress);

}
