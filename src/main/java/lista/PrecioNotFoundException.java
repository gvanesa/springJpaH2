package lista;

class PrecioNotFoundException extends RuntimeException {

    PrecioNotFoundException(Long id){
        super("Could not find precio" + id);
    }
}
