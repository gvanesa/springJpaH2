package lista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo")
public class CompraController {

    @Autowired
    CompraRepository compraRepository;


    @RequestMapping(value = "/insertcompra", method = RequestMethod.POST)
    public @ResponseBody
    String insertCompra(@RequestParam String descripcion
            , @RequestParam Estados estados) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Compra n = new Compra();
        n.setDescripcion(descripcion);
        n.setEstado(estados);
        compraRepository.save(n);
        return "Saved";
    }
    
    @RequestMapping(value = "/compras", method = RequestMethod.GET)
    public <Compra> Iterable<lista.Compra> getCompras(){
        //return compraService.getAllCompra();
        return compraRepository.findAll();
    }


}

   
