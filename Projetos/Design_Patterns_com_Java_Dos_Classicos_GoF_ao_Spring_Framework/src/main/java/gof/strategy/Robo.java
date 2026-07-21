// Contexto

package gof.strategy;

public class Robo {

    // Estrategia do comportamento do robo
    private Comportamento comportamento;

    // Para permitir a alteracao da estrategia do comportamento do robo
    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    // Para permitir que o robo se mova de acordo com a estrategia definida
    public void mover() {
        // Delega o comportamento ao objeto comportamento
        comportamento.mover();
    }
}
