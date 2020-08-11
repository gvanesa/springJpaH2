package lista;

class PrecioNotFoundException extends RuntimeException {

    PrecioNotFoundException(Long id){
        super("No puedo encontrar precio" + id);
    }
}
