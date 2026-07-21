// Coração do sistema

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 */

package gof.service.impl;

import gof.model.Cliente;
import gof.model.ClienteRepository;
import gof.model.Endereco;
import gof.model.EnderecoRepository;
import gof.service.ClientService;
import gof.service.ViaCepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

// Anotação @Service indica que essa classe é um componente de serviço do Spring, permitindo que seja gerenciada pelo contêiner de injeção de dependência.
@Service
public class ClienteServiceImpl implements ClientService {
    // Singleton: Injetar os componentes do Spring com @Autowired
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    // Iterable - padrão de projeto
    @Override
    public Iterable<Cliente> buscarTodos() {
        LOGGER.info("---- Buscando todos os clientes: ");

        // Verificar se a lista de clientes está vazia
        boolean lista = clienteRepository.findAll().iterator().hasNext();
        if (!lista) {
            LOGGER.info("---- Nenhum cliente encontrado.");
        } else {
            LOGGER.info("---- Clientes encontrados: ");
            return clienteRepository.findAll();
        }
        return null;
    }

    // Optional - pode ou nao existir
    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        // Se o cliente existir, retorna o cliente, caso contrário, retorna null
        Cliente result = cliente.orElse(null);
        if (result == null){
            LOGGER.info("---- Cliente não encontrado para o ID: {}", id);
        }
        LOGGER.info("---- Cliente encontrado: {}", cliente.get().getNome());
        return result;
    }

    @Override
    public void inserir(Cliente cliente) {
        if (cliente == null) {
            LOGGER.info("---- Cliente nulo não pode ser inserido.");
            return;
        }
        LOGGER.info("---- Cliente inserido com sucesso: {}", cliente.getNome());
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Buscar Cliente por ID, caso exista
        Optional<Cliente> clienteBD = clienteRepository.findById(id);
        if (clienteBD.isPresent()){
            // Verificar se o endereço do cliente ja existe (pelo CEP)
            // Caso nao exista, integrar com o ViaCEP e persistir o retorno
            // Alterar cliente, vinculando o endereço (novo ou existente)
            LOGGER.info("---- Cliente encontrado para atualização: {}", clienteBD.get().getNome());
            salvarClienteComCep(cliente);
        } else {
            // Caso nao exista, lançar um erro ou tratar de acordo com a regra de negócio
            LOGGER.info("---- Cliente não encontrado para atualização.");
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar cliente por ID
        boolean existe = clienteRepository.existsById(id);
        if (!existe) {
            LOGGER.info("---- Cliente não encontrado para deleção: id {}", id);
            return;
        }
        LOGGER.info("---- Cliente deletado com sucesso: {}", id);
        clienteRepository.deleteById(id);
    }

    // Metodo que vai ser usado mais de uma vez
    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o endereço do cliente ja existe (pelo CEP)
        String cep = cliente.getEndereco().getCep();

        // Busca no banco. Se existir, retorna o endereço. Se nao existir, retorna algo
        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    // Pesquisar o CEP no ViaCEP
                    Endereco novoEndereco = viaCepService.consultarCep(cep);
                    if (novoEndereco == null) {
                        throw new IllegalStateException("ViaCep retornou null para o CEP=" + cep);
                    }
                    // Salvar o novo endereço no banco
                    LOGGER.info("---- Novo endereço encontrado para o CEP: {}", cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });

        // Passar o endereço ao cliente e salvar
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
