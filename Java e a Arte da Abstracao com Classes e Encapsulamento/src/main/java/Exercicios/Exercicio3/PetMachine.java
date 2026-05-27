package Exercicios.Exercicio3;

public class PetMachine {
    private boolean clean = true;
    private int water = 30;
    private int shampoo = 10;

    //Receber o pet
    private Pet pet;



    //Dar banho no pet
    public void takeAShower(){
        if (this.pet == null){
            System.out.println("Coloque o pet na máquina para iniciar o banho\n");
            return;
        }

        //Consumo de cada banho
        this.water -= 10;
        this.shampoo -= 2;

        //Pet limpo
        pet.setClean(true);

        System.out.println("O pet " + pet.getName() + " está limpo\n");
    }

    //Adicionar água
    public void addWater(){
        if (water == 30){
            System.out.println("A capacidade de água da máquina está no máximo\n");
            return;
        }
        water  += 2;
    }

    //Adicionar shampoo
    public void addShampoo(){
        if (shampoo == 10){
            System.out.println("A capacidade de shampoo da máquina está no máximo\n");
            return;
        }
        shampoo  += 2;
    }

    //Verificar nível de agua no shampoo

    public int getWater() {
        return water;
    }

    public int getShampoo() {
        return shampoo;
    }

    //Verificar se tem pet no banho
    public boolean hasPet(){
        return pet != null;
    }

    //Colocar 1 pet por vez na máquina
    public void setPet(Pet pet) {

        //Se a máquina não estiver limpa
        if (!this.clean){
            System.out.println("A máquina está suja, é necessário limpá-la\n");
            return;
        }

        //Se tiver pet na máquina
        if (hasPet()){
            System.out.println("O pet " + this.pet.getName() + " está na máquina neste momento\n");
            return;
        }
        this.pet = pet;
        System.out.println("O pet " + pet.getName() + " foi colocado na máquina\n");
    }

    //Retirar o pet da máquina
    public void removePet(){
        //Se o pet estiver limpo, a máquina estara limpa
        this.clean = this.pet.isClean();

        System.out.println("O pet " + this.pet.getName() + " foi retirado\n");
        this.pet = null;
    }

    //Limpar a máquina
    public void wash(){
        this.water -= 10;
        this.shampoo -= 2;
        this.clean = true;
        System.out.println("A máquina foi limpa\n");
    }
}
