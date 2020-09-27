package lista;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("No puedo encontrar precio" + id);
    }
}

