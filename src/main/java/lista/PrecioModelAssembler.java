package lista;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class PrecioModelAssembler  implements RepresentationModelAssembler<Precio, EntityModel<Precio>> {

    @Override
    public EntityModel<Precio> toModel(Precio precio){
        return EntityModel.of(precio,
                linkTo(methodOn(PrecioController.class).one(precio.getId())).withSelfRel(),
                linkTo(methodOn(PrecioController.class).all()).withRel("Precio"));
    }

}
