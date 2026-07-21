package gof.singleton;// Singleton "apressado".
// Disponibiliza a instância imediatamente

public class SingletonEager {

    // A instância é criada imediatamente
    private static SingletonEager instancia = new SingletonEager();

    // Garantir que ninguem, externamente, possa instanciar. Precisa ser privado
    private SingletonEager() {
        // Não é necessário, mas é uma boa prática
        super();
    }

    // Garantir que a instância seja exposta.
    // Vai retornar ele mesmo (Singleton.SingletonEager)
    public static SingletonEager getInstancia() {
        // Retorna a instancia
        return instancia;
    }
}
