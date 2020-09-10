package lista.controllers;

import lista.entities.Compra;
import lista.entities.Producto;
import lista.repository.CompraRepository;
import lista.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller // This means that this class is a Controller
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProductoRepository repository;


    @GetMapping
    public String index(Model modelo, Compra compra, Producto producto){
        modelo.addAttribute( "compra", new Compra());
        modelo.addAttribute( "producto", new Producto());
        modelo.addAttribute("compras",compraRepository.findAll());
        modelo.addAttribute("productos", repository.findAll());
        return "index";
    }


    //@RequestMapping(value = "/list", method = RequestMethod.GET)
    @GetMapping("/list")
    private String getAll(Model model){
        model.addAttribute("compras", compraRepository.findAll());
        model.addAttribute("productos", repository.findAll());
        return "compra_list";
    }

    @GetMapping(path = {"/add", "edit/{idCompra}"})
    public String addForm(@PathVariable("idCompra") Optional<Long> idCompra, Model model){
        if(idCompra.isPresent()){
            model.addAttribute("compra", compraRepository.findById(idCompra.get()));
           model.addAttribute("productos", repository.findAll());

        }else{
            model.addAttribute("compra", new Compra());
            model.addAttribute("productos", repository.findAll());

        }
        return "add_edit_compra";
    }

    @PostMapping("/addEdit") // Map ONLY POST Requests
    private String insertOrUpdate(Compra compra){
        //String precio;
        if(compra.getIdCompra()==0){
            compraRepository.save(compra);
            //precio = compra.getDescripcion();
        }else{
            Optional<Compra> compraOptional = compraRepository.findById(compra.getIdCompra());
            if(compraOptional.isPresent()){
                Compra editCompra = compraOptional.get();
                editCompra.setDescripcion(compra.getDescripcion());
                editCompra.setCantidad(compra.getCantidad());
                editCompra.setEstado(compra.getEstado());
                //editCompra.setEstado(compra.getEstado());
            //creo que es acá donde los debo relacionar pero no estoy segura
                compraRepository.save(editCompra);
            }
        }
        return "redirect:/compra/list";
    }


    @GetMapping("/delete/{idCompra}")
    private String deleteCompra(@PathVariable("idCompra") Long idCompra){
        Optional<Compra> compra = compraRepository.findById(idCompra);
        if(compra.isPresent()){
            compraRepository.delete(compra.get());
        }else{
            System.err.println("not found");
        }
        return "redirect:/compra/list";
    }

}

   
