// Interface que vai prover todos os metodos de acesso a dados da determinada Entidade

package gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Nao é necessario colocar
@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
