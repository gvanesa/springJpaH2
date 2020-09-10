package lista.controllers;

import lista.entities.Compra;
import lista.entities.Producto;
import lista.repository.CompraRepository;
import lista.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListaComprasController {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProductoRepository repository;

    @GetMapping
    public String index(Model modelo, Compra compra, Producto producto){

        modelo.addAttribute("compras",compraRepository.findAll());
        modelo.addAttribute("productos", repository.findAll());
        return "index";
    }
}
