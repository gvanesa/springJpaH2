package lista;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class PrecioController {

    private final PrecioRepositorio repositorio;
    private final PrecioModelAssembler assembler;

    PrecioController(PrecioRepositorio repositorio, PrecioModelAssembler assembler) {
        this.repositorio = repositorio;
        this.assembler = assembler;

    }

    @RequestMapping("/")
    public @ResponseBody
    String index() {
        return "Bienvenido a SuperList el proyecto mas simple y mas largo de la historia!";
    }

    @GetMapping("/precio")
    CollectionModel<EntityModel<Precio>> all() {

        List<EntityModel<Precio>> precios =
                repositorio.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

       /* List<EntityModel<Precio>> precios = repositorio.findAll().stream()
                .map(precio -> EntityModel.of(precio,
                        linkTo(methodOn(PrecioController.class).one(precio.getId())).withSelfRel(),
                        linkTo(methodOn(PrecioController.class).one((long) precio.getValor())).withSelfRel(),
                        linkTo(methodOn(PrecioController.class).one(precio.getId())).withRel(precio.getArticulo()),
                        linkTo(methodOn(PrecioController.class).all()).withRel("ppppp"))).collect(Collectors.toList());
*/
        return CollectionModel.of(precios, linkTo(methodOn(
                PrecioController.class).all()).withSelfRel());
    }

    // List<Precio> all()
    //{return repositorio.findAll();}

    @PostMapping("/precio")
    ResponseEntity<?> newPrecio(@RequestBody Precio newPrecio) {

        EntityModel<Precio> entityModel =
                assembler.toModel(repositorio.save(newPrecio));
         return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                 .body(entityModel);


        // return repositorio.save(newPrecio);
    }

    @GetMapping("/precio/{id}")
    EntityModel<Precio> one(@PathVariable Long id) {
        Precio precio = repositorio.findById(id)
                .orElseThrow(() -> new PrecioNotFoundException(id));

        return assembler.toModel(precio);
       /* return EntityModel.of(precio, //
                linkTo(methodOn(PrecioController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PrecioController.class).all()).withRel("Precios"));
*/

    }

    @PutMapping("/precio/{id}")
    Precio replacePrecio(@RequestBody Precio newPrecio, @PathVariable Long id) {
        return repositorio.findById(id)
                .map(precio -> {
                    precio.setArticulo(newPrecio.getArticulo());
                    precio.setValor(newPrecio.getValor());
                    return repositorio.save(precio);
                })
                .orElseGet(() -> {
                    newPrecio.setId(id);
                    return repositorio.save(newPrecio);
                });
    }

    @DeleteMapping("/precio/{id}")
    void deletePrecio(@PathVariable Long id) {
        repositorio.deleteById(id);
    }

}

