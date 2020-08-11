package lista;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class CompraController {
    private final CompraRepositorio compraRepositorio;
    private final CompraModelAssembler assembler;

    CompraController(CompraRepositorio compraRepositorio, CompraModelAssembler assembler){
        this.compraRepositorio = compraRepositorio;
        this.assembler = assembler;
    }

    @GetMapping("/compras")
    CollectionModel<EntityModel<Compra>> all(){

        List<EntityModel<Compra>> compra = compraRepositorio.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(compra,
                linkTo(methodOn(CompraController.class).all()).withSelfRel());
    }

    @PostMapping("/compras")
    ResponseEntity<EntityModel<Compra>> newOrder(@RequestBody Compra compra) {

        compra.setEstado(Estados.IN_PROGRESS);
        Compra newOrder = compraRepositorio.save(compra);

        return ResponseEntity //
                .created(linkTo(methodOn(CompraController.class).one(newOrder.getIdCompra())).toUri()) //
                .body(assembler.toModel(newOrder));
    }

    @PostMapping("/compras/add")
    ResponseEntity<?> newCompra(@RequestBody Compra newCompra) {

        EntityModel<Compra> entityModel =
                assembler.toModel(compraRepositorio.save(newCompra));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

        // return repositorio.save(newPrecio);
    }

    @DeleteMapping("/compras/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) {
        Compra compra = compraRepositorio.findById(id).orElseThrow(() -> new CompraNotFoudnException(id));

        if (compra.getEstado() == Estados.IN_PROGRESS){
            compra.setEstado(Estados.COMPLETADO);
            return ResponseEntity.ok(assembler.toModel(compraRepositorio.save(compra)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("You can't complete an order that is in the " + compra.getEstado() + " status"));

    }

    @PutMapping("/compras/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) {
        Compra compra = compraRepositorio.findById(id)
                .orElseThrow(() -> new CompraNotFoudnException(id));

        if (compra.getEstado() == Estados.IN_PROGRESS){
            compra.setEstado(Estados.COMPLETADO);
            return ResponseEntity.ok(assembler.toModel(compraRepositorio.save(compra)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("You can't complete an order that is in the " + compra.getEstado() + " status"));

    }

    @PutMapping("/compras/{id}/progress")
    ResponseEntity<?> progress(@PathVariable Long id) {
        Compra compra = compraRepositorio.findById(id)
                .orElseThrow(() -> new CompraNotFoudnException(id));

        if (compra.getEstado() == Estados.COMPLETADO){
            compra.setEstado(Estados.IN_PROGRESS);
            return ResponseEntity.ok(assembler.toModel(compraRepositorio.save(compra)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("You can't complete an order that is in the " + compra.getEstado() + " status"));

    }

    @GetMapping("/compras/{id}")
    EntityModel<Compra> one(@PathVariable Long id) {
        Compra compra = compraRepositorio.findById(id)
                .orElseThrow(() -> new PrecioNotFoundException(id));

        return assembler.toModel(compra);
       /* return EntityModel.of(precio, //
                linkTo(methodOn(PrecioController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PrecioController.class).all()).withRel("Precios"));
*/

    }
}
