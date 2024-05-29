package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.Categoria;
import com.example.model.Producto;
import com.example.repository.ICategoria;
import com.example.repository.IProducto;


@Controller
public class Controlador {
	
	@Autowired
    private ICategoria categoriaRepo;

    @Autowired
    private IProducto productoRepo;

    @GetMapping("/")
    public String index(Model model) {
    	Categoria categoria = new Categoria();
        categoria.setNombre("Electrónicos");
        categoria.setDescripcion("Celulares y dispositivos");

        categoriaRepo.save(categoria);

        Producto producto1 = new Producto();
        producto1.setNombre("Smartphone");
        producto1.setDescripcion("Llevate el ultimo cell con Snapdragon #10");
        producto1.setPrecio(699.99);
        producto1.setUrlImagen("https://as2.ftcdn.net/v2/jpg/01/06/38/11/1000_F_106381187_BTwzKKXGYaeCpAk92GQOyX4uYGCV4Ai5.jpg");
        producto1.setCategoria(categoria);

        Producto producto2 = new Producto();
        producto2.setNombre("Laptop");
        producto2.setDescripcion("Mejor procesador con la mejor refrigeración");
        producto2.setPrecio(1299.99);
        producto2.setUrlImagen("https://compusistemasperu.com/wp-content/uploads/2023/03/HPG8.jpg");
        producto2.setCategoria(categoria);

        //Producto producto3 = new Producto();
        //producto3.setNombre("Cellphone");
        //producto3.setDescripcion("Llevate el ultimo cell con Snapdragon #10");
        //producto3.setPrecio(699.99);
        //producto3.setUrlImagen("https://as2.ftcdn.net/v2/jpg/01/06/38/11/1000_F_106381187_BTwzKKXGYaeCpAk92GQOyX4uYGCV4Ai5.jpg");
        //producto3.setCategoria(categoria);
        
        productoRepo.save(producto1);
        productoRepo.save(producto2);
        //productoRepo.save(producto3);
    	
    	
        model.addAttribute("productos", productoRepo.findAll());
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "index";
    }

    @GetMapping("/categoria")
    public String categoria(Long id, Model model) {
        Categoria categoria = categoriaRepo.findById(id).orElse(null);
        
        model.addAttribute("categoria", categoria);
        model.addAttribute("productos", productoRepo.findByCategoriaId(categoria.getId()));
        
        return "categoria";
    }
}
