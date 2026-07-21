/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 */

package gof.controller;

import gof.model.Cliente;
import gof.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    // Sempre vai prover os recursos e os componentes do Spring como um Singleton
    @Autowired
    private ClientService clientService;

    // Busca todos
    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        return ResponseEntity.ok(clientService.buscarTodos());
    }

    // Busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.buscarPorId(id));
    }

    // Insere um novo cliente
    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente){
        clientService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    // Atualiza um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        clientService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    // Remove um cliente existente
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> remover(@PathVariable Long id){
        clientService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
