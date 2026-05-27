package domain;//Estende da classe 'domain.Employee'

public non-sealed class Manager extends Employee{
    private String login;
    private String password;
    private double commission;

    //Sobrescrevendo
    @Override
    public String getCode(){

        //Usar algum comportamento da antiga implementação
        // super.getCode();
        return "MN" +

                //Pode usar pq no 'employee' esta como 'protected'
                this.code;
    }

    //Precisa ter o construtor
    public Manager(String code,
                   String name,
                   String address,
                   int age,
                   double salary,

                   //Classe filha (domain.Manager)
                   String login,
                   String password,
                   double commission
    ) {
        //Acionar algum comportamento da classe pai
        super(code, name, address, age, salary);

        //Classe filha (domain.Manager)
        this.login = login;
        this.password = password;
        this.commission = commission;
    }

    //Construtor sem argumentos
    public Manager() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    //Obrigue que todas as classes terem um met odo para poder retornar t odo o salario do colaborador
    @Override
    public double getFullSalary(){
        return this.salary + this.commission;
    }

    public double getFullSalary(double extra){
        return this.getFullSalary() + extra;
    }
}
