package gof.singleton;// Encapsula a instância em uma classe estatica interna

public class SingletonLazyHolder {

    // Classe interna
    // Tem a função de encapsular a instância
    private static class InstanceHolder {
        // A instância é criada imediatamente
        public static final SingletonLazyHolder instancia = new SingletonLazyHolder();
    }

    // A instância é criada imediatamente
    private static SingletonLazyHolder instancia = new SingletonLazyHolder();

    // Garantir que ninguem, externamente, possa instanciar. Precisa ser privado
    private SingletonLazyHolder() {
        // Não é necessário, mas é uma boa prática
        super();
    }

    // Garantir que a instância seja exposta.
    // Vai retornar ele mesmo (Singleton.SingletonEager)
    public static SingletonLazyHolder getInstancia() {
        // Retorna a instancia
        // Acessar a instância pelo Holder
        return InstanceHolder.instancia;
    }
}
