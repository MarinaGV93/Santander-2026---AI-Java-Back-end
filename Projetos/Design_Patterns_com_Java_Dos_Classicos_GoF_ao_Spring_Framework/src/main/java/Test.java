import gof.facade.Facade;
import gof.singleton.SingletonEager;
import gof.singleton.SingletonLazy;
import gof.singleton.SingletonLazyHolder;
import gof.strategy.*;

public class Test {

    public static void main(String[] args) {

        // Instanciando os Singletons

        System.out.println("\nSingletons:");

        System.out.println("------------------------------");

        // Acessa pela classe porque é privado
        SingletonLazy lazy = SingletonLazy.getInstancia();

        System.out.println("Instancia do SingletonLazy 1: " + lazy);
        lazy = SingletonLazy.getInstancia();
        System.out.println("Instancia do SingletonLazy 2: " + lazy);

        System.out.println("------------------------------");

        SingletonEager eager = SingletonEager.getInstancia();

        System.out.println("Instancia do SingletonEager 1: " + eager);
        eager = SingletonEager.getInstancia();
        System.out.println("Instancia do SingletonEager 2: " + eager);

        System.out.println("------------------------------");

        SingletonLazyHolder lazyHolder = SingletonLazyHolder.getInstancia();

        System.out.println("Instancia do SingletonLazyHolder 1: " + lazyHolder);
        lazyHolder = SingletonLazyHolder.getInstancia();
        System.out.println("Instancia do SingletonLazyHolder 2: " + lazyHolder);

        System.out.println("------------------------------");

        // Instanciando os Strategy

        System.out.println("\nStrategy:");

        System.out.println("------------------------------");

        // Criar os comportamentos
        Comportamento normal = new ComportamentoNormal();
        Comportamento agressivo = new ComportamentoAgressivo();
        Comportamento defensivo = new ComportamentoDefensivo();

        // Criar o robo
        Robo robo = new Robo();

        //Definir um comportamento
        robo.setComportamento(normal);

        // Mover o robo
        robo.mover();
        robo.mover();

        System.out.println("------------------------------");

        // Mover o robo com comportamento agressivo
        robo.setComportamento(agressivo);
        robo.mover();
        robo.mover();
        robo.mover();

        System.out.println("------------------------------");

        // Mover o robo com comportamento defensivo
        robo.setComportamento(defensivo);
        robo.mover();

        System.out.println("------------------------------");

        // Instanciando os Facade

        System.out.println("\nFacade:");

        System.out.println("------------------------------");

        // Criar o facade
        Facade facade = new Facade();
        facade.migrarCliente("Marina", "123456789");

        System.out.println("------------------------------");
    }


}
