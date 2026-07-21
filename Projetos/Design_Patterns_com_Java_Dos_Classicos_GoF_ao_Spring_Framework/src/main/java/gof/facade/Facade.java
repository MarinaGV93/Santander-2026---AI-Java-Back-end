// Realize operações para consolidar/expor uma interface mais simples para uma operação que é mais complexa
// Criar uma interface mais simples para consumo das informações de um subsistema (subsistema 1 e 2)

package gof.facade;

import subsistema1.crm.crmService;
import subsistema2.cep.cepApi;

public class Facade {

    /*Imagine que tem uma base de clientes que deseja fazer uma migração desses clientes para uma nova base ou incrementar a base atual.
    A base do tem nome e CEP e quer consumir uma API de CEP para que a partir do CEP que ja tem, ela consuma a API e consolide as informações como endereço....
     */

    public void migrarCliente(String nome, String cep) {
        // Integrações com os subsistemas (API)

        // Para usar os atributos que so tem nos subsistemas
        String cidade = cepApi.getInstance().recuperarCidade(cep);
        String estado = cepApi.getInstance().recuperarEstado(cep);

        crmService.gravarCliente(nome, cep, cidade, estado);
    }
}
