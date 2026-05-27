package domain;

public non-sealed class Salesman extends Employee{
    private double percentPerSold;
    private double soldAmount;

    //Precisa ter o construtor
    public Salesman(String code,
                    String name,
                    String address,
                    int age,
                    double salary,

                    //Classe filha (domain.Salesman)
                    double percentPerSold,
                    double soldAmount
                    ) {
        //Acionar algum comportamento da classe pai
        super(code, name, address, age, salary);

        //Classe filha (domain.Salesman)
        this.percentPerSold = percentPerSold;
        this.soldAmount = soldAmount;
    }

    //Sobrescrevendo
    @Override
    public String getCode(){

        //Usar algum comportamento da antiga implementação
        // super.getCode();
        return "SL" +

                //Pode usar pq no 'employee' esta como 'protected'
                this.code;
    }

    @Override
    public double getFullSalary(){
        return this.salary + ((soldAmount * percentPerSold) / 100);
    }

    //Construtor sem argumentos
    public Salesman() {
    }

    public double getPercentPerSold() {
        return percentPerSold;
    }

    public void setPercentPerSold(double percentPerSold) {
        this.percentPerSold = percentPerSold;
    }

    public double getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(double soldAmount) {
        this.soldAmount = soldAmount;
    }


}
