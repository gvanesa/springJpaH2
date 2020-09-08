package lista.controllers;

import lista.entities.Producto;
import lista.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository repository;

    @GetMapping("/list")
    private String getAll(Model model){
        model.addAttribute("productos", repository.findAll());
        return "producto_list";
    }

    @GetMapping(path = {"/add", "edit/{id}"})
    private String addForm(@PathVariable("id") Optional<Long> id, Model model){
        if(id.isPresent()){
            model.addAttribute("producto", repository.findById(id.get()));
        }else{
            model.addAttribute("producto", new Producto());
        }
        return "add_edit_producto";
    }

    @PostMapping("/addEdit")
    private String insertOrUpdate(Producto producto){
        if(producto.getId()==0) {
            repository.save(producto);
        }else{
            Optional<Producto> productoOptional = repository.findById(producto.getId());
            if(productoOptional.isPresent()){
                Producto editProducto = productoOptional.get();
                editProducto.setNombre(producto.getNombre());
                editProducto.setPrecio(producto.getPrecio());
                repository.save(editProducto);
            }
        }
        return "redirect:/producto/list";
    }
    @GetMapping("/delete/{id}")
    private String deleteProducto(@PathVariable("id") Long id){
        Optional<Producto> producto = repository.findById(id);
        if(producto.isPresent()){
            repository.delete(producto.get());
        }else{
            System.err.println("not found");
        }
        return "redirect:/producto/list";
    }
}
