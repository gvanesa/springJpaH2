package lista;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class CompraModelAssembler implements RepresentationModelAssembler<Compra, EntityModel<Compra>> {

@Override
    public EntityModel<Compra> toModel(Compra compra){



    EntityModel<Compra> compraModel =  EntityModel.of(compra,
            linkTo(methodOn(CompraController.class).one(compra.getIdCompra())).withSelfRel(),
            linkTo(methodOn(CompraController.class).all()).withRel("Compra"));


    if (compra.getEstado() == Estados.IN_PROGRESS){
        compraModel.add(linkTo(methodOn(CompraController.class).cancel(compra.getIdCompra())).withRel("Cancel"));
        compraModel.add(linkTo(methodOn(CompraController.class).complete(compra.getIdCompra())).withRel("Complete"));
        compraModel.add(linkTo(methodOn(CompraController.class).progress(compra.getIdCompra())).withRel("Progress"));

    }

    return compraModel;
}
}



