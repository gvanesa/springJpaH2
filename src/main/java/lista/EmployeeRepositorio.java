package lista;


import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositorio extends JpaRepository<Employee, Long> {

}