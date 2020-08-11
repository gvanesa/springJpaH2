package lista;

class CompraNotFoudnException extends RuntimeException{

    CompraNotFoudnException(long id){
        super("No puedo encontrar precio" + id);
    }
}
