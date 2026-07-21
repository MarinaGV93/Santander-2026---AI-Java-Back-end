package gof.singleton;// Singleton "preguiçoso". No primeiro momento, nao disponibiliza a instancia para o usuario

public class SingletonLazy {

    private static SingletonLazy instancia;

    // Garantir que ninguem, externamente, possa instanciar. Precisa ser privado
    private SingletonLazy() {
        // Não é necessário, mas é uma boa prática
        super();
    }

    // Garantir que a instância seja exposta.
    // Vai retornar ele mesmo (Singleton.SingletonLazy)
    public static SingletonLazy getInstancia() {
        // Verifica se a instância é nula. Se for, cria uma nova instância
        if (instancia == null) {
            instancia = new SingletonLazy();
        }
        // Retorna a instancia
        return instancia;
    }
}
