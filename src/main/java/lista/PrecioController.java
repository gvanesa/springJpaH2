package lista;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class PrecioController {

    private final PrecioRepositorio repositorio;

    PrecioController(PrecioRepositorio repositorio){
        this.repositorio = repositorio;
    }

    @RequestMapping("/")
    public @ResponseBody
    String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/precio")
    List<Precio> all()
    {return repositorio.findAll();}

    @PostMapping("/precio")
    Precio newPrecio(@RequestBody Precio newPrecio){
        return repositorio.save(newPrecio);
    }

    @GetMapping("/precio/{id}")
    Precio one(@PathVariable Long id){
        return repositorio.findById(id)
                .orElseThrow(() -> {
                    return new PrecioNotFoundException(id);
                });
    }
    @PutMapping("/precio/{id}")
    Precio replacePrecio(@RequestBody Precio newPrecio, @PathVariable Long id){
        return repositorio.findById(id)
                .map(precio -> {
                    precio.setArticulo(newPrecio.getArticulo());
                    precio.setValor(newPrecio.getValor());
                    return repositorio.save(precio);
                })
                    .orElseGet(()->{
                        newPrecio.setId(id);
                        return repositorio.save(newPrecio);
                    });
    }
    @DeleteMapping("/precio/{id}")
    void deletePrecio(@PathVariable Long id){
        repositorio.deleteById(id);
    }

}

