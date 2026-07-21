/**
 * Client HTTP, criado via <b>OpenFeign</b>, para o consumo da API do
 * <b>ViaCEP</b>.
 *
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 */

package gof.service;

import gof.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// Client HTTP para consumo da API do ViaCEP. URL base do determinado client (tem no site do viaCep)
@FeignClient(name = "viaCep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    // Mét odo para buscar o endereço via CEP. Pode usar o GetMapping
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json")
    // Converte a resposta da API em um objeto Endereco
    Endereco consultarCep(@PathVariable("cep") String cep);
}
