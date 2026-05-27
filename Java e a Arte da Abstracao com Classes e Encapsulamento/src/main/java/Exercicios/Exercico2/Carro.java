package Exercicios.Exercico2;

public class Carro {
    private boolean ligado;
    private int velocidade;
    private int marcha;

    public Carro() {

        //Desligado
        this.ligado = false;
        this.marcha = 0;
        this.velocidade = 0;
    }

    public void ligar(){
        this.ligado = true;
        System.out.println("Carro ligado");
    }

    public void desligar(){
        if (this.marcha == 0 && this.velocidade == 0){
            this.ligado = false;
            System.out.println("Carro desligado");
        }else {
            System.out.println("Para desligar, o carro deve estar parado e em ponto morto");
        }
    }

    public void acelerar(){
        if (!ligado){
            System.out.println("O carro esta desligado");
            return;
        }
        if (marcha == 0){
            System.out.println("O  carro está em ponto morto. Engate uma marcha para acelerar");
            return;
        }

        //1 = 20; 2 = 40;  3  = 60...
        int limiteMax = marcha * 20;

        if (velocidade < limiteMax && velocidade < 120){

            //Acrescenta 1 na velocidade
            velocidade++;
            System.out.println("Velocidade atual: " + velocidade + "km/h");
        } else {
            System.out.println("Limite da " + marcha + "ª marcha atingida ( " + limiteMax + " km/h");
        }
    }

    public void desacelerar(){
        if (!ligado) return;

        if (velocidade > 0){

            //Tira 1 na velocidade
            velocidade--;
            System.out.println("Velocidade atual: " + velocidade + " km/h");
        }
    }

    public void trocarMarcha(int novaMarcha){
        if (!ligado) return;

        //Não pode pular marcha
        if (Math.abs(novaMarcha - this.marcha) > 1){
            System.out.println("Não pode pular marchas!");
            return;
        }

        //Regra de limites de velocidade
        int min = (novaMarcha == 0) ? 0 : (novaMarcha - 1) * 20 + 1;
        int max = novaMarcha * 20;

        String txt = "Marcha alterada: ";

        if (novaMarcha == 0 && velocidade == 0){
            this.marcha = 0;
            System.out.println(txt + "Ponto Morto");
        } else if (velocidade >= min && velocidade <= max) {
            this.marcha = novaMarcha;
            System.out.println(txt + marcha + "ª");
        } else {
            System.out.println("Velocidade incompatível com a " + novaMarcha + "ª marcha");
        }
    }

    public void virar(String direcao){
        if (!ligado) return;

        if (velocidade >= 1 && velocidade <= 40){
            System.out.println("Virando para a " + direcao);
        } else {
            System.out.println("Só é seguro virar entre 1km/h e 40km/h.");
        }
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    public int getMarcha() {
        return marcha;
    }

    public boolean estaLigado(){
        return ligado;
    }
}
