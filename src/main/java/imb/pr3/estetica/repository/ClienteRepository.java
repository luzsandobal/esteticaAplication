package imb.pr3.estetica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.estetica.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
		public List<Cliente> findByActivo(boolean activo);
}
